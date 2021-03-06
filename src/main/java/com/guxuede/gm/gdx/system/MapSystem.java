package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class MapSystem extends EntitySystem {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    public MapSystem(String mapFileName,SpriteBatch spriteBatch,OrthographicCamera camera){
        this.camera = camera;
        tiledMap = new TmxMapLoader().load(mapFileName);
        renderer = new OrthogonalTiledMapRenderer(tiledMap,spriteBatch);
        this.spriteBatch = spriteBatch;
    }

    public TiledMapTileLayer getTiledMapLayer(int layer) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(layer);
    }

    @Override
    public void update(float deltaTime) {
        renderer.setView(camera);
    }
}
