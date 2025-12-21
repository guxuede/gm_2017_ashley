package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PositionYAttribute extends Attribute{

    public static final PositionYAttribute INSTANCE = new PositionYAttribute();

    private float y;

    @Override
    public String getName() {
        return "POSITION_Y";
    }

    @Override
    public void init(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        y = positionComponent.position.y;
    }

    @Override
    public void plus(Entity entity, float v) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.position.y = positionComponent.position.y + v;
        log.info("plus " + positionComponent.position.y);
    }


    @Override
    public void reset(Entity entity) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
//        positionComponent.position.y = y;
    }

}
