package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.TempObjects;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.actions.TemporalAction;


public class BezierMoveAction extends TemporalAction{
    private Bezier<Vector2> bezier;
    private Vector2 targetPoint = new Vector2();

    public BezierMoveAction(float x, float y){
        targetPoint.set(x,y);
        this.setDuration(1);
    }


    @Override
    protected void begin() {
        super.begin();
        Vector2 startPoint = Mappers.positionCM.get(actor).position.cpy();
        Vector2 endPoint = targetPoint;
        Vector2 v = TempObjects.temp0Vector2.set(startPoint).sub(endPoint);
        Vector2 p1 = new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.95f, startPoint.y+(endPoint.y-startPoint.y)*-0.31f);
        Vector2 p2 =  new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.00f, startPoint.y+(endPoint.y-startPoint.y)*1.14f);
        bezier = new Bezier<Vector2>(startPoint, p2, p1,endPoint);
    }

    @Override
    protected void update(float percent) {
        //bezier.valueAt(TempObjects.temp0Vector2, percent);
        //Vector2 nor = TempObjects.temp0Vector2.set(ownerPos).sub(pos).nor().scl(50f);
        Vector2 vector2 =bezier.derivativeAt(TempObjects.temp0Vector2,percent).nor().scl(50f);
        //Mappers.actorStateCM.get(actor).acceleration.set(vector2);
        bezier.valueAt(TempObjects.temp0Vector2, percent);
        Mappers.positionCM.get(actor).position.set(TempObjects.temp0Vector2);
    }

}
