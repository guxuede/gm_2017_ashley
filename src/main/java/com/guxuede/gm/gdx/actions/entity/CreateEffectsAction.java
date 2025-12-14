package com.guxuede.gm.gdx.actions.entity;

import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.actions.Action;

/**
 * 创建一个动画在指定地点
 * Created by guxuede on 2017/4/4 .
 */
public class CreateEffectsAction extends Acting {

    private String actorName;
    private final Vector2 pos = new Vector2();
    private Action[] actions;

    @Override
    public boolean update(float delta) {
        PresentableComponent component = getActor().getComponent(PresentableComponent.class);
        float rotation = component == null? 0 : component.rotation;
        E.create().animation(actorName, rotation).pos(pos.x,pos.y).actions(actions).buildToWorld();
        return true;
    }

    @Override
    public void reset() {
        super.reset();
        actorName = null;
        pos.set(0,0);
        actions = null;
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
