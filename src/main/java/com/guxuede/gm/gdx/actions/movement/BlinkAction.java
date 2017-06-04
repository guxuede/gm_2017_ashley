package com.guxuede.gm.gdx.actions.movement;

import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.actions.DelayAction;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class BlinkAction extends Action {

    @Override
    public boolean act(float delta) {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(300,300);
        return true;
    }
}
