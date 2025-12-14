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

    private static final int SLASH_UP_ANIMATION_START_NUMBER = 12 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SLASH_LEFT_ANIMATION_START_NUMBER = 13 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int SLASH_DOWN_ANIMATION_START_NUMBER = 14 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SLASH_RIGHT_ANIMATION_START_NUMBER = 15 * NUMBER_OF_FRAME_OF_ONE_LINE;

    private static final int SIT_UP_ANIMATION_START_NUMBER = 30 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SIT_LEFT_ANIMATION_START_NUMBER = 31 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int SIT_DOWN_ANIMATION_START_NUMBER = 32 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int SIT_RIGHT_ANIMATION_START_NUMBER = 33 * NUMBER_OF_FRAME_OF_ONE_LINE;

    private static final int WALTER_UP_ANIMATION_START_NUMBER = 4 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int WALTER_LEFT_ANIMATION_START_NUMBER = 5 * NUMBER_OF_FRAME_OF_ONE_LINE;//
    private static final int WALTER_DOWN_ANIMATION_START_NUMBER = 6 * NUMBER_OF_FRAME_OF_ONE_LINE;
    private static final int WALTER_RIGHT_ANIMATION_START_NUMBER = 7 * NUMBER_OF_FRAME_OF_ONE_LINE;

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


        parseContext.frameDuration= 0.1f;
        int[] slashFrameCycle = new int[]{0,1,2,3,4,5};
        animationHolder.addAnimation(AnimationHolder.SLASH, AnimationHolder.DIRECTION_UP,parseAnimation(parseContext, SLASH_UP_ANIMATION_START_NUMBER, slashFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SLASH, AnimationHolder.DIRECTION_LEFT,parseAnimation(parseContext, SLASH_LEFT_ANIMATION_START_NUMBER, slashFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SLASH, AnimationHolder.DIRECTION_DOWN,parseAnimation(parseContext, SLASH_DOWN_ANIMATION_START_NUMBER, slashFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SLASH, AnimationHolder.DIRECTION_RIGHT,parseAnimation(parseContext, SLASH_RIGHT_ANIMATION_START_NUMBER, slashFrameCycle));

        parseContext.frameDuration= 0.1f;
        int[] sitFrameCycle = new int[]{0};
        animationHolder.addAnimation(AnimationHolder.SIT, AnimationHolder.DIRECTION_UP,parseAnimation(parseContext, SIT_UP_ANIMATION_START_NUMBER, sitFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SIT, AnimationHolder.DIRECTION_LEFT,parseAnimation(parseContext, SIT_LEFT_ANIMATION_START_NUMBER, sitFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SIT, AnimationHolder.DIRECTION_DOWN,parseAnimation(parseContext, SIT_DOWN_ANIMATION_START_NUMBER, sitFrameCycle));
        animationHolder.addAnimation(AnimationHolder.SIT, AnimationHolder.DIRECTION_RIGHT,parseAnimation(parseContext, SIT_RIGHT_ANIMATION_START_NUMBER, sitFrameCycle));

        parseContext.frameDuration= 0.1f;
        int[] walterFrameCycle = new int[]{0,1,2,3,4,5,6,7};
        animationHolder.addAnimation(AnimationHolder.WATER, AnimationHolder.DIRECTION_UP,parseAnimation(parseContext, WALTER_UP_ANIMATION_START_NUMBER, walterFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WATER, AnimationHolder.DIRECTION_LEFT,parseAnimation(parseContext, WALTER_LEFT_ANIMATION_START_NUMBER, walterFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WATER, AnimationHolder.DIRECTION_DOWN,parseAnimation(parseContext, WALTER_DOWN_ANIMATION_START_NUMBER, walterFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WATER, AnimationHolder.DIRECTION_RIGHT,parseAnimation(parseContext, WALTER_RIGHT_ANIMATION_START_NUMBER, walterFrameCycle));

    }

}
