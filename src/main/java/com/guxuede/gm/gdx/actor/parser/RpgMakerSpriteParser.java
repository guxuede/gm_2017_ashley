package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.ResourceManager;

import static com.guxuede.gm.gdx.actor.parser.ActorJsonParse.*;

public class RpgMakerSpriteParser extends DefaultSpriteParser {

    @Override
    public ActorHolder parseActor(JsonValue jsonValue) {
        ActorHolder actorHolder = super.parseActor(jsonValue);
        actorHolder.shadowWidth = actorHolder.animationHolder.width*0.7f;
        return actorHolder;
    }

    @Override
    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext =super.buildDefaultParserContext();
        parseContext.width = 48;
        parseContext.height = 48;
        parseContext.drawOffSetY = parseContext.height/2; // by default. RPG marker actor
        return parseContext;
    }

    @Override
    protected void parseActorAttributes(JsonValue jsonValue, ParseContext parseContext, ActorHolder actorHolder) {
        actorHolder.age = 18;
        actorHolder.blood = 100;
        actorHolder.bounds = new Rectangle(0,0, parseContext.width, parseContext.height);
    }

    //
    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        ParseContext localParseContext = extendParentParseContext(parseContext, jsonValue);
        TextureRegion aSpriteTextureRegion = getSpriteTextureRegion(jsonValue, localParseContext);
        //walk
        {
            parseBattleAnimation(animationHolder,WALK_DOWN_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{0,1,2});
            parseBattleAnimation(animationHolder,WALK_LEFT_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{3,4,5});
            parseBattleAnimation(animationHolder,WALK_RIGHT_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{6,7,8});
            parseBattleAnimation(animationHolder,WALK_UP_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{9,10,11});

            parseBattleAnimation(animationHolder,STOP_DOWN_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{1});
            parseBattleAnimation(animationHolder,STOP_LEFT_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{4});
            parseBattleAnimation(animationHolder,STOP_RIGHT_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{7});
            parseBattleAnimation(animationHolder,STOP_UP_ANIMATION,localParseContext, aSpriteTextureRegion,  new int[]{10});

        }


        parseBattleAnimation(animationHolder, jsonValue, localParseContext);
    }


    private void parseBattleAnimation(AnimationHolder animationHolder, JsonValue jsonValue, ParseContext localParseContext){
        ParseContext parseContext = extendParentParseContext(localParseContext, jsonValue);
        parseContext.width = 64;
        parseContext.height = 64;
        String textureRpgmBattle = jsonValue.getString("texture_rpgm_battle",null);
        if(textureRpgmBattle!=null){
            TextureRegion textureRegion = ResourceManager.getTextureRegion(textureRpgmBattle);
            parseBattleAnimation(animationHolder, "idel",parseContext,textureRegion,new int[]{0,1,2});
            parseBattleAnimation(animationHolder, "thrust",parseContext,textureRegion,new int[]{3,4,5});
            parseBattleAnimation(animationHolder, "escape",parseContext,textureRegion,new int[]{6,7,8});
            parseBattleAnimation(animationHolder, "wait",parseContext,textureRegion,new int[]{9,10,11});
            parseBattleAnimation(animationHolder, "swing",parseContext,textureRegion,new int[]{12,13,14});
            parseBattleAnimation(animationHolder, "victory",parseContext,textureRegion,new int[]{15,16,17});
            parseBattleAnimation(animationHolder, "chant",parseContext,textureRegion,new int[]{18,19,20});//念诵
            parseBattleAnimation(animationHolder, "missile",parseContext,textureRegion,new int[]{21,22,23});
            parseBattleAnimation(animationHolder, "dying",parseContext,textureRegion,new int[]{24,25,26});
            parseBattleAnimation(animationHolder, "guard",parseContext,textureRegion,new int[]{27,28,29});
            parseBattleAnimation(animationHolder, "skill",parseContext,textureRegion,new int[]{30,31,32});
            parseBattleAnimation(animationHolder, "abnormal",parseContext,textureRegion,new int[]{33,34,35});
            parseBattleAnimation(animationHolder, "damage",parseContext,textureRegion,new int[]{36,37,38});
            parseBattleAnimation(animationHolder, "spell",parseContext,textureRegion,new int[]{39,40,41});
            parseBattleAnimation(animationHolder, "sleep",parseContext,textureRegion,new int[]{42,43,44});
            parseBattleAnimation(animationHolder, "evade",parseContext,textureRegion,new int[]{45,46,47});//闪避
            parseBattleAnimation(animationHolder, "item",parseContext,textureRegion,new int[]{48,49,50});
            parseBattleAnimation(animationHolder, "dead",parseContext,textureRegion,new int[]{51,52,53});

        }

    }


    private void parseBattleAnimation(AnimationHolder animationHolder, String animationName, ParseContext parseContext,TextureRegion textureRegion,int[] frameNumber){
        final GdxSprite[] frames = new GdxSprite[frameNumber.length];
        for(int i = 0; i< frameNumber.length; i++){
            frames[i]=parseNumbersSprite(parseContext, textureRegion,frameNumber[i]);

        }
        final Animation<TextureRegion> animation = new Animation<TextureRegion>(parseContext.frameDuration,frames);
        animationHolder.addAnimation(animationName.hashCode(),animation);
    }

    private static TextureRegion getSpriteTextureRegion(JsonValue jsonValue, ParseContext parseContext) {
        TextureRegion textureRegion = ResourceManager.getTextureRegion(parseContext.textureName);
        int actorNum  = jsonValue.getInt("actorNum");
        int aSpriteW = 48*3;
        int aSpriteH = 48*4;
        int spriteNumOfLine = textureRegion.getRegionWidth() / aSpriteW;//每行有几个角色
        int y = actorNum / spriteNumOfLine;//第几行
        int x = actorNum % spriteNumOfLine;//第几列

        int startXOffset = x * aSpriteW;
        int startYOffset = y * aSpriteH;
        TextureRegion aSpriteTextureRegion = new TextureRegion(textureRegion, startXOffset, startYOffset, aSpriteW, aSpriteH);
        return aSpriteTextureRegion;
    }
}
