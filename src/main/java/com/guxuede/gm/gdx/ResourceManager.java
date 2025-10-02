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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceManager {
    private static ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
    private static final Map<String,Sound> soundMap = new HashMap<String, Sound>();
    private static final LongMap<Sound> spriteSoundMap = new LongMap<Sound>();
    private static final Map<String,Texture> TEXTURE_MAP = new HashMap<String, Texture>();
    private static final Map<String,TextureRegion> TEXTURE_REGION_MAP =  new HashMap<String, TextureRegion>();
    private static final TextureAtlas TEXTURE_ATLAS_PACK =new TextureAtlas(Gdx.files.internal("data/pack"));
    private static final List<ActorHolder> ANIMATION_HOLDER_LIST = new ActorJsonParse().parse(Gdx.files.internal("data/actors.json"));
    public static final Map<String, Skill> SKILLS = ActorSkillParse.parseSkill(Gdx.files.internal("data/skill.html"));

    public static Skin skin=new Skin(Gdx.files.internal("skin/skin.json"));
    public static BitmapFont font = skin.getFont("default-font");

    public static Sprite shadow = new Sprite(getTextureRegion("data/180-Switch03",96,96,32,32));

    public static Sprite mouseAreaIndicator=new GdxSprite(getTextureRegion("data/Gun2", 0, 0, 192, 192));
    public static Sprite mouseTargetIndicator=new GdxSprite(getTextureRegion("data/Gun2", 84, 84, 24, 24));
    static {
        mouseAreaIndicator.setOriginCenter();
        mouseTargetIndicator.setOriginCenter();
    }

    public static Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("data/cursor_1.gif")), 0, 0);


    public static Texture getTexture(String name){
        Texture texture = null;
        texture = TEXTURE_MAP.get(name);
        if(texture==null){
            if(name.contains(".")){
                throw new RuntimeException("resource name not support contain dot or suffix");
            }
            FileHandle fileHandle = Gdx.files.internal(name+".png");
            if(fileHandle.exists()){
                texture = new Texture(fileHandle);
                TEXTURE_MAP.put(name, texture);
                TEXTURE_REGION_MAP.put(name, new TextureRegion(texture));
            }else{
                System.err.println(name + " resource not found.");
            }
        }
        return texture;
    }

    public static TextureRegion getTextureRegion(String name, int x, int y, int w, int h){
        return new TextureRegion(getTextureRegion(name),x,y,w,h);
    }

    public static TextureRegion getTextureRegion(String name, int xoffset, int yoffset){
        TextureRegion fullTextureRegion = getTextureRegion(name);
        return new TextureRegion(fullTextureRegion,xoffset,yoffset,fullTextureRegion.getRegionWidth(),fullTextureRegion.getRegionHeight());
    }

    public static TextureRegion getTextureRegion(String name){
        TextureRegion textureRegion = null;
        textureRegion = TEXTURE_REGION_MAP.get(name);
        if(textureRegion==null){
            textureRegion = TEXTURE_ATLAS_PACK.findRegion(name);
        }
        if(textureRegion==null){
            Texture texture = getTexture(name);
            if(texture !=null){
                textureRegion = TEXTURE_REGION_MAP.get(name);
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
}
