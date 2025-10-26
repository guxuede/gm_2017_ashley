package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.component.PositionComponent;

import static com.guxuede.gm.gdx.system.physics.SensorMovementSystem.getNextPositionDelta;
import static com.guxuede.gm.gdx.system.physics.SensorMovementSystem.updateMovement;

/**
 * Created by guxuede on 2016/7/14 .
 */
public abstract class ActorMoveToAction extends Acting {
    public static final float IS_ARRIVE_RADIO = 0.1f;
    public ActorMoveListener actorMoveListener;
    private Vector2 oldPoint = new Vector2();


    private static final Vector2 temp0Vector2 = new Vector2();

    @Override
    protected void begin() {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        oldPoint.set(positionComponent.position);
    }

    @Override
    protected boolean update(float delta) {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);

        final Vector2 position = positionComponent.position;
        final Vector2 target = getTargetPoint();

        Vector2 vector2 = temp0Vector2.set(target).sub(position).nor();//acceleration
        actorStateComponent.acceleration.set(vector2);
        getNextPositionDelta(delta,actorStateComponent,temp0Vector2);

        Vector2 nextPosition = temp0Vector2.add(position);
        updateMovement(actorStateComponent, positionComponent, nextPosition.x ,nextPosition.y);


        temp0Vector2.set(target).sub(position).nor();//recalculate next time's acceleration

        //if current new position's acceleration is same with previous, ok, we are not arrival
        if(temp0Vector2.hasSameDirection(actorStateComponent.acceleration)){
            return false;
        }else{
            actorStateComponent.acceleration.set(0,0);
            return true;
        }
    }

    @Override
    protected void end() {
        super.end();
    }


    public void onArrived(){

    }


    public abstract Vector2 getTargetPoint();

    public static interface ActorMoveListener{
        void onArrived(Vector2 target, Entity actor);
    }

    @Override
    public void reset() {
        super.reset();
        actorMoveListener = null;
        oldPoint.set(0,0);
    }
}
