package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.basic.libgdx.MapMask;

public class TiledMapDataComponent  implements Component, Pool.Poolable{

    public String mapName;
    public TiledMap tiledMap;
    public MapMask mapMask;

    public TiledMapDataComponent(String mapFileName) {
        mapName = mapFileName;
        this.tiledMap = new TmxMapLoader().load(mapFileName);
        Array<TiledMapTileLayer> layers = tiledMap.getLayers().getByType(TiledMapTileLayer.class);
        Integer width = tiledMap.getProperties().get("width", Integer.class);
        Integer height = tiledMap.getProperties().get("height", Integer.class);
        Integer tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        Integer tileHeight = tiledMap.getProperties().get("tileheight", Integer.class);
        mapMask = new MapMask(height, width, tileWidth, tileHeight, layers, "block");
    }

    @Override
    public void reset() {

    }
}
