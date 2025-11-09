package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.actor.parser.ParseContext;

//由CharGen_v0.9.3 生成 无跳跃/跑动作,idel 和walk 在一行
public class LpcStyle2Parser extends AbstractRpgActorSpriteParser {

    private static final int NUMBER_OF_FRAME_OF_ONE_LINE = 13;

    private static final int WALK_UP_ANIMATION_START_NUMBER = 8 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int WALK_LEFT_ANIMATION_START_NUMBER = 9 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_DOWN_ANIMATION_START_NUMBER = 10 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_RIGHT_ANIMATION_START_NUMBER = 11 * NUMBER_OF_FRAME_OF_ONE_LINE;;


    private static final int IDLE_UP_ANIMATION_START_NUMBER = 8 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_LEFT_ANIMATION_START_NUMBER = 9 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int IDLE_DOWN_ANIMATION_START_NUMBER = 10 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_RIGHT_ANIMATION_START_NUMBER = 11 * NUMBER_OF_FRAME_OF_ONE_LINE;


    @Override
    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext =super.buildDefaultParserContext();
        parseContext.width = 64;
        parseContext.height = 64;
        parseContext.frameDuration= 0.1f;
        return parseContext;
    }


    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        int[] walkFrameCycle = new int[]{1,2,3,4,5,6,7,8};

        animationHolder.addAnimation(AnimationHolder.WALK_UP_ANIMATION,parseAnimation(parseContext, WALK_UP_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_LEFT_ANIMATION,parseAnimation(parseContext, WALK_LEFT_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_DOWN_ANIMATION, parseAnimation(parseContext, WALK_DOWN_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_RIGHT_ANIMATION,parseAnimation(parseContext, WALK_RIGHT_ANIMATION_START_NUMBER, walkFrameCycle));

        parseContext.frameDuration= 0.5f;
        int[] idleFrameCycle = new int[]{9,9,10};
        animationHolder.addAnimation(AnimationHolder.STOP_UP_ANIMATION,parseAnimation(parseContext, IDLE_UP_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_LEFT_ANIMATION,parseAnimation(parseContext, IDLE_LEFT_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_DOWN_ANIMATION,parseAnimation(parseContext, IDLE_DOWN_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_RIGHT_ANIMATION,parseAnimation(parseContext, IDLE_RIGHT_ANIMATION_START_NUMBER, idleFrameCycle));
    }

}
