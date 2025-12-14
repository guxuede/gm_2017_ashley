package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.guxuede.gm.gdx.component.TiledMapDataComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class TiledMapManagerSystem extends IteratingSystem {
    private static final Family family = Family.all(TiledMapDataComponent.class).get();

    private SpriteBatch spriteBatch;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;
    public TiledMapDataComponent currentTiledMapDataComponent;
    public Entity currentTiledMapEntity;
    public String currentMapName;

    public TiledMapManagerSystem(SpriteBatch spriteBatch, OrthographicCamera camera){
        super(family);
        this.camera = camera;
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(renderer!=null){
            renderer.setView(camera);
        }
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        TiledMapDataComponent tiledMapDataComponent = Mappers.tiledMapDataCM.get(entity);
        if(!StringUtils.equals(tiledMapDataComponent.mapName,currentMapName)){
            if(renderer!=null){
                renderer.dispose();
            }
            currentTiledMapEntity = entity;
            currentMapName = tiledMapDataComponent.mapName;
            currentTiledMapDataComponent = tiledMapDataComponent;
            renderer = new OrthogonalTiledMapRenderer(tiledMapDataComponent.tiledMap,spriteBatch);
            System.out.println("renderer new map" + currentMapName);
        }
    }
}
