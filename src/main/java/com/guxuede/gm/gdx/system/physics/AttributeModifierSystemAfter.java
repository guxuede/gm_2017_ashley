package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.component.AttributeModifferComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.modifier.Attribute;
import com.guxuede.gm.gdx.modifier.AttributeModifier;

import java.util.Map;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class AttributeModifierSystemAfter extends IteratingSystem {

    private static final Family family = Family.all(AttributeModifferComponent.class).get();

    public AttributeModifierSystemAfter(int priority){
        super(family);
        this.priority = priority;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttributeModifferComponent actionsComponent = Mappers.attrModCM.get(entity);
        Map<Attribute, Array<AttributeModifier>> attributeArrayMap = actionsComponent.attributeModifiers;
        attributeArrayMap.keySet().forEach(attribute->{
            attribute.reset(entity);
//            Array<AttributeModifier> attributeModifiers = attributeArrayMap.get(attribute);
//            if(attributeModifiers.isEmpty()){
//                attributeArrayMap.remove(attribute);
//            }
        });
    }


}
