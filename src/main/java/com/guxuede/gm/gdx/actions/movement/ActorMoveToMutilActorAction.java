package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guxuede on 2016/7/16 .
 */
public class ActorMoveToMutilActorAction extends ActorMoveToMutilAction{

    private Iterator<Entity> iterator;
    private Entity currentEntity;


    public ActorMoveToMutilActorAction(List<Entity> currentEntity) {
        this.iterator = currentEntity.listIterator();
    }

    @Override
    public boolean haveNextTarget() {
        return iterator.hasNext();
    }

    @Override
    public void moveToNextTarget() {
        currentEntity = iterator.next();
    }

    @Override
    public void onArrived() {
        if(actorMoveListener!=null){
            actorMoveListener.onArrived(null,currentEntity);
        }
    }

    @Override
    public Vector2 getCurrentTarget() {
        if(currentEntity!=null){
            return Mappers.positionCM.get(currentEntity).position;
        }
        return null;
    }
}
