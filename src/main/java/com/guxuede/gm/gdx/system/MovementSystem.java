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

package com.guxuede.gm.gdx.system;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

import static com.guxuede.gm.gdx.Mappers.actorStateCM;

public class MovementSystem extends IteratingSystem {

    public static final float MOVE_VELOCITY = 20;

    private Vector2 tmp = new Vector2();

    public MovementSystem() {
        super(Family.all(ActorStateComponent.class, PositionComponent.class).get());
        this.priority = 1;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        ActorStateComponent actorStateComponent = actorStateCM.get(entity);
        actorStateComponent.velocity.x = actorStateComponent.acceleration.x / 10.0f * MOVE_VELOCITY;
        actorStateComponent.velocity.y = actorStateComponent.acceleration.y / 10.0f * MOVE_VELOCITY;
        tmp.set(actorStateComponent.velocity).scl(deltaTime);

        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        positionComponent.position.add(tmp);

        actorStateComponent.isMoving = tmp.x != 0 || tmp.y!=0;
        if (actorStateComponent.isMoving){
            positionComponent.degrees = tmp.angle();
            actorStateComponent.direction = convertDegreesToDirection(positionComponent.degrees);
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
