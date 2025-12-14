package com.guxuede.gm.gdx.actions.buff;

import com.badlogic.gdx.math.Interpolation;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.modifier.AnyAttributeModifier;
import com.guxuede.gm.gdx.modifier.AttributeModifier;

public class TemporalActionAttributeDeltaModifierAction extends RelativeTemporalAction {

    private float delta;

    private AttributeModifier attributeModifier;


    public TemporalActionAttributeDeltaModifierAction(AnyAttributeModifier attributeModifier, float delta, float duration, Interpolation interpolation) {
        setDuration(duration);
        setInterpolation(interpolation);
        this.delta = delta;
        this.attributeModifier = attributeModifier;
    }

    @Override
    protected void updateRelative(float percentDelta) {
        float number = delta * percentDelta;
        attributeModifier.setNumber(number);
    }


}
