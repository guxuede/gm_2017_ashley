package com.guxuede.gm.gdx.actions.animation;

import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class AnimationAction extends TemporalAction {

    private final String animationName;
    private final float directionInDegrees;

    public AnimationAction(String animationName, float directionInDegrees, float duration){
        this.animationName = animationName;
        this.directionInDegrees = directionInDegrees;
        setDuration(duration);
    }
    public AnimationAction(String animationName,float duration){
        this.animationName = animationName;
        this.directionInDegrees = -1;
        setDuration(duration);
    }

    @Override
    protected void begin() {
        if(directionInDegrees >0){
            ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);
            actorStateComponent.setDirectionInDegrees(directionInDegrees);
        }

        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(actor);
        actorAnimationComponent.playAnimation(animationName, getDuration());

    }

    @Override
    protected void end() {
    }

    @Override
    protected void update(float percent) {

    }
}
