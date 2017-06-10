package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class MapRenderingSystem extends EntitySystem {

    private int[] layers = new int[]{0,1};
    MapSystem mapSystem;

    public MapRenderingSystem(int priority , MapSystem mapSystem, int[] layers){
        this.mapSystem = mapSystem;
        this.priority = priority;
        this.layers = layers;
    }

    @Override
    public void update(float deltaTime) {
       // mapSystem.renderer.render(layers);
        mapSystem.renderer.render(new int[]{0});
        mapSystem.renderer.render(new int[]{1});
    }
}
