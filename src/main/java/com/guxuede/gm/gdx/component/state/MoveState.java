package com.guxuede.gm.gdx.component.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import static com.guxuede.gm.gdx.component.ActorStateComponent.*;

/**
 * Created by guxuede on 2016/6/15 .
 */
public class MoveState extends StandState {

    public MoveState(int direction){
        super(direction);
    }

    @Override
    public void enter(Entity entity, InputEvent event) {
        if(event.getType()== InputEvent.Type.touchDown){
//            entity.moveToPoint(event.getStageX(), event.getStageY());
//            this.direction = entity.direction;//修正direction，应该重构造方法里传入？
        }else{
            //entity.move(direction);
        }
    }

    @Override
    public ActorState handleInput(Entity entity, InputEvent event) {
        ActorState actorState = super.handleInput(entity, event);
        if(actorState!=null){
            return actorState;
        }
        Integer standCode = null;
        if(InputEvent.Type.keyUp == event.getType() && ( standCode = convertKeyToDirection(event.getKeyCode()))!=null){
            Integer moveDirection = getMovePressPressKeyDirection();
            //如果没有方向键被按住,那么角色停止
            if(moveDirection==null){
                return new StandState(standCode);
            }else if(moveDirection != direction){
                //如果有方向键被按住,且不一样的方向,那么重新走
                return new MoveState(moveDirection);
            }
        }

        return null;
    }

    /**
     * 去继续检查是否任然有方向键被按住
     * @return
     */
    public Integer getMovePressPressKeyDirection(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            return UP;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            return DOWN;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            return RIGHT;
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            return LEFT;
        }
        return null;
    }

    public Integer convertKeyToDirection(int keyCode){
        if (Input.Keys.RIGHT == keyCode){
            return RIGHT;
        }else if(Input.Keys.UP == keyCode){
            return UP;
        }else if(Input.Keys.DOWN == keyCode){
            return DOWN;
        }else if(Input.Keys.LEFT == keyCode){
            return LEFT;
        }
        return null;
    }
}
