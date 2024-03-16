package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.GdxSprite;
import com.guxuede.gm.gdx.ResourceManager;

import static com.guxuede.gm.gdx.actor.parser.ActorJsonParse.*;

public class RpgMakerSpriteParser extends DefaultSpriteParser {

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
        TextureRegion aSpriteTextureRegion = getSpriteTextureRegion(jsonValue, parseContext);
        //walk
        {
            final GdxSprite[] frames = new GdxSprite[3];
            for (int i = 0 ; i < 3 ; i++) {
                frames[i]=parseNumbersSprite(parseContext,aSpriteTextureRegion, i);
            }
            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(WALK_DOWN_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[3];
            for (int i = 0 ; i < 3 ; i++) {
                frames[i]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 3 + i);
            }

            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(WALK_LEFT_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[3];
            for (int i = 0 ; i < 3 ; i++) {
                frames[i]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 6 + i);
            }

            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(WALK_RIGHT_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[3];
            for (int i = 0 ; i < 3 ; i++) {
                frames[i]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 9 + i);
            }

            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(WALK_UP_ANIMATION.hashCode(),animation);
        }


        //idel
        {
            final GdxSprite[] frames = new GdxSprite[1];
            frames[0]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 1);
            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(STOP_DOWN_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[1];
            frames[0]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 4);

            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(STOP_LEFT_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[1];
            frames[0]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 7);


            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(STOP_RIGHT_ANIMATION.hashCode(),animation);
        }

        {
            final GdxSprite[] frames = new GdxSprite[1];
            frames[0]=parseNumbersSprite(parseContext,aSpriteTextureRegion, 10);

            final Animation animation = new Animation(localParseContext.frameDuration,frames);
            animationHolder.addAnimation(STOP_UP_ANIMATION.hashCode(),animation);
        }

        parseBattleAnimation(animationHolder, jsonValue, parseContext);
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
            parseBattleAnimation(animationHolder, "chant",parseContext,textureRegion,new int[]{18,19,20});
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

    private void parseBattleAnimation(AnimationHolder animationHolder, String animationName, ParseContext parseContext,TextureRegion textureRegion,int[] frameNumer){
        final GdxSprite[] frames = new GdxSprite[3];
        frames[0]=parseNumbersSprite(parseContext, textureRegion,frameNumer[0]);
        frames[1]=parseNumbersSprite(parseContext, textureRegion,frameNumer[1]);
        frames[2]=parseNumbersSprite(parseContext, textureRegion,frameNumer[2]);
        final Animation animation = new Animation(parseContext.frameDuration,frames);
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
