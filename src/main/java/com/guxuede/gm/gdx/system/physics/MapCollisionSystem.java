package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.basic.libgdx.MapMask;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.component.*;
import com.guxuede.gm.gdx.system.render.TiledMapManagerSystem;

import java.util.Optional;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class MapCollisionSystem extends IteratingSystem {

    private static final Family family = Family.all(ActorStateComponent.class, PositionComponent.class, BoundsComponent.class).get();

    public MapCollisionSystem() {
        super(family);
        this.priority = 0;
    }

    @Override
    protected void processEntity(Entity e, float delta) {
        MapMask mapMask = Optional.ofNullable(getEngine().getSystem(TiledMapManagerSystem.class).currentTiledMapDataComponent).map(i->i.mapMask).orElse(null);

        final ActorStateComponent physics = Mappers.actorStateCM.get(e);
        final PositionComponent pos = Mappers.positionCM.get(e);
        final BoundsComponent bounds = Mappers.boundsCM.get(e);

        //  no math required here.
        if (physics.acceleration.x != 0 || physics.acceleration.y != 0) {
            //calculate predicts position
            Vector2 temp0Vector2 = TempObjects.temp0Vector2;
            SensorMovementSystem.getNextPositionDelta(delta, physics, temp0Vector2);//get next Position Delta
            temp0Vector2.add(pos.position);//get next position
            float px = temp0Vector2.x;
            float py = temp0Vector2.y;
            //check position is collided
            if ((physics.acceleration.x > 0 && collides(mapMask,px, py)) || (physics.acceleration.x < 0 && collides(mapMask,px, py))) {
                physics.acceleration.x = 0;
                physics.velocity.x = 0;
            }
            if ((physics.acceleration.y > 0 && collides(mapMask,px, py)) || (physics.acceleration.y < 0 && collides(mapMask,px, py))) {
                physics.acceleration.y = 0;
                physics.velocity.y = 0;
            }

        }
    }

    private boolean collides(MapMask mapMask, final float x, final float y) {
        if(mapMask == null){
            return false;
        }
        return mapMask.atScreen(x, y, true);
    }
}
