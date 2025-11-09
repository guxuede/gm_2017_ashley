package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class ActorRepelAction extends TemporalAction {


    private final Vector2 facePosition = new Vector2();

    private float far;

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    private float startHigh;
    private final float endHigh;


    public ActorRepelAction(Vector2 facePosition, float far, float high, float duration, Interpolation interpolation) {
        super(duration, interpolation);
        this.facePosition.set(facePosition);
        this.far = far;
        this.endHigh = high;
    }


    @Override
    protected void begin() {
        super.begin();
        PositionComponent positionComponent = Mappers.positionCM.get(this.target);
        Vector2 position = positionComponent.position;
        this.startX = position.x;
        this.startY = position.y;

        facePosition.sub(position).nor().scl(-far,-far);

        this.endX = position.x + facePosition.x;
        this.endY = position.y + facePosition.y;

        this.startHigh = positionComponent.high;
    }

    @Override
    protected void update(float percent) {
        PositionComponent positionComponent = Mappers.positionCM.get(getActor());
        updateHigh(percent, positionComponent);
        updatePosition(percent, positionComponent);
    }

    private void updateHigh(float percent, PositionComponent positionComponent) {
        positionComponent.high  =  this.startHigh + (this.endHigh - this.startHigh) * percent;
    }

    private void updatePosition(float percent, PositionComponent positionComponent) {
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
        positionComponent.position.set(x, y);
    }

    @Override
    protected void end() {
        super.end();
    }

    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        this.far = far;
    }
}
