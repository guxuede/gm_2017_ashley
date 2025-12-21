package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PositionXAttribute extends Attribute {

    public static final PositionXAttribute INSTANCE = new PositionXAttribute();

    private float x;

    @Override
    public String getName() {
        return "POSITION_X";
    }

    @Override
    public void init(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        x = positionComponent.position.x;
    }


    @Override
    public void plus(Entity entity, float v) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.position.x = positionComponent.position.x + v;
        log.info("plus " + positionComponent.position.x);
    }


    @Override
    public void reset(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
//        positionComponent.position.x = x;
    }

}
