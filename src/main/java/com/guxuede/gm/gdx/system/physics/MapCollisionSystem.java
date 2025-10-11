package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.basic.libgdx.MapMask;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.component.*;
import com.guxuede.gm.gdx.system.render.MapSystem;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class MapCollisionSystem extends IteratingSystem {

    private static final Family family = Family.all(ActorStateComponent.class, PositionComponent.class, BoundsComponent.class).get();

    private MapMask mapMask;

    public MapCollisionSystem() {
        super(family);
        this.priority = 0;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        TiledMap map = engine.getSystem(MapSystem.class).tiledMap;
        Array<TiledMapTileLayer> layers = map.getLayers().getByType(TiledMapTileLayer.class);
        Integer width = map.getProperties().get("width", Integer.class);
        Integer height = map.getProperties().get("height", Integer.class);
        Integer tileWidth = map.getProperties().get("tilewidth", Integer.class);
        Integer tileHeight = map.getProperties().get("tileheight", Integer.class);
        mapMask = new MapMask(height, width, tileWidth, tileHeight, layers, "block");
    }

    @Override
    protected void processEntity(Entity e, float delta) {
        final ActorStateComponent physics = Mappers.actorStateCM.get(e);
        final PositionComponent pos = Mappers.positionCM.get(e);
        final BoundsComponent bounds = Mappers.boundsCM.get(e);

        //  no math required here.
        if (physics.acceleration.x != 0 || physics.acceleration.y != 0) {
            //calculate predicts position
            Vector2 temp0Vector2 = TempObjects.temp0Vector2;
            MovementSystem.getNextPositionDelta(delta, physics, temp0Vector2);//get next Position Delta
            temp0Vector2.add(pos.position);//get next position
            float px = temp0Vector2.x;
            float py = temp0Vector2.y;
            //check position is collided
            if ((physics.acceleration.x > 0 && collides(px + bounds.maxx / 2, py)) || (physics.acceleration.x < 0 && collides(px - bounds.maxx / 2, py))) {
                physics.acceleration.x = physics.acceleration.x > 0?-1:1;
                physics.velocity.x = 0;
            }
            if ((physics.acceleration.y > 0 && collides(px, py + bounds.maxy / 2)) || (physics.acceleration.y < 0 && collides(px, py - bounds.maxy / 2))) {
                physics.acceleration.y = physics.acceleration.y > 0?-1:1;
                physics.velocity.y = 0;
            }

        }
    }

    private boolean collides(final float x, final float y) {
        return mapMask.atScreen(x, y, true);
    }
}
