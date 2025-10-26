package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorMoveToPointAction extends ActorMoveToAction {

    protected Vector2 targetPoint = new Vector2();

    public ActorMoveToPointAction(Vector2 targetPoint) {
        this.targetPoint.set(targetPoint);
    }

    public ActorMoveToPointAction(float targetX, float targetY) {
        this.targetPoint.set(targetX, targetY);
    }

    @Override
    public Vector2 getTargetPoint() {
        return targetPoint;
    }

//    @Override
//    protected boolean isArrive() {
//        boolean is= super.isArrive();
//        if(is){
//            onArrived();
//        }
//        return is;
//    }

    @Override
    public void onArrived() {
        if(actorMoveListener!=null){
            actorMoveListener.onArrived(getTargetPoint(),null);
        }
    }

}
