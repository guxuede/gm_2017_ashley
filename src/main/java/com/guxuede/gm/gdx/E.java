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

    public static E create()
    {
        return instance.createEntity();
    }
    public static E remove(Entity entity)
    {
        return instance.removeEntity(entity);
    }
    public static E edit(Entity e)
    {
        return instance.editEntity(e);
    }


}
