package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
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
    public static final int         //实体状态
            LIFE_STATUS_CREATE=0x01, //实体处于创建创建状态，还没有进入世界，系统将不久后初始化它，并进入LIFE_STATUS_BORN
            LIFE_STATUS_BORN=0x02,//实体处于诞生状态，进入世界，诞生状态的实体，无敌不可攻击，并有诞生动画，诞生完成后进入LIFE_STATUS_LIVE
            LIFE_STATUS_LIVE=0x04,//实体处于正常活动状态，参与世界的任何事件，可以被控制，可以被攻击
            LIFE_STATUS_DEAD=0x08,//实体处于死亡状态，无敌不可攻击不可控制，并有死亡动画，且有尸体消亡动画,死亡完成后进入LIFE_STATUS_DESTORY
            LIFE_STATUS_DESTROY =0x10;//实体处于摧毁状态，退出世界之外，系统将不久销毁它，内存空间将清理

    public static final int DIRECTION_IN_DEGREES_DOWN = 270;


    public long id;
    public float directionInDegrees = DIRECTION_IN_DEGREES_DOWN;
    public float speed=100;
    public boolean isMoving;
    public float visualRadius=100;
    public Color primaryColor;
    public int lifeStatus = LIFE_STATUS_CREATE;
    public boolean isEventAble = true;
    public boolean isSensor = false;
    public boolean isHover = false;
    public float collisionSize = 0;
    public boolean hasShadow = true;


    public float bounce = 0;
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
        visualRadius=100;
        lifeStatus = LIFE_STATUS_CREATE;
        isEventAble = true;
        isSensor = false;
        isHover = false;
        collisionSize = 0;
        hasShadow = true;


        bounce = 0;
        velocity.set(0,0);
        acceleration.set(0,0);
        actorState = new AttackState(0);
    }
}
