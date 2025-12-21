package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HighAttribute extends Attribute {

    public static final HighAttribute INSTANCE = new HighAttribute();

    private float original;

    @Override
    public String getName() {
        return "HIGH";
    }

    @Override
    public void init(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        original = positionComponent.high;
    }

    @Override
    public void plus(Entity entity, float v) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.high = positionComponent.high + v;
        log.info("plus " + positionComponent.high);
    }


    @Override
    public void reset(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.high = original;
    }

}
