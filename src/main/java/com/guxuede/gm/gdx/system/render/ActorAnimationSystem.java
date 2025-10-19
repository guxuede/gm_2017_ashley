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
                animationComponent.animation = stateComponent.animationHolder.getAnimation(stateComponent.adhotAnimation.hashCode());
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
            switch (stateComponent.direction) {
                case AnimationComponent.UP:
                    doAnimation(entity,deltaTime,AnimationHolder.WALK_UP_ANIMATION);
                    break;
                case AnimationComponent.DOWN:
                    doAnimation(entity,deltaTime,AnimationHolder.WALK_DOWN_ANIMATION);
                    break;
                case AnimationComponent.RIGHT:
                    doAnimation(entity,deltaTime,AnimationHolder.WALK_RIGHT_ANIMATION);
                    break;
                case AnimationComponent.LEFT:
                    doAnimation(entity,deltaTime,AnimationHolder.WALK_LEFT_ANIMATION);
                    break;
                default:
                    doAnimation(entity,deltaTime,AnimationHolder.WALK_DOWN_ANIMATION);
                    break;
            }
        }else{
            //idel
            switch (stateComponent.direction) {
                case AnimationComponent.UP:
                    doAnimation(entity,deltaTime,AnimationHolder.STOP_UP_ANIMATION);
                    break;
                case AnimationComponent.DOWN:
                    doAnimation(entity,deltaTime,AnimationHolder.STOP_DOWN_ANIMATION);
                    break;
                case AnimationComponent.RIGHT:
                    doAnimation(entity,deltaTime,AnimationHolder.STOP_RIGHT_ANIMATION);
                    break;
                case AnimationComponent.LEFT:
                    doAnimation(entity,deltaTime,AnimationHolder.STOP_LEFT_ANIMATION);
                    break;
                default:
                    doAnimation(entity,deltaTime,AnimationHolder.STOP_DOWN_ANIMATION);
                    break;
            }
        }
    }

    public void doAnimation(Entity entity, float deltaTime,int animationName){
        AnimationComponent animationComponent = Mappers.animationCM.get(entity);
        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(entity);
        animationComponent.animation = actorAnimationComponent.animationHolder.getAnimation(animationName);
    }
}
