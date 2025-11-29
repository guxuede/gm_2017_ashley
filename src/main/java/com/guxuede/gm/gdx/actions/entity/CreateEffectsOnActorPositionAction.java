package com.guxuede.gm.gdx.actions.entity;

import com.guxuede.gm.gdx.actions.A;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/**
 * 创建特效在当前位置
 * Created by guxuede on 2017/4/4 .
 */
public class CreateEffectsOnActorPositionAction extends Acting {

    public String effectName;


    @Override
    public boolean update(float delta) {
        Mappers.actionCM.get(actor).addAction(actor, A.effectsActorAction(effectName,Mappers.positionCM.get(actor).position));
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        effectName = null;
    }


    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }
}
