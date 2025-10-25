package com.guxuede.gm.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.LongMap;
import com.guxuede.gm.gdx.actor.parser.ActorHolder;
import com.guxuede.gm.gdx.actor.parser.ActorJsonParse;
import com.guxuede.gm.gdx.actor.parser.ActorSkillParse;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.component.skill.ScriptSkill;
import com.guxuede.gm.gdx.component.skill.Skill;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ResourceManager {

    private static ScriptEngine scriptEngine;
    static {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngineManager.registerEngineName("nashorn",new NashornScriptEngineFactory());
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
    }
    private static final Map<String,Sound> soundMap = new HashMap<String, Sound>();
    private static final LongMap<Sound> spriteSoundMap = new LongMap<Sound>();
    private static final Map<String,Texture> TEXTURE_MAP = new HashMap<String, Texture>();
    private static final Map<String,TextureRegion> TEXTURE_REGION_MAP =  new HashMap<String, TextureRegion>();
    private static final Map<String,TextureAtlas> TEXTURE_ATLAS_PACK =new HashMap<>();//new TextureAtlas(Gdx.files.internal("data/texture/actor/pack.atlas"));
    private static final List<ActorHolder> ANIMATION_HOLDER_LIST = new ActorJsonParse().parse(Gdx.files.internal("data/texture/actors.json"));
    public static final Map<String, Skill> SKILLS = ActorSkillParse.parseSkill(Gdx.files.internal("data/skill.html"));

    public static Skin skin=new Skin(Gdx.files.internal("skin/kenney-pixel/skin.json"));
    public static BitmapFont font = skin.getFont("default-font");

    public static Sprite shadow = new Sprite(getTextureRegion("data/180-Switch03",96,96,32,32));

    public static Sprite mouseAreaIndicator=new GdxSprite(getTextureRegion("data/Gun2", 0, 0, 192, 192));
    public static Sprite mouseTargetIndicator=new GdxSprite(getTextureRegion("data/Gun2", 84, 84, 24, 24));
    static {
        mouseAreaIndicator.setOriginCenter();
        mouseTargetIndicator.setOriginCenter();
    }

    public static Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("data/cursor_1.gif")), 0, 0);


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
}
