package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;


/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorBrandishedByAction extends TemporalAction {
    protected Vector2 targetPoint = new Vector2();
    public float fromBrandishedAngleInRadians;
    public float brandishedAngleInRadians;

    public Entity sourceOwner;
    private static final Vector2 temp0Vector2 = new Vector2();


    public ActorBrandishedByAction(float duration, float brandishedAngleInRadians, Entity sourceOwner, Vector2 targetPoint) {
        super(duration);
        this.sourceOwner = sourceOwner;
        this.targetPoint.set(targetPoint);
        this.brandishedAngleInRadians = brandishedAngleInRadians;
    }

    float r = 50;

    @Override
    protected void begin() {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);

        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(sourceOwnerPositionComponent.position);

        temp0Vector2.set(targetPoint).sub(positionComponent.position).nor();
        fromBrandishedAngleInRadians = temp0Vector2.angleRad() + brandishedAngleInRadians/2;
    }



    @Override
    protected void update(float percent) {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);
        Vector2 sourceOwnerPosition = sourceOwnerPositionComponent.position;

        PositionComponent positionComponent = Mappers.positionCM.get(actor);

        float angleInRadians =fromBrandishedAngleInRadians - brandishedAngleInRadians* percent;


        float x = (float) (sourceOwnerPosition.x + r*Math.cos(angleInRadians));
        float y = (float) (sourceOwnerPosition.y + r*Math.sin(angleInRadians));


        positionComponent.position.set(x, y);

        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);

        temp0Vector2.set(x,y).sub(sourceOwnerPosition).nor();
        presentableComponent.rotation = temp0Vector2.angle() - 45;//45 is the texture angle
    }

    @Override
    protected void end() {
        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);
        super.end();
    }


    public void onArrived(){

    }

    @Override
    public void reset() {
        super.reset();
    }
}
