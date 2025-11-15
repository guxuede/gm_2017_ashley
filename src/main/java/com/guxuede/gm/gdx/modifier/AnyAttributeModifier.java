package com.guxuede.gm.gdx.modifier;

import com.badlogic.ashley.core.Entity;

public class AnyAttributeModifier implements AttributeModifier{

    public float number;

    @Override
    public void beforeApply(Entity entity) {

    }

    @Override
    public float getNumber(Entity entity) {
        float a = number;
        number = 0;
        return a;
    }

    public void setNumber(float number) {
        this.number = number;
    }
}
