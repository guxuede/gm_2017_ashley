package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.modifier.Attribute;
import com.guxuede.gm.gdx.modifier.AttributeModifier;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guxuede on 2017/5/29 .
 */

@Slf4j
public class AttributeModifferComponent implements Component {

    public final Map<Attribute,Array<AttributeModifier>> attributeModifiers = new HashMap<>();

    public void addHighAttributeModifier(Entity entity, Attribute attribute , AttributeModifier attributeModifier){
        if(attributeModifiers.containsKey(attribute)){
            attributeModifiers.get(attribute).add(attributeModifier);
        }else{
            Attribute attr = null;
            try {
                attr = attribute.getClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            attr.init(entity);
            Array<AttributeModifier> array = new Array<>();
            array.add(attributeModifier);
            attributeModifiers.put(attribute, array);
        }
        log.info("add "+attributeModifiers);
    }


    public void removeHighAttributeModifier(Entity entity, Attribute attribute, AttributeModifier attributeModifier){
        if(attributeModifiers.containsKey(attribute)){
            attributeModifiers.get(attribute).removeValue(attributeModifier, true);
        }
        log.info("remove "+attributeModifiers);
    }

}
