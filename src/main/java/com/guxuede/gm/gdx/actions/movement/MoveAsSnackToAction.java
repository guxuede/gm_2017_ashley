//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.system.physics.SensorMovementSystem;

public class MoveAsSnackToAction extends TemporalAction {
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    float percent = 0.1f;
    private Bezier<Vector2> bezier;
    Vector2 temPoint = new Vector2();

    public MoveAsSnackToAction(float duration, float endX, float endY) {
        super(duration);
        this.endX = endX;
        this.endY = endY;

    }

    protected void begin() {
        Vector2 position = Mappers.positionCM.get(this.target).position;
        this.startX = position.x;
        this.startY = position.y;


        Vector2 p1 = new Vector2(startX+(endX-startX)*0.95f, startY+(endY-startY)*-0.31f);
        Vector2 p2 =  new Vector2(startX+(endX-startX)*0.00f, startY+(endY-startY)*1.14f);
        bezier = new Bezier<Vector2>(new Vector2(startX,startY), p2, p1,new Vector2(endX,endY));
        bezier.valueAt(temPoint, percent);
    }

    protected void update(float percent) {
        ActorStateComponent stateComponent = Mappers.actorStateCM.get(this.target);
        bezier.valueAt(temPoint, percent);
        PositionComponent positionComponent = Mappers.positionCM.get(this.target);
        SensorMovementSystem.updateMovement(stateComponent, positionComponent, temPoint.x, temPoint.y);
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
