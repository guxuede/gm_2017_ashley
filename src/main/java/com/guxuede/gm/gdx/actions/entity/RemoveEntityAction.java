package com.guxuede.gm.gdx.actions.entity;

import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.actions.Action;

/**
 * Created by guxuede on 2017/6/11 .
 */
public class RemoveEntityAction extends Action {

    @Override
    public boolean act(float delta) {
        E.remove(actor);
        return true;
    }
}
