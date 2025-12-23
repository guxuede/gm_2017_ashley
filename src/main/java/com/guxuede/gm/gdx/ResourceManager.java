package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.TaskCloner;
import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.AlwaysFail;
import com.badlogic.gdx.ai.btree.decorator.Include;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.guxuede.gm.gdx.actor.parser.ActorHolder;
import com.guxuede.gm.gdx.actor.parser.ActorJsonParse;
import com.guxuede.gm.gdx.actor.parser.ActorSkillParse;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.ai.PlayTask;
import com.guxuede.gm.gdx.ai.RestTask;
import com.guxuede.gm.gdx.ai.WalkTask;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.component.skill.ScriptSkill;
import com.guxuede.gm.gdx.component.skill.Skill;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class ResourceManager {

    private static AssetManager assetManager = new AssetManager();
    private static ScriptEngine scriptEngine;
    static {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngineManager.registerEngineName("nashorn",new NashornScriptEngineFactory());
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
    }

    public static final BitmapFont myFont;
    static {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/ARKai_T.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 16;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + Gdx.files.internal("data/fonts/word.txt").readString();
        myFont = generator.generateFont(parameter);
        myFont.getData().markupEnabled = true;
        generator.dispose(); // Dispose the generator to prevent memory leaks, the font remains valid
    }


    private static final Map<String,Sound> soundMap = new HashMap<String, Sound>();
    private static final LongMap<Sound> spriteSoundMap = new LongMap<Sound>();
    private static final Map<String,Texture> TEXTURE_MAP = new HashMap<String, Texture>();
    private static final Map<String,TextureRegion> TEXTURE_REGION_MAP =  new HashMap<String, TextureRegion>();
    private static final Map<String,TextureAtlas> TEXTURE_ATLAS_PACK =new HashMap<>();//new TextureAtlas(Gdx.files.internal("data/texture/actor/pack.atlas"));
    private static final List<ActorHolder> ANIMATION_HOLDER_LIST = new ArrayList<>();
    static{
        ActorJsonParse actorJsonParse = new ActorJsonParse();
        ANIMATION_HOLDER_LIST.addAll(actorJsonParse.parse(Gdx.files.internal("data/texture/actors.json")));
        ANIMATION_HOLDER_LIST.addAll(actorJsonParse.parse(Gdx.files.internal("data/texture/effects.json")));
        ANIMATION_HOLDER_LIST.addAll(actorJsonParse.parse(Gdx.files.internal("data/texture/projectiles.json")));
        ANIMATION_HOLDER_LIST.addAll(actorJsonParse.parse(Gdx.files.internal("data/texture/weapons.json")));
    }
    public static final Map<String, Skill> SKILLS = ActorSkillParse.parseSkill(Gdx.files.internal("data/skill/skill.html"));

    public static Skin skin;
    static{
        ObjectMap<String, Object> fontMap = new ObjectMap<String, Object>();
        fontMap.put("default", myFont);
        fontMap.put("default-font", myFont);
        /* Create the SkinParameter and supply the ObjectMap to it */
        SkinLoader.SkinParameter parameter = new SkinLoader.SkinParameter(fontMap);
        assetManager.load("skin/kenney-pixel/skin.json", Skin.class, parameter);
        assetManager.finishLoading();
        skin = assetManager.get("skin/kenney-pixel/skin.json", Skin.class);
    }

    public static Sprite shadow = new Sprite(getTextureRegion("data/shadow",96,96,32,32));

    public static Sprite mouseAreaIndicator=new GdxSprite(getTextureRegion("data/texture/effect/Gun2", 0, 0, 192, 192));
    public static Sprite mouseTargetIndicator=new GdxSprite(getTextureRegion("data/texture/effect/Gun2", 84, 84, 24, 24));
    static {
        mouseAreaIndicator.setOriginCenter();
        mouseTargetIndicator.setOriginCenter();
    }

    public static Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("data/cursor_1.gif")), 0, 0);


    public static ParticleEffectPool bombEffectPool;
    static {
        ParticleEffect bombEffect = new ParticleEffect();
        bombEffect.load(Gdx.files.internal("data/particles/slash.p"), new TextureAtlas(Gdx.files.internal("data/particles/slash.atlas")));
//        bombEffect.setEmittersCleanUpBlendFunction(false);

        bombEffectPool = new ParticleEffectPool(bombEffect, 1, 2);

    }



    public static Texture getTexture(String path){
        Texture texture = null;
        texture = TEXTURE_MAP.get(path);
        if(texture==null){
            if(path.contains(".")){
                throw new RuntimeException("resource name not support contain dot or suffix");
            }
            FileHandle fileHandle = Gdx.files.internal(path+".png");
            if(fileHandle.exists()){
                texture = new Texture(fileHandle);
                TEXTURE_MAP.put(path, texture);
                TEXTURE_REGION_MAP.put(path, new TextureRegion(texture));
            }else{
                throw new RuntimeException(path + " resource not found.");
            }
        }
        return texture;
    }

    public static TextureRegion getTextureRegion(String path, int x, int y, int w, int h){
        return new TextureRegion(getTextureRegion(path),x,y,w,h);
    }

    public static TextureRegion getTextureRegion(String path, int xoffset, int yoffset){
        TextureRegion fullTextureRegion = getTextureRegion(path);
        return new TextureRegion(fullTextureRegion,xoffset,yoffset,fullTextureRegion.getRegionWidth(),fullTextureRegion.getRegionHeight());
    }

    public static TextureRegion getTextureRegion(String path){
        TextureRegion textureRegion = null;
        textureRegion = TEXTURE_REGION_MAP.get(path);
        if(textureRegion==null){
            if(path.contains(".atlas")){
                String regionName = StringUtils.substringAfter(path, ".atlas/");
                String atlas = StringUtils.substringBefore(path, regionName);
                TextureAtlas textureAtlas = getTextureAtlas(atlas);
                textureRegion = textureAtlas.findRegion(regionName);
                TEXTURE_REGION_MAP.put(path, textureRegion);
            }else{
                Texture texture = getTexture(path);
                if(texture !=null){
                    textureRegion = TEXTURE_REGION_MAP.get(path);
                }
            }
        }
        return textureRegion;
    }

    public static AnimationHolder getActorAnimation(String name){
        for(ActorHolder animationHolder : ANIMATION_HOLDER_LIST){
            if(name.equals(animationHolder.name)){
                return animationHolder.animationHolder;//.getCopy();
            }
        }
        if("spine".equalsIgnoreCase(name)){

            new AnimationHolder();
        }
        return null;
    }

    public static ActorHolder getActor(String name){
        for(ActorHolder animationHolder : ANIMATION_HOLDER_LIST){
            if(name.equals(animationHolder.name)){
                return animationHolder;//.getCopy();
            }
        }
        return null;
    }

    public static Sound getSoundOrLoad(String soundFile){
        if(soundMap.containsKey(soundFile)){
            return soundMap.get(soundFile);
        }
        if(Gdx.files.internal("data/sounds/"+soundFile).exists()){
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/"+soundFile));
            soundMap.put(soundFile,sound);
        }
        return soundMap.get(soundFile);
    }

    public static void putSpriteSound(long spriteId,Sound sound ){
        spriteSoundMap.put(spriteId,sound);
    }

    public static Sound getSpriteSound(long spriteId){
        return spriteSoundMap.get(spriteId);
    }

    public static Skill getSkillById(String id){
        return SKILLS.get(id);
    }

    public static List<Skill> getSkillByIds(String... ids){
        List<Skill> skills = new ArrayList<Skill>();
        for(String id:ids){
            skills.add(((ScriptSkill)SKILLS.get(id)).clone());
        }
        return skills;
    }

    public static ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public static TextureAtlas getTextureAtlas(String path){
        return TEXTURE_ATLAS_PACK.computeIfAbsent(path, new Function<String, TextureAtlas>() {
            @Override
            public TextureAtlas apply(String s) {
                return new TextureAtlas(Gdx.files.internal(path));
            }
        });
    }











    /// ////////////////AI
    public static BehaviorTreeLibraryManager behaviorTreeLibraryManager = createActor();
    public static BehaviorTreeLibraryManager createActor () {
        BehaviorTreeLibraryManager libraryManager = BehaviorTreeLibraryManager.getInstance();
        BehaviorTreeLibrary library = new BehaviorTreeLibrary(BehaviorTreeParser.DEBUG_HIGH);
        registerDogBehavior(library);
        libraryManager.setLibrary(library);
        return libraryManager;
    }

    private static void registerDogBehavior (BehaviorTreeLibrary library) {
        Include<Entity> include = new Include<Entity>();
        include.subtree = "dog.actual";
        BehaviorTree<Entity> includeBehavior = new BehaviorTree<Entity>(include);
        library.registerArchetypeTree("dog", includeBehavior);

        BehaviorTree<Entity> actualBehavior = new BehaviorTree<Entity>(createDogBehavior());
        library.registerArchetypeTree("dog.actual", actualBehavior);
    }

    private static Task<Entity> createDogBehavior () {
        Selector<Entity> selector = new Selector<Entity>();

        {
            Parallel<Entity> parallel = new Parallel<Entity>();
            selector.addChild(parallel);
            parallel.addChild(new AlwaysFail<Entity>(new RestTask()));
        }

        {
            Sequence<Entity> sequence = new Sequence<Entity>();
            selector.addChild(sequence);

            sequence.addChild(new WalkTask());
            sequence.addChild(new PlayTask());
        }

        return selector;
    }
}
