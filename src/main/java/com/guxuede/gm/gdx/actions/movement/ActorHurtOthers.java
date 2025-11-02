package com.guxuede.gm.gdx.actions.movement;

import com.guxuede.gm.gdx.actions.Acting;
import com.guxuede.gm.gdx.component.PositionComponent;

public class ActorHurtOthers extends Acting {



    @Override
    protected boolean update(float delta) {
        PositionComponent component = getActor().getComponent(PositionComponent.class);

        return false;
    }


}
