package com.guxuede.gm.gdx.actions.damage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.guxuede.gm.gdx.actions.A;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.BloodComponent;
import com.guxuede.gm.gdx.component.HurtComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import org.lwjgl.Sys;

import static com.guxuede.gm.gdx.system.physics.HurtSystem.addEffect1;

public class HealByAction extends RelativeTemporalAction {

    private float heal;

    private long lastEffectTime;

    public HealByAction(float heal, float duration) {
        setDuration(duration);
        this.heal = heal;
    }


    @Override
    protected void updateRelative(float percentDelta) {
        BloodComponent bloodComponent = Mappers.bloodCM.get(target);
        float delta = heal * percentDelta;
        bloodComponent.modify(delta);

        if(System.currentTimeMillis() - lastEffectTime> 100){
            lastEffectTime = System.currentTimeMillis();
            PositionComponent positionComponent = Mappers.positionCM.get(target);
            addEffect1(positionComponent.position.x + MathUtils.random(-50,50), positionComponent.position.y + MathUtils.random(-50,50),0, "1" , Color.GREEN);
        }
    }

}