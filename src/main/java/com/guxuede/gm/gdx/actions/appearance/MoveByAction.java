//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guxuede.gm.gdx.actions.appearance;

import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class MoveByAction extends RelativeTemporalAction {
    private float amountX;
    private float amountY;

    public MoveByAction() {
    }

    public MoveByAction(float duration, float amountX, float amountY) {
        this.setDuration(duration);
        this.amountX = amountX;
        this.amountY = amountY;
    }


    @Override
    protected void updateRelative(float percentDelta) {
        PositionComponent positionComponent = Mappers.positionCM.get(this.target);
        positionComponent.position.x += amountX * percentDelta;
        positionComponent.position.y += amountY * percentDelta;
    }

    public float getAmountX() {
        return amountX;
    }

    public void setAmountX(float amountX) {
        this.amountX = amountX;
    }

    public float getAmountY() {
        return amountY;
    }

    public void setAmountY(float amountY) {
        this.amountY = amountY;
    }

    public void reset() {
        super.reset();
    }




}
