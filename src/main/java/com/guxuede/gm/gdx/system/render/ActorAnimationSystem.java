package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.basic.libgdx.MathUtils;
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

        //actor animation have high priority
        if(stateComponent.currentAnimation !=null){
            if(stateComponent.currentAnimationTime ==0) {//初始化
                AnimationComponent animationComponent = Mappers.animationCM.get(entity);
                Animation animation = stateComponent.animationHolder.getAnimation(stateComponent.currentAnimation.hashCode(), MathUtils.convertDegreesToDirection(stateComponent.directionInDegrees));
                animationComponent.animation = animation;
                animationComponent.stateTime = 0;
                if(stateComponent.currentAnimationDuration <=0) {
                    stateComponent.currentAnimationDuration = animation.getAnimationDuration();//纠正
                }
            }

            if(stateComponent.currentAnimationTime <= stateComponent.currentAnimationDuration){
                stateComponent.currentAnimationTime += deltaTime;
                return;
            }else{
                stateComponent.currentAnimation = null;
                stateComponent.currentAnimationTime = 0;
                stateComponent.currentAnimationDuration = 0;
            }
        }

        //then process moving and idle animation
        if(stateComponent.isMoving){
            doAnimation(entity,deltaTime,AnimationHolder.WALK, MathUtils.convertDegreesToDirection(stateComponent.directionInDegrees));
        }else{
            doAnimation(entity,deltaTime,AnimationHolder.IDLE, MathUtils.convertDegreesToDirection(stateComponent.directionInDegrees));
        }
    }

    public void doAnimation(Entity entity, float deltaTime,int animationName, int direction){
        AnimationComponent animationComponent = Mappers.animationCM.get(entity);
        ActorAnimationComponent actorAnimationComponent = Mappers.animationHolderCM.get(entity);
        animationComponent.animation = actorAnimationComponent.animationHolder.getAnimation(animationName, direction);
    }
}
