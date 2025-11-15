package com.guxuede.gm.gdx.actions.buff;

import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.modifier.Attribute;
import com.guxuede.gm.gdx.modifier.AttributeModifier;

public class RemoveAttributeModifierAction extends Acting {

    private AttributeModifier attributeModifier;
    private Attribute attribute;

    public RemoveAttributeModifierAction(Attribute attribute, AttributeModifier attributeModifier) {
        this.attributeModifier = attributeModifier;
        this.attribute = attribute;
    }


    @Override
    protected boolean update(float delta) {
        Mappers.attrModCM.get(this.target).removeHighAttributeModifier(this.target, attribute, attributeModifier);
        return true;
    }
}
