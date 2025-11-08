package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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
            if(Mappers.positionCM.get(e).position.dst(positionComponent.position) < hurtComponent.hurtRadius){
                BloodComponent bloodComponent = Mappers.bloodCM.get(e);
                bloodComponent.modify(-hurtComponent.hurtDamage);

                ActionsComponent actionsComponent = Mappers.actionCM.get(e);
                actionsComponent.addAction(e,new ActorRepelAction(positionComponent.position,30, 0.5f));
            }
        });
    }
}
