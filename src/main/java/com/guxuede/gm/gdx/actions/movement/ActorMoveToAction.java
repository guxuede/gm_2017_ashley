package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.TempObjects;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2016/7/14 .
 */
public abstract class ActorMoveToAction extends Acting {
    public static final int IS_ARRIVE_RADIO = 10;
    public ActorMoveListener actorMoveListener;
    private Vector2 oldPoint = new Vector2();

    @Override
    protected void begin() {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        oldPoint.set(positionComponent.position);
    }

    @Override
    protected boolean update(float delta) {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        if(!isArrive()){
            final Vector2 target = getTargetPoint();
            final Vector2 entryPos = positionComponent.position;
            Vector2 vector2 = TempObjects.temp0Vector2.set(entryPos).sub(target).nor().scl(50f);
            Mappers.actorStateCM.get(actor).acceleration.set(vector2);
            return false;
        }
        Mappers.actorStateCM.get(actor).acceleration.set(0,0);
        return true;
    }

    @Override
    protected void end() {
        super.end();
    }

    protected boolean isArrive() {
        final Vector2 target = getTargetPoint();
        if(target == null){
            return true;
        }else{
            PositionComponent positionComponent = Mappers.positionCM.get(actor);
            float dist = target.dst(positionComponent.position);
            return dist < IS_ARRIVE_RADIO;
        }
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
