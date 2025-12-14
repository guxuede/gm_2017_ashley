package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.DynamicDirectionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

/**
 * Created by guxuede on 2017/9/10 .
 */
public class DynamicDirectionSystem extends IteratingSystem {

    private static final Family family = Family.all(DynamicDirectionComponent.class, ActorStateComponent.class, PresentableComponent.class).get();

    public DynamicDirectionSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        presentableComponent.rotation = actorStateComponent.directionInDegrees + 90;
    }
}