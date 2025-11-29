package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.A;
import com.guxuede.gm.gdx.actions.ParallelAction;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.actions.buff.AddAttributeModifierAction;
import com.guxuede.gm.gdx.actions.buff.RemoveAttributeModifierAction;
import com.guxuede.gm.gdx.actions.buff.TemporalActionAttributeDeltaModifierAction;
import com.guxuede.gm.gdx.actions.buff.TemporalActionAttributeModifierAction;
import com.guxuede.gm.gdx.component.ActionsComponent;
import com.guxuede.gm.gdx.component.BloodComponent;
import com.guxuede.gm.gdx.component.HurtComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.modifier.AnyAttributeModifier;
import com.guxuede.gm.gdx.modifier.HighAttribute;
import com.guxuede.gm.gdx.modifier.PositionXAttribute;
import com.guxuede.gm.gdx.modifier.PositionYAttribute;

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

            PositionComponent tPosition = Mappers.positionCM.get(e);
            if(!hurtComponent.hurtEntity.contains(e, true) && tPosition.position.dst(positionComponent.position) < hurtComponent.hurtRadius){
                BloodComponent bloodComponent = Mappers.bloodCM.get(e);
                bloodComponent.modify(-hurtComponent.hurtDamage);
                hurtComponent.hurtEntity.add(e);
                ActionsComponent actionsComponent = Mappers.actionCM.get(e);

                Vector2 facePosition = new Vector2(tPosition.position);
                facePosition.sub(positionComponent.position).nor().scl(50,50);

                AnyAttributeModifier highAttributeModifier = new AnyAttributeModifier();
                AnyAttributeModifier xAttributeModifier = new AnyAttributeModifier();
                AnyAttributeModifier yAttributeModifier = new AnyAttributeModifier();
                actionsComponent.addAction(e,
                        new ParallelAction(
                                new SequenceAction(
                                        new AddAttributeModifierAction(HighAttribute.INSTANCE, highAttributeModifier),
                                        new TemporalActionAttributeModifierAction(highAttributeModifier, 0,50f, 0.5f, Interpolation.circleOut),
                                        new TemporalActionAttributeModifierAction(highAttributeModifier, 50f, 0f, 0.5f, Interpolation.bounceOut),
                                        new RemoveAttributeModifierAction(HighAttribute.INSTANCE, highAttributeModifier)
                                ),
                                new SequenceAction(
                                        new AddAttributeModifierAction(PositionXAttribute.INSTANCE, xAttributeModifier),
                                        new TemporalActionAttributeDeltaModifierAction(xAttributeModifier,facePosition.x/2, 0.5f, Interpolation.circleOut),
                                        new TemporalActionAttributeDeltaModifierAction(xAttributeModifier,facePosition.x/2, 0.5f, Interpolation.bounceOut),
                                        new RemoveAttributeModifierAction(PositionXAttribute.INSTANCE, xAttributeModifier)
                                ),
                                new SequenceAction(
                                        new AddAttributeModifierAction(PositionYAttribute.INSTANCE, yAttributeModifier),
                                        new TemporalActionAttributeDeltaModifierAction(yAttributeModifier, facePosition.y/2, 0.5f, Interpolation.circleOut),
                                        new TemporalActionAttributeDeltaModifierAction(yAttributeModifier, facePosition.y/2, 0.5f, Interpolation.bounceOut),
                                        new RemoveAttributeModifierAction(PositionYAttribute.INSTANCE, yAttributeModifier)
                                ),
                                A.effectsActorAction("blood",tPosition.position)
                        )


                );
            }
        });
    }
}
