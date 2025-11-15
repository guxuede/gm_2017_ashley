package com.guxuede.gm.gdx.actions.buff;

import com.badlogic.gdx.math.Interpolation;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.modifier.AttributeModifier;
import com.guxuede.gm.gdx.modifier.AnyAttributeModifier;

public class TemporalActionAttributeModifierAction extends TemporalAction {

    private float start;
    private float end;

    private AttributeModifier attributeModifier;


    public TemporalActionAttributeModifierAction(AnyAttributeModifier attributeModifier, float start, float end, float duration, Interpolation interpolation) {
        super(duration, interpolation);
        this.start = start;
        this.end = end;
        this.attributeModifier = attributeModifier;
    }

    @Override
    protected void update(float percent) {
        float number = this.start + (this.end - this.start) * percent;
        attributeModifier.setNumber(number);
    }


}
