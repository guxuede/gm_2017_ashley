package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;

public class LpcWithJumpParser extends DefaultSpriteParser{

    private static final int NUMBER_OF_FRAME_OF_ONE_LINE = 13;

    private static final int WALK_UP_ANIMATION_START_NUMBER = 8 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int WALK_LEFT_ANIMATION_START_NUMBER = 9 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_DOWN_ANIMATION_START_NUMBER = 10 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_RIGHT_ANIMATION_START_NUMBER = 11 * NUMBER_OF_FRAME_OF_ONE_LINE;;


    private static final int IDLE_UP_ANIMATION_START_NUMBER = 22 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_LEFT_ANIMATION_START_NUMBER = 23 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int IDLE_DOWN_ANIMATION_START_NUMBER = 24 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_RIGHT_ANIMATION_START_NUMBER = 25 * NUMBER_OF_FRAME_OF_ONE_LINE;


    @Override
    public ActorHolder parseActor(JsonValue jsonValue) {
        ActorHolder actorHolder = super.parseActor(jsonValue);
        actorHolder.shadowWidth = actorHolder.animationHolder.width*0.6f;
        return actorHolder;
    }

    @Override
    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext =super.buildDefaultParserContext();
        parseContext.width = 64;
        parseContext.height = 64;
        parseContext.frameDuration= 0.2f;
        parseContext.drawOffSetY = parseContext.height/2; // by default. LPC actor
        return parseContext;
    }


    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        int[] walkFrameCycle = new int[]{1,2,3,4,5,6,7,8};

        animationHolder.addAnimation(AnimationHolder.WALK_UP_ANIMATION,parseAnimation(parseContext, WALK_UP_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_LEFT_ANIMATION,parseAnimation(parseContext, WALK_LEFT_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_DOWN_ANIMATION, parseAnimation(parseContext, WALK_DOWN_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_RIGHT_ANIMATION,parseAnimation(parseContext, WALK_RIGHT_ANIMATION_START_NUMBER, walkFrameCycle));

        int[] idleFrameCycle = new int[]{0,0,1};
        animationHolder.addAnimation(AnimationHolder.STOP_UP_ANIMATION,parseAnimation(parseContext, IDLE_UP_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_LEFT_ANIMATION,parseAnimation(parseContext, IDLE_LEFT_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_DOWN_ANIMATION,parseAnimation(parseContext, IDLE_DOWN_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_RIGHT_ANIMATION,parseAnimation(parseContext, IDLE_RIGHT_ANIMATION_START_NUMBER, idleFrameCycle));
    }

    public Animation parseAnimation(ParseContext parseContext, int animationStartNumber, int animationFrameCount){
        final GdxSprite[] frames = new GdxSprite[animationFrameCount];
        for (int i = 0; i< animationFrameCount ; i++) {
            frames[i] = parseNumbersSprite(parseContext, animationStartNumber + i);
        }
        final Animation animation = new Animation(parseContext.frameDuration,frames);
        return animation;
    }

    public Animation parseAnimation(ParseContext parseContext, int animationStartNumber, int[] animationFrameNumber){
        final GdxSprite[] frames = new GdxSprite[animationFrameNumber.length];
        for (int i = 0; i< animationFrameNumber.length ; i++) {
            frames[i] = parseNumbersSprite(parseContext, animationStartNumber + animationFrameNumber[i]);
        }
        final Animation animation = new Animation(parseContext.frameDuration,frames);
        return animation;
    }
}
