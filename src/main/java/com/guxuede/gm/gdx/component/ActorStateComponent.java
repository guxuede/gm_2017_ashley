package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.component.state.ActorState;
import com.guxuede.gm.gdx.component.state.AttackState;
import com.guxuede.gm.gdx.component.state.StandState;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class ActorStateComponent implements Component, Pool.Poolable{

    public static final float DIRECTION_IN_DEGREES_DOWN = 270;

    public long id;
    public float directionInDegrees = DIRECTION_IN_DEGREES_DOWN;
    public float speed=100;
    public boolean isMoving;


    public boolean isEventAble = true;

    public final Vector2 velocity = new Vector2();//速度
    public final Vector2 acceleration = new Vector2();//加速度，要有加速度才能有速度

    public ActorState actorState = new StandState(0);

    public boolean handleInput(final Entity entity , final InputEvent event){
        if(isEventAble && actorState!=null){
            ActorState newState = actorState.handleInput(entity,event);
            return goingToNewState(entity,newState,event);
        }
        return false;
    }

    public boolean goingToNewState(final Entity entity , ActorState newState, InputEvent event){
        if(newState!=null){
            actorState.exit(entity);
            actorState = newState;
            actorState.enter(entity,event);
            return true;
        }
        return false;
    }

    public void setDirectionInDegrees(float directionInDegrees) {
        this.directionInDegrees = directionInDegrees;
    }

    @Override
    public void reset() {
        id = 0;
        directionInDegrees =0;
        speed=100;
        isMoving = false;
        isEventAble = true;


        velocity.set(0,0);
        acceleration.set(0,0);
        actorState = new AttackState(0);
    }
}
