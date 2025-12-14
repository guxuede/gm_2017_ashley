package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.guxuede.gm.gdx.component.ai.AiComponent;

public class AiSystem extends IntervalIteratingSystem {
    private static final Family family = Family.all(AiComponent.class).get();

    public AiSystem(float interval) {
        super(family, interval);
    }

    @Override
    protected void processEntity(Entity entity) {
//        Mappers.aiCM.get(entity).behaviorTree.step();
    }
}
