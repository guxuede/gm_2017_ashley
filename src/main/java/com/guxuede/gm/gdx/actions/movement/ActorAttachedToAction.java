package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;


/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorAttachedToAction extends TemporalAction {

    public Entity sourceOwner;
    private static final Vector2 temp0Vector2 = new Vector2();


    public ActorAttachedToAction(float duration, Entity sourceOwner) {
        super(duration);
        this.sourceOwner = sourceOwner;
    }


    @Override
    protected void begin() {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);

        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(sourceOwnerPositionComponent.position);
    }



    @Override
    protected void update(float percent) {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);
        Vector2 sourceOwnerPosition = sourceOwnerPositionComponent.position;

        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(sourceOwnerPosition);
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    public void reset() {
        super.reset();
    }
}
