package com.guxuede.gm.gdx.actions.appearance;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.GdxSprite;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.MathUtils;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

/**
 * Created by guxuede on 2017/6/5 .
 */
public class ScaleToLineAction  extends RelativeTemporalAction {

    Entity e1,e2;

    public ScaleToLineAction(Entity e1,Entity e2,float duration) {
        this.e1 = e1;
        this.e2 = e2;
        this.setDuration(duration);
    }

    @Override
    protected void updateRelative(float percentDelta) {
        PositionComponent p1 = Mappers.positionCM.get(e2);
        PositionComponent p2 = Mappers.positionCM.get(e1);


        float degrees = MathUtils.getAngle(p1.position.x,p1.position.y,p2.position.x,p2.position.y);

        float distance = (float) MathUtils.distance(p1.position.x,p1.position.y,p2.position.x,p2.position.y);//2单位之间的距离
        float lightLen = 192;//闪电素材的长度

        float drxa = p1.position.x + com.badlogic.gdx.math.MathUtils.cosDeg(degrees) * (distance/2);
        float drya = p1.position.y + com.badlogic.gdx.math.MathUtils.sinDeg(degrees) * (distance/2);

        float tsaclex = 1;
        float tscalex = distance/lightLen;
        float rotation = degrees+90;

        PositionComponent p = Mappers.positionCM.get(actor);
        PresentableComponent pc = Mappers.presentableCM.get(actor);
        p.position.set(drxa,drya);
        pc.rotation = rotation;
        pc.scaleX = tsaclex;
        pc.scaleY = tscalex;
    }

}
