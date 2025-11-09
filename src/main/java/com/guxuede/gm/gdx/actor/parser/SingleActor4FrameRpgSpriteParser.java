package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.ResourceManager;

//
public class SingleActor4FrameRpgSpriteParser extends AbstractRpgActorSpriteParser {

    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        int[] walkFrameCycle = getWalkFrameCycle();
        int numberOfFrameOfOneLine = getNumberOfFrameOfOneLine(parseContext);
        int numberOfActor = getNumberOfActor(jsonValue, parseContext);
        animationHolder.addAnimation(AnimationHolder.WALK_DOWN_ANIMATION, parseAnimation(parseContext, (0 + numberOfActor) * numberOfFrameOfOneLine, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_LEFT_ANIMATION,parseAnimation(parseContext, (1 + numberOfActor) * numberOfFrameOfOneLine, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_RIGHT_ANIMATION,parseAnimation(parseContext, (2 + numberOfActor) * numberOfFrameOfOneLine, walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_UP_ANIMATION,parseAnimation(parseContext, (3 + numberOfActor) * numberOfFrameOfOneLine, walkFrameCycle));

        int[] idleFrameCycle = getIdleFrameCycle();
        parseContext.frameDuration = parseContext.frameDuration * 0.5f;
        animationHolder.addAnimation(AnimationHolder.STOP_DOWN_ANIMATION,parseAnimation(parseContext, (0 + numberOfActor) * numberOfFrameOfOneLine, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_LEFT_ANIMATION,parseAnimation(parseContext, (1 + numberOfActor) * numberOfFrameOfOneLine, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_RIGHT_ANIMATION,parseAnimation(parseContext, (2 + numberOfActor) * numberOfFrameOfOneLine, idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_UP_ANIMATION,parseAnimation(parseContext, (3 + numberOfActor) * numberOfFrameOfOneLine, idleFrameCycle));
    }

    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return 4;
    }

    protected int getNumberOfActor(JsonValue jsonValue, ParseContext parseContext) {
        return 0;
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2, 3};
    }
}
