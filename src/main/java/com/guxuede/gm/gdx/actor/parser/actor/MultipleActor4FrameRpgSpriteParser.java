package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.actor.parser.ParseContext;

//
public class MultipleActor4FrameRpgSpriteParser extends AbstractRpgActorSpriteParser {

    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        int[] walkFrameCycle = getWalkFrameCycle();

        int numberOfActionOfActor = getNumberOfActionOfActor(parseContext);//一个角色几个动作,在图片资源类,通常4个,他们是向下走 向左走 向右走 向上走
        int numberOfFrameOfActorOneAction = getNumberOfFrameOfActorAction(parseContext);//一个动作有几帧,比如向下走

        int numberOfFrameOfOneLine = getNumberOfFrameOfOneLine(parseContext);
        int numberOfActorOfOneLine = numberOfFrameOfOneLine/numberOfFrameOfActorOneAction;
        int numberOfActor = getNumberOfActor(jsonValue, parseContext);

        int rowNumberOfActor = numberOfActor/numberOfActorOfOneLine;
        int columNumberOfActor = numberOfActor%numberOfActorOfOneLine;

        int frameOffSet = rowNumberOfActor * numberOfActorOfOneLine * (numberOfFrameOfActorOneAction * numberOfActionOfActor) + columNumberOfActor * numberOfFrameOfActorOneAction;
        animationHolder.addAnimation(AnimationHolder.WALK_DOWN_ANIMATION, parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine * 0), walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_LEFT_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*1), walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_RIGHT_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*2), walkFrameCycle));
        animationHolder.addAnimation(AnimationHolder.WALK_UP_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*3), walkFrameCycle));

        int[] idleFrameCycle = getIdleFrameCycle();
        parseContext.frameDuration = 0.5f;
        animationHolder.addAnimation(AnimationHolder.STOP_DOWN_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*0), idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_LEFT_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*1), idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_RIGHT_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*2), idleFrameCycle));
        animationHolder.addAnimation(AnimationHolder.STOP_UP_ANIMATION,parseAnimation(parseContext, frameOffSet + (numberOfFrameOfOneLine*3), idleFrameCycle));
    }

    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return (int) (ResourceManager.getTextureRegion(parseContext.textureName).getRegionWidth()/parseContext.width);
    }

    protected int getNumberOfFrameOfActorAction(ParseContext parseContext) {
        return getWalkFrameCycle().length;
    }

    protected int getNumberOfActionOfActor(ParseContext parseContext) {
        return 4;
    }

    protected int getNumberOfActor(JsonValue jsonValue, ParseContext parseContext) {
        return jsonValue.getInt("actorNumber", 0);
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1,2};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2, 3};
    }
}
