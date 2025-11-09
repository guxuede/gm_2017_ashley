package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.actor.parser.ParseContext;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.ResourceManager;

public class RpgMakerSpriteParser extends SingleActor4FrameRpgSpriteParser {

    @Override
    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext =super.buildDefaultParserContext();
        parseContext.width = 48;
        parseContext.height = 48;
        return parseContext;
    }

    //
    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        super.parseAnimations(jsonValue,parseContext,animationHolder);
        parseBattleAnimation(animationHolder, jsonValue, parseContext);
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2};
    }

    private void parseBattleAnimation(AnimationHolder animationHolder, JsonValue jsonValue, ParseContext localParseContext){
        ParseContext parseContext = extendParentParseContext(localParseContext, jsonValue);
        parseContext.width = 64;
        parseContext.height = 64;
        String textureRpgmBattle = jsonValue.getString("texture_rpgm_battle",null);
        if(textureRpgmBattle!=null){
            TextureRegion textureRegion = ResourceManager.getTextureRegion(textureRpgmBattle);
            parseAnimation(animationHolder, "idel",parseContext,textureRegion,new int[]{0,1,2});
            parseAnimation(animationHolder, "thrust",parseContext,textureRegion,new int[]{3,4,5});
            parseAnimation(animationHolder, "escape",parseContext,textureRegion,new int[]{6,7,8});
            parseAnimation(animationHolder, "wait",parseContext,textureRegion,new int[]{9,10,11});
            parseAnimation(animationHolder, "swing",parseContext,textureRegion,new int[]{12,13,14});
            parseAnimation(animationHolder, "victory",parseContext,textureRegion,new int[]{15,16,17});
            parseAnimation(animationHolder, "chant",parseContext,textureRegion,new int[]{18,19,20});//念诵
            parseAnimation(animationHolder, "missile",parseContext,textureRegion,new int[]{21,22,23});
            parseAnimation(animationHolder, "dying",parseContext,textureRegion,new int[]{24,25,26});
            parseAnimation(animationHolder, "guard",parseContext,textureRegion,new int[]{27,28,29});
            parseAnimation(animationHolder, "skill",parseContext,textureRegion,new int[]{30,31,32});
            parseAnimation(animationHolder, "abnormal",parseContext,textureRegion,new int[]{33,34,35});
            parseAnimation(animationHolder, "damage",parseContext,textureRegion,new int[]{36,37,38});
            parseAnimation(animationHolder, "spell",parseContext,textureRegion,new int[]{39,40,41});
            parseAnimation(animationHolder, "sleep",parseContext,textureRegion,new int[]{42,43,44});
            parseAnimation(animationHolder, "evade",parseContext,textureRegion,new int[]{45,46,47});//闪避
            parseAnimation(animationHolder, "item",parseContext,textureRegion,new int[]{48,49,50});
            parseAnimation(animationHolder, "dead",parseContext,textureRegion,new int[]{51,52,53});

        }

    }

    private void parseAnimation(AnimationHolder animationHolder, String animationName, ParseContext parseContext, TextureRegion textureRegion, int[] frameNumber){
        final GdxSprite[] frames = new GdxSprite[frameNumber.length];
        for(int i = 0; i< frameNumber.length; i++){
            frames[i]=parseNumbersSprite(parseContext, textureRegion,frameNumber[i]);

        }
        final Animation<TextureRegion> animation = new Animation<TextureRegion>(parseContext.frameDuration,frames);
        animationHolder.addAnimation(animationName.hashCode(),animation);
    }

}
