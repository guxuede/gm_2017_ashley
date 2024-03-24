package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorMoveAsSnackToPointAction extends ActorMoveToAction {

    private Bezier<Vector2> bezier;
    Vector2 targetPoint =new Vector2();
    Vector2 temPoint = new Vector2();

    float percent = 0.1f;

    public ActorMoveAsSnackToPointAction(Vector2 t){
        targetPoint.set(t);
    }

    public ActorMoveAsSnackToPointAction(float x,float y){
        targetPoint.set(x,y);
    }


    @Override
    protected void begin() {
        super.begin();
        Vector2 startPoint = Mappers.positionCM.get(actor).position.cpy();
        Vector2 endPoint = targetPoint;
//        Vector2 v = TempObjects.temp0Vector2.set(startPoint).sub(endPoint);
        Vector2 p1 = new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.95f, startPoint.y+(endPoint.y-startPoint.y)*-0.31f);
        Vector2 p2 =  new Vector2(startPoint.x+(endPoint.x-startPoint.x)*0.00f, startPoint.y+(endPoint.y-startPoint.y)*1.14f);
        bezier = new Bezier<Vector2>(startPoint, p2, p1,endPoint);
        bezier.valueAt(temPoint, percent);
    }

    @Override
    protected boolean isArrive() {
        return percent > 1 && super.isArrive();
    }

    @Override
    public Vector2 getTargetPoint() {
        Vector2 actP = Mappers.positionCM.get(actor).position;
        if(actP.dst2(temPoint) < 1){
            percent=percent+0.1f;
            bezier.valueAt(temPoint, percent);
        }
        return temPoint;
    }
}
