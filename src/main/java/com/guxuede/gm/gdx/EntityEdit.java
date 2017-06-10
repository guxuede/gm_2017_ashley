package com.guxuede.gm.gdx;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.component.*;

public final class EntityEdit {

    protected PooledEngine engine;

    EntityEdit(PooledEngine engine) {
        this.engine = engine;
    }


    public <T extends Component> T create(Class<T> componentKlazz) {
        return engine.createComponent(componentKlazz);
    }

//
//    /**
//     * Removes the component from this entity.
//     *
//     * @param component the component to remove from this entity.
//     * @return this EntityEdit for chaining
//     */
//    public EntityEdit remove(Component component) {
//        return remove(component.getClass());
//    }
//
//    /**
//     * Remove component by its type.
//     *
//     * @param type the class type of component to remove from this entity
//     * @return this EntityEdit for chaining
//     */
//    public EntityEdit remove(Class<? extends Component> type) {
//        entity.remove(type);
//        return this;
//    }


}
