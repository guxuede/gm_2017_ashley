package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.guxuede.gm.gdx.component.TiledMapDataComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/**
 * 使用它画第一层地板第二层人物的效果
 *         engine.addSystem(new MapRenderingSystem(100,mapSystem,new int[]{0}));//优先级越低，先画到最底部
            engine.addSystem(new MapRenderingSystem(101,mapSystem,new int[]{1}));
            engine.addSystem(new MapRenderingSystem(400,mapSystem,new int[]{2}));
    但是engine不支持增加相同类型的system。
    2017-10-20 可以使用内部類的方式
 * Created by guxuede on 2017/6/10 .
 */
public class TiledMapRenderingSystem extends EntitySystem {

    private final int[] layers;

    public TiledMapRenderingSystem(int priority, int[] layers){
        this.priority = priority;
        this.layers = layers;
    }

    @Override
    public void update(float deltaTime) {
        TiledMapManagerSystem tiledMapManagerSystem = getEngine().getSystem(TiledMapManagerSystem.class);
        if(tiledMapManagerSystem.renderer!=null){
            tiledMapManagerSystem.renderer.render(layers);
        }
    }

}
