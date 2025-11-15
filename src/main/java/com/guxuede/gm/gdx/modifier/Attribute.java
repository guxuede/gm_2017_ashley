package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;

import java.util.Objects;

public abstract class Attribute{

    public abstract String getName();

    public abstract void init(Entity entity);

    public abstract void plus(Entity entity, float t);
    public abstract void reset(Entity entity);



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HighAttribute that = (HighAttribute) o;
        return Objects.equals(this.getName(),that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}
