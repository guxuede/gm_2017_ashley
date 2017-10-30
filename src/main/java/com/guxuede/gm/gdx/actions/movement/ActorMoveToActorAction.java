package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;

/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorMoveToActorAction extends ActorMoveToAction {

    protected Entity targetActor;

    public ActorMoveToActorAction(){

    }

    public ActorMoveToActorAction(Entity targetActor) {
        this.targetActor = targetActor;
    }

    @Override
    protected boolean isArrive() {
        boolean is= super.isArrive();
        if(is){
            onArrived();
        }
        return is;
    }

    @Override
    public void onArrived() {
        if(actorMoveListener!=null){
            actorMoveListener.onArrived(null,targetActor);
        }
    }

    @Override
    public Vector2 getTargetPoint() {
        if(targetActor!=null){
            return Mappers.positionCM.get(target).position;
        }
        return null;
    }

    public void setTargetActor(Entity targetActor) {
        this.targetActor = targetActor;
    }
}
