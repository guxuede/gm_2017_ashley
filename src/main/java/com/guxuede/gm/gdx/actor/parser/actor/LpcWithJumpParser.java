package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.actor.parser.ParseContext;

public class LpcWithJumpParser extends AbstractRpgActorSpriteParser {

    private static final int NUMBER_OF_FRAME_OF_ONE_LINE = 13;

    private static final int SPELLCAST_UP_ANIMATION_START_NUMBER = 0 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SPELLCAST_LEFT_ANIMATION_START_NUMBER = 1 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int SPELLCAST_DOWN_ANIMATION_START_NUMBER = 2 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SPELLCAST_RIGHT_ANIMATION_START_NUMBER = 3 * NUMBER_OF_FRAME_OF_ONE_LINE;


    private static final int WALK_UP_ANIMATION_START_NUMBER = 8 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int WALK_LEFT_ANIMATION_START_NUMBER = 9 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_DOWN_ANIMATION_START_NUMBER = 10 * NUMBER_OF_FRAME_OF_ONE_LINE;;
    private static final int WALK_RIGHT_ANIMATION_START_NUMBER = 11 * NUMBER_OF_FRAME_OF_ONE_LINE;;


    private static final int IDLE_UP_ANIMATION_START_NUMBER = 22 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_LEFT_ANIMATION_START_NUMBER = 23 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int IDLE_DOWN_ANIMATION_START_NUMBER = 24 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int IDLE_RIGHT_ANIMATION_START_NUMBER = 25 * NUMBER_OF_FRAME_OF_ONE_LINE;


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
        parseContext.frameDuration= 0.3f;
        int[] spellcaseFrameCycle = new int[]{0,1,2,3,4,5,6};
        animationHolder.addAnimation(AnimationHolder.SPELLCAST,AnimationHolder.DIRECTION_UP,parseAnimation(parseContext, SPELLCAST_UP_ANIMATION_START_NUMBER, spellcaseFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SPELLCAST,AnimationHolder.DIRECTION_LEFT,parseAnimation(parseContext, SPELLCAST_LEFT_ANIMATION_START_NUMBER, spellcaseFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SPELLCAST,AnimationHolder.DIRECTION_DOWN,parseAnimation(parseContext, SPELLCAST_DOWN_ANIMATION_START_NUMBER, spellcaseFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SPELLCAST,AnimationHolder.DIRECTION_RIGHT,parseAnimation(parseContext, SPELLCAST_RIGHT_ANIMATION_START_NUMBER, spellcaseFrameCycle));


        parseContext.frameDuration= 0.1f;
        int[] walkFrameCycle = new int[]{1,2,3,4,5,6,7,8};

        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_UP,parseAnimation(parseContext, WALK_UP_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_LEFT,parseAnimation(parseContext, WALK_LEFT_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_DOWN, parseAnimation(parseContext, WALK_DOWN_ANIMATION_START_NUMBER, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_RIGHT,parseAnimation(parseContext, WALK_RIGHT_ANIMATION_START_NUMBER, walkFrameCycle));


        parseContext.frameDuration= 0.5f;
        int[] idleFrameCycle = new int[]{0,0,1};
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_UP, parseAnimation(parseContext, IDLE_UP_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_LEFT, parseAnimation(parseContext, IDLE_LEFT_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_DOWN, parseAnimation(parseContext, IDLE_DOWN_ANIMATION_START_NUMBER, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_RIGHT, parseAnimation(parseContext, IDLE_RIGHT_ANIMATION_START_NUMBER, idleFrameCycle));
    }

}
