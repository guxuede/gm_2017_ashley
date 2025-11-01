package com.guxuede.gm.gdx.actions.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;


/**
 * Created by guxuede on 2016/7/14 .
 */
public class ActorBrandishedByAction extends Acting {

    public Entity sourceOwner;

    public ActorBrandishedByAction(Entity sourceOwner) {
        this.sourceOwner = sourceOwner;
    }

    @Override
    protected void begin() {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);

        PositionComponent positionComponent = Mappers.positionCM.get(actor);
        positionComponent.position.set(sourceOwnerPositionComponent.position);
    }

    float r = 50;
    float angleInRadians = 0;

    private static final Vector2 temp0Vector2 = new Vector2();

    @Override
    protected boolean update(float delta) {
        PositionComponent sourceOwnerPositionComponent = Mappers.positionCM.get(sourceOwner);
        Vector2 sourceOwnerPosition = sourceOwnerPositionComponent.position;

        PositionComponent positionComponent = Mappers.positionCM.get(actor);

        angleInRadians =angleInRadians + delta;

        float x = (float) (sourceOwnerPosition.x + r*Math.sin(angleInRadians));
        float y = (float) (sourceOwnerPosition.y + r*Math.cos(angleInRadians));


        positionComponent.position.set(x, y);

        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);

        temp0Vector2.set(x,y).sub(sourceOwnerPosition).nor();
        presentableComponent.rotation = temp0Vector2.angle();
        System.out.println(angleInRadians +" "+ presentableComponent.rotation);

//        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);


       return false;
    }

    @Override
    protected void end() {
        ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(actor);
        super.end();
    }


    public void onArrived(){

    }

    @Override
    public void reset() {
        super.reset();
    }
}
