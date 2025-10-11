package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.component.ActorStateComponent;

public class EntityCollisionSystem extends IteratingSystem {

    private static final Family family = Family.all(ActorStateComponent.class).get();

    public EntityCollisionSystem(Family family) {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }
}
