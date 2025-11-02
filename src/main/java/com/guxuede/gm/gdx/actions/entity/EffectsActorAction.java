package com.guxuede.gm.gdx.actions.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.actions.Action;

/**
 * Created by guxuede on 2017/4/4 .
 */
public class EffectsActorAction extends Action {

    private String actorName;
    private final Vector2 pos = new Vector2();
    private Action[] actions;
    private Entity effectsEntity;

    @Override
    public boolean act(float delta) {
        PresentableComponent component = getActor().getComponent(PresentableComponent.class);
        float roration = component == null? 0 : component.rotation;
        effectsEntity = E.create().animation(actorName, roration).pos(pos.x,pos.y).actions(actions).buildToWorld();
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        actorName = null;
        pos.set(0,0);
        actions = null;
        effectsEntity = null;
    }


    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setPos(Vector2 pos) {
        this.pos.set(pos);
    }

    public void setActions(Action... actions) {
        this.actions = actions;
    }
}
