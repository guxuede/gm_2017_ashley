package com.guxuede.gm.gdx.actions.movement.bysensor;

import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorMoveToAction extends Acting {
    private final Vector2 targetPoint = new Vector2();

    private final Vector2 previousPostion = new Vector2();//记录上次位置,用来判断是不是碰撞的墙而停止



    private static final Vector2 temp0Vector2 = new Vector2();

    public ActorMoveToAction(float x, float y) {
        targetPoint.set(x,y);
    }

    @Override
    protected void begin() {
    }

    @Override
    protected boolean update(float delta) {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);

        Vector2 position = positionComponent.position;

        if(position.dst(targetPoint) <5){
            actorStateComponent.acceleration.set(0,0);//arrived. ok
            return true;
        }
        if(previousPostion.dst(position) == 0){//Collision!. stop
            return true;
        }

        previousPostion.set(position);

        temp0Vector2.set(targetPoint).sub(position).nor();
        if(temp0Vector2.x > 0.1f){
            temp0Vector2.x = 1;
        }else if (temp0Vector2.x < -0.1f){
            temp0Vector2.x = -1;
        }else{
            temp0Vector2.x = 0;
        }

        if(temp0Vector2.y > 0.1f){
            temp0Vector2.y = 1;
        }else if (temp0Vector2.y < -0.1f){
            temp0Vector2.y = -1;
        }else{
            temp0Vector2.y = 0;
        }
        actorStateComponent.acceleration.set(temp0Vector2);

        return false;
    }

    @Override
    protected void end() {
        super.end();
    }



    @Override
    public void reset() {
        super.reset();
        targetPoint.set(0,0);
    }
}
