/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.guxuede.gm.gdx.system.physics;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

import static com.guxuede.gm.gdx.entityEdit.Mappers.actorStateCM;

public class MovementSystem extends IteratingSystem {

    //摩擦力
    public static final float friction = 0.95f;

    private Vector2 tmp = new Vector2();

    public MovementSystem() {
        super(Family.all(ActorStateComponent.class, PositionComponent.class).get());
        this.priority = 1;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        ActorStateComponent actorStateComponent = actorStateCM.get(entity);
        getNextPositionDelta(deltaTime, actorStateComponent, tmp);
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.position.add(tmp);

        //更新方向信息
        actorStateComponent.isMoving = tmp.x != 0 || tmp.y!=0;
        if (actorStateComponent.isMoving){
            positionComponent.degrees = tmp.angle();
            actorStateComponent.direction = convertDegreesToDirection(tmp.angle());
        }

    }

    public static void getNextPositionDelta(float deltaTime, ActorStateComponent actorStateComponent, Vector2 out) {
        //加速度驱使速度
        actorStateComponent.velocity.x = actorStateComponent.acceleration.x;
        actorStateComponent.velocity.y = actorStateComponent.acceleration.y;

        //debug
//        if(actorStateComponent.velocity.x!=0 || actorStateComponent.velocity.y!=0){
//            System.out.println(actorStateComponent.velocity);
//        }
        //速度改变位置
        out.set(actorStateComponent.velocity).scl(deltaTime);
        //S=v+1/2*a*t^2
        //摩擦力减少速度? 不知道为什么 要很长时间才能把速度减为0
//        if (actorStateComponent.velocity.x != 0 && actorStateComponent.acceleration.x == 0) {
//            float prev =  actorStateComponent.velocity.x;
//            float after = prev + (prev > 0? -1:1) * (20 * deltaTime);//20 减速度
//            if(Math.abs(after) < 1){//如果速度已经将为1,那么就停止
//                after = 0;
//            }
//            actorStateComponent.velocity.x = after;
//        }
//        if (actorStateComponent.velocity.y != 0 && actorStateComponent.acceleration.y == 0) {
//            float prev =  actorStateComponent.velocity.y;
//            float after = prev + (prev > 0? -1:1) * (20 * deltaTime);
//            if(Math.abs(after) < 1){
//                after = 0;
//            }
//            actorStateComponent.velocity.y = after;
//        }
//        actorStateComponent.velocity.x = actorStateComponent.velocity.x* friction;
//        actorStateComponent.velocity.y = actorStateComponent.velocity.y* friction;

        if(Math.abs(actorStateComponent.velocity.x) <  0.1){
            actorStateComponent.velocity.x = 0;
        }
        if(Math.abs(actorStateComponent.velocity.y) < 0.1){
            actorStateComponent.velocity.y = 0;
        }
    }


    public int convertDegreesToDirection(float degrees){
        int direction = 0;
        if(degrees >= 45 && degrees < 135){
            direction = ActorStateComponent.UP;
        }else if(degrees >= 135 && degrees < 225){
            direction = ActorStateComponent.LEFT;
        }else if(degrees >= 225 && degrees < 325){
            direction = ActorStateComponent.DOWN;
        }else if(degrees >= 325 || degrees < 45){
            direction = ActorStateComponent.RIGHT;
        }
        return direction;
    }

}
