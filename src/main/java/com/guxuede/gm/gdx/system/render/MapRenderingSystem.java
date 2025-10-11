package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

/**
 * 使用它画第一层地板第二层人物的效果
 *         engine.addSystem(new MapRenderingSystem(100,mapSystem,new int[]{0}));//优先级越低，先画到最底部
            engine.addSystem(new MapRenderingSystem(101,mapSystem,new int[]{1}));
            engine.addSystem(new MapRenderingSystem(400,mapSystem,new int[]{2}));
    但是engine不支持增加相同类型的system。
    2017-10-20 可以使用内部類的方式
 * Created by guxuede on 2017/6/10 .
 */
public class MapRenderingSystem extends EntitySystem {

    private int[] layers = new int[]{0,1};
    MapSystem mapSystem;

    public MapRenderingSystem(int priority, int[] layers){
        this.priority = priority;
        this.layers = layers;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        mapSystem = getEngine().getSystem(MapSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        mapSystem.renderer.render(layers);
    }
}
