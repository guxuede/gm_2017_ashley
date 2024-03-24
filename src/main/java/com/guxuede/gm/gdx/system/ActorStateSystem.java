package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.ActorComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * ActorComponent 与其他组件的连接
 * Created by guxuede on 2017/6/2 .
 */
public class ActorStateSystem extends IteratingSystem {


    private static final Family family = Family.all(ActorComponent.class,PositionComponent.class).get();

    public ActorStateSystem(){
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ActorComponent actorComponent = Mappers.actorCM.get(entity);
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        actorComponent.setPosition(positionComponent.position.x,positionComponent.position.y);
    }
}
