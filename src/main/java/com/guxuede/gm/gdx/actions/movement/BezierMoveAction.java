package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.actions.Action;


public class BezierMoveAction extends Action{
    private Bezier<Vector2> bezier;
    private Vector2 targetPoint = new Vector2();
    private Vector2 temPoint = new Vector2();

    public BezierMoveAction(float x, float y){
        targetPoint.set(x,y);
    }

    float percent = 0.1f;

    @Override
    public boolean act(float delta) {
        if(bezier==null){
            Vector2 startPoint = Mappers.positionCM.get(actor).position.cpy();
            Vector2 endPoint = targetPoint;
            Vector2 v = TempObjects.temp0Vector2.set(startPoint).sub(endPoint);
            Vector2 p1 = new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.95f, startPoint.y+(endPoint.y-startPoint.y)*-0.31f);
            Vector2 p2 =  new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.00f, startPoint.y+(endPoint.y-startPoint.y)*1.14f);
            bezier = new Bezier<Vector2>(startPoint, p2, p1,endPoint);
            bezier.valueAt(temPoint, percent);
        }
        Vector2 actP = Mappers.positionCM.get(actor).position;
        if(actP.dst2(temPoint) < 1){
            percent=percent+0.1f;
            bezier.valueAt(temPoint, percent);
        }
        Vector2 vector2 =TempObjects.temp0Vector2.set(actP).sub(temPoint).nor().scl(50f);
        Mappers.actorStateCM.get(actor).acceleration.set(vector2);
        return false;
    }

}
