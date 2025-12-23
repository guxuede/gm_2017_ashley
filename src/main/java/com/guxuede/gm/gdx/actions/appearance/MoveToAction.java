//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guxuede.gm.gdx.actions.appearance;

import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.system.physics.SensorMovementSystem;

public class MoveToAction extends TemporalAction {
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public MoveToAction(float duration, float endX, float endY) {
        super(duration);
        this.endX = endX;
        this.endY = endY;
    }

    protected void begin() {
        Vector2 position = Mappers.positionCM.get(this.target).position;
        this.startX = position.x;
        this.startY = position.y;
    }

    protected void update(float percent) {
        ActorStateComponent stateComponent = Mappers.actorStateCM.get(this.target);
        float x;
        float y;
        if (percent == 0.0F) {
            x = this.startX;
            y = this.startY;
        } else if (percent == 1.0F) {
            x = this.endX;
            y = this.endY;
        } else {
            x = this.startX + (this.endX - this.startX) * percent;
            y = this.startY + (this.endY - this.startY) * percent;
        }
        PositionComponent positionComponent = Mappers.positionCM.get(this.target);
        SensorMovementSystem.updateMovement(stateComponent, positionComponent, x, y);
    }

    @Override
    protected void end() {
        ActorStateComponent stateComponent = Mappers.actorStateCM.get(this.target);
        stateComponent.isMoving = false;
    }

    public void reset() {
        super.reset();
    }


    public float getX() {
        return this.endX;
    }

    public void setX(float x) {
        this.endX = x;
    }

    public float getY() {
        return this.endY;
    }

    public void setY(float y) {
        this.endY = y;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

}
