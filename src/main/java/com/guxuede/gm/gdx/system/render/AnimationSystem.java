package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.AnimationComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class AnimationSystem extends IteratingSystem {

    private static final Family family = Family.all(AnimationComponent.class,PresentableComponent.class, PositionComponent.class).get();

    public AnimationSystem(){
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        AnimationComponent animationComponent = Mappers.animationCM.get(entity);

        presentableComponent.region = animationComponent.getNextKeyFrame(deltaTime,true);
        presentableComponent.zIndex = -positionComponent.position.y;
    }

    public void playFrameSound(AnimationComponent animationComponent){
        //animationComponent.currentFrameIndex
    }
}
