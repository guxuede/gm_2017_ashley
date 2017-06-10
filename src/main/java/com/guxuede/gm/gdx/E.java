package com.guxuede.gm.gdx;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

/**
 * Local entity editor. Does not support multiple worlds.
 *
 * @author Daan van Yperen
 */
public class E extends EntityEditor<E> {

    private E() {

    }

    private static final E instance=new E();

    public static E create(PooledEngine engine)
    {
        return instance.createEntity(engine);
    }

    public static E edit(PooledEngine engine,Entity e)
    {
        return instance.editEntity(engine,e);
    }

}
