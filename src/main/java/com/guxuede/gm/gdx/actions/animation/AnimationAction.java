package com.guxuede.gm.gdx.actions.animation;

import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class AnimationAction extends TemporalAction {

    private final String animationName;

    public AnimationAction(String animationName,float duration){
        this.animationName = animationName;
        setDuration(duration);
    }


    @Override
    protected void begin() {
        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(actor);
        actorAnimationComponent.setCurrentAnimation(animationName, getDuration());
    }

    @Override
    protected void end() {
        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(actor);
        actorAnimationComponent.adhotAnimation = null;
    }

    @Override
    protected void update(float percent) {

    }
}
