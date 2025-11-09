package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Interpolation;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.actions.appearance.ColorAction;
import com.guxuede.gm.gdx.actions.movement.ActorRepelAction;
import com.guxuede.gm.gdx.component.ActionsComponent;
import com.guxuede.gm.gdx.component.BloodComponent;
import com.guxuede.gm.gdx.component.HurtComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

//携带此Component的entity 可以对别的 entity 的BloodComponent 造成伤害2
public class HurtSystem extends IteratingSystem {

    private static final Family family = Family.all(HurtComponent.class, PositionComponent.class).get();

    private static final Family blood = Family.all(BloodComponent.class, PositionComponent.class).get();

    public HurtSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        HurtComponent hurtComponent = Mappers.hurtCM.get(entity);
        getEngine().getEntitiesFor(blood).forEach(e->{
            if(!hurtComponent.hurtEntity.contains(e, true) && Mappers.positionCM.get(e).position.dst(positionComponent.position) < hurtComponent.hurtRadius){
                BloodComponent bloodComponent = Mappers.bloodCM.get(e);
                bloodComponent.modify(-hurtComponent.hurtDamage);
                hurtComponent.hurtEntity.add(e);
                ActionsComponent actionsComponent = Mappers.actionCM.get(e);
                actionsComponent.addAction(e,new SequenceAction(new ActorRepelAction(positionComponent.position,20, 15f, 0.5f, Interpolation.circleOut),new ActorRepelAction(positionComponent.position,50, 0f, 0.5f, Interpolation.bounceOut)));
            }
        });
    }
}
