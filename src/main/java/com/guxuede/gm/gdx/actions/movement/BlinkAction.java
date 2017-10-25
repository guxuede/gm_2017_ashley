package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class BlinkAction extends Action {

    private Vector2 targetPosition = new Vector2();

    public BlinkAction(){

    }

    public BlinkAction(Vector2 targetPositon){
        this.targetPosition.set(targetPositon);
    }

    @Override
    public boolean act(float delta) {
        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(this.targetPosition);
        return true;
    }

    public void setTargetPosition(Vector2 targetPosition) {
        this.targetPosition = targetPosition;
    }
}
