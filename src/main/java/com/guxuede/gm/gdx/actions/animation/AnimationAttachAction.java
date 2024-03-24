package com.guxuede.gm.gdx.actions.animation;

import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class AnimationAttachAction extends RelativeTemporalAction{

    public AnimationAttachAction(float duration, Entity target){
        setDuration(duration);
        setTarget(target);
    }

    @Override
    protected void updateRelative(float percentDelta) {
        PositionComponent positionComponentSource = Mappers.positionCM.get(actor);
        PositionComponent positionComponentTarget = Mappers.positionCM.get(target);
        positionComponentSource.position.set(positionComponentTarget.position);
    }

}
