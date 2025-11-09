package com.guxuede.gm.gdx.actions.damage;

import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.BloodComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class HealByAction extends RelativeTemporalAction {

    private float heal;

    public HealByAction(float heal, float duration) {
        setDuration(duration);
        this.heal = heal;
    }


    @Override
    protected void updateRelative(float percentDelta) {
        BloodComponent bloodComponent = Mappers.bloodCM.get(target);
        float delta = heal * percentDelta;
        bloodComponent.modify(delta);
    }
}