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
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.net.component.PlayerDataComponent;

import static com.guxuede.gm.gdx.entityEdit.Mappers.actorStateCM;

public class SensorMovementSystem extends IteratingSystem {

    private final Vector2 temp = new Vector2();

    public SensorMovementSystem() {
        super(Family.all(ActorStateComponent.class, PositionComponent.class, PlayerDataComponent.class).get());
        this.priority = 1;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        ActorStateComponent actorStateComponent = actorStateCM.get(entity);
        getNextPositionDelta(deltaTime, actorStateComponent, temp);
        PositionComponent positionComponent = Mappers.positionCM.get(entity);

        Vector2 nextPosition = temp.add(positionComponent.position);

        updateMovement(actorStateComponent, positionComponent, nextPosition.x ,nextPosition.y);
    }

    public static void getNextPositionDelta(float deltaTime, ActorStateComponent actorStateComponent, Vector2 out) {
        actorStateComponent.velocity.x = actorStateComponent.acceleration.x * actorStateComponent.speed;
        actorStateComponent.velocity.y = actorStateComponent.acceleration.y * actorStateComponent.speed;

        //速度改变位置
        out.set(actorStateComponent.velocity).scl(deltaTime);

        if(Math.abs(actorStateComponent.velocity.x) <  0.1){
            actorStateComponent.velocity.x = 0;
        }
        if(Math.abs(actorStateComponent.velocity.y) < 0.1){
            actorStateComponent.velocity.y = 0;
        }
    }


    public static void updateMovement(ActorStateComponent stateComponent, PositionComponent positionComponent, float nextPositionX, float nextPositionY){
        TempObjects.temp0Vector2.set(nextPositionX,nextPositionY).sub(positionComponent.position).nor();
        positionComponent.position.set(nextPositionX, nextPositionY);

        float angle = TempObjects.temp0Vector2.angle();
        stateComponent.isMoving = !TempObjects.temp0Vector2.isZero();
        if(stateComponent.isMoving) {
            stateComponent.directionInDegrees = angle;
        }
    }

}
