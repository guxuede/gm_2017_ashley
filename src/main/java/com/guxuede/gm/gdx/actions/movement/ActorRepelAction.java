package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class ActorRepelAction extends TemporalAction {

    private float intensity;

    private final Vector2 facePosition = new Vector2();
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public ActorRepelAction(Vector2 facePosition, float intensity, float duration) {
        super(duration, Interpolation.bounceOut);
        this.facePosition.set(facePosition);
        this.intensity = intensity;
    }

    float x = 0;

    @Override
    protected void begin() {
        super.begin();
        Vector2 position = Mappers.positionCM.get(this.target).position;
        this.startX = position.x;
        this.startY = position.y;

        facePosition.sub(position).nor().scl(- intensity,-intensity);

        this.endX = position.x + facePosition.x;
        this.endY = position.y + facePosition.y;
    }

    @Override
    protected void update(float percent) {
        PositionComponent positionComponent = Mappers.positionCM.get(getActor());
        positionComponent.high  =  intensity* percent;


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
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(getActor());
        sourceOwnerPositionComponent.high  = 0;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
