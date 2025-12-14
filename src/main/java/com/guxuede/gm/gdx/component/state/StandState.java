package com.guxuede.gm.gdx.component.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.basic.libgdx.SingletonPooledEngine;
import com.guxuede.gm.gdx.component.SkillComponent;
import com.guxuede.gm.gdx.component.skill.Skill;
import com.guxuede.gm.gdx.system.physics.MouseSystem;

import static com.guxuede.gm.gdx.component.skill.SkillTargetTypeEnum.TARGET_TYPE_AREA;

/**
 * Created by guxuede on 2016/6/15 .
 */
public class StandState extends ActorState {

    int direction;


    public StandState(int direction){
        this.direction = direction;
    }

    @Override
    public void enter(Entity entity, InputEvent event) {
        //entity.stop();
    }

    @Override
    public ActorState handleInput(final Entity entity, final InputEvent event) {
        if(InputEvent.Type.keyDown == event.getType()){
//            if (Input.Keys.RIGHT == event.getKeyCode()){
//                return new MoveState(RIGHT);
//            }else if(Input.Keys.UP == event.getKeyCode()){
//                return new MoveState(UP);
//            }else if(Input.Keys.DOWN == event.getKeyCode()){
//                return new MoveState(DOWN);
//            }else if(Input.Keys.LEFT == event.getKeyCode()){
//                return new MoveState(LEFT);
//            }else if(Input.Keys.SPACE == event.getKeyCode()){
//                return new AttackState(direction);
//            }
            SkillComponent skillComponent = Mappers.skillCM.get(entity);
            if(skillComponent!=null){
                for (final Skill skill : skillComponent.skills) {
                    if (skill.getHotKey() == event.getKeyCode() && skill.isAvailable) {
                        MouseSystem.MouseIndicatorListener listener = new MouseSystem.MouseIndicatorListener() {
                            @Override
                            public boolean onHoner(Entity animationEntity, Vector2 center, float r) {
                                return true;
                            }
                            @Override
                            public void onActive(Entity animationEntity, Vector2 center, float r) {
                                if(skill.verifyTarget(animationEntity,center,r)){
                                    skill.targetEntry = animationEntity;
                                    skill.owner = entity;
                                    skill.targetPos = center==null?null:center.cpy();
                                    AttackState actorState = new AttackState(direction);
                                    actorState.skill = skill;
                                    skill.play();
                                    //skill.isAvailable = false;
                                    Mappers.actorStateCM.get(entity).goingToNewState(animationEntity,actorState,null);
                                }
                            }
                        };
                        if (skill.getTargetType() == TARGET_TYPE_AREA) {
                            SingletonPooledEngine.instance.getSystem(MouseSystem.class).enterToAreaChoiceStatus(100, listener);
                        } else {
                            SingletonPooledEngine.instance.getSystem(MouseSystem.class).enterToTargetChoiceStatus(listener);
                        }
                        break;
                    }
                }
            }
        }else if(event.getType() == InputEvent.Type.touchDown){
            Entity entityT = event.getTarget()==null?null:(Entity) event.getTarget().getUserObject();
            if(entityT!=null &&  entityT !=entity){
                if(!(this instanceof AttackState) ){
                    AttackState as = new AttackState(direction);
                    as.target = entityT;
                    return as;
                }
            }
        }
        return null;
    }


}
