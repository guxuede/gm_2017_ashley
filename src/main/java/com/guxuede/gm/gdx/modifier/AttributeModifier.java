package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;

public interface AttributeModifier {

    public void beforeApply(Entity entity);

    public float getNumber(Entity entity);
    public void setNumber(float number);
}
