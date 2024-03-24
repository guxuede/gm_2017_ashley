package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.DynamicDirectionComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

/**
 * Created by guxuede on 2017/9/10 .
 */
public class DynamicDirectionSystem extends IteratingSystem {

    private static final Family family = Family.all(DynamicDirectionComponent.class,PositionComponent.class, PresentableComponent.class).get();

    public DynamicDirectionSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        presentableComponent.rotation = positionComponent.degrees + 90;
    }
}