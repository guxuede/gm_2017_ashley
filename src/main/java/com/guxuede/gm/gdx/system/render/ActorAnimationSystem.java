package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.AnimationComponent;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class ActorAnimationSystem extends IteratingSystem {

    private static final Family family = Family.all(ActorAnimationComponent.class,AnimationComponent.class).get();

    public ActorAnimationSystem(){
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ActorAnimationComponent stateComponent = Mappers.animationHolderCM.get(entity);

        //adhot Animation have high priority
        if(stateComponent.adhotAnimation !=null){
            if(stateComponent.adhotAnimationTime <= stateComponent.adhotAnimationDuration){
                AnimationComponent animationComponent = Mappers.animationCM.get(entity);
                animationComponent.animation = stateComponent.animationHolder.getAnimation(stateComponent.adhotAnimation.hashCode(), stateComponent.direction);
                stateComponent.adhotAnimationTime += deltaTime;
                return;
            }else{
                stateComponent.adhotAnimation = null;
                stateComponent.adhotAnimationTime = 0;
                stateComponent.adhotAnimationDuration = 0;
            }
        }


        //moving
        if(stateComponent.isMoving){
            doAnimation(entity,deltaTime,AnimationHolder.WALK, stateComponent.direction);
        }else{
            doAnimation(entity,deltaTime,AnimationHolder.IDLE, stateComponent.direction);
        }
    }

    public void doAnimation(Entity entity, float deltaTime,int animationName, int direction){
        AnimationComponent animationComponent = Mappers.animationCM.get(entity);
        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(entity);
        animationComponent.animation = actorAnimationComponent.animationHolder.getAnimation(animationName, direction);
    }
}
