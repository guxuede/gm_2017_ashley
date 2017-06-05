package com.guxuede.gm.gdx.component.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.component.SkillComponent;
import com.guxuede.gm.gdx.component.skill.Skill;

import static com.guxuede.gm.gdx.component.ActorStateComponent.*;

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
            if (Input.Keys.RIGHT == event.getKeyCode()){
                return new MoveState(RIGHT);
            }else if(Input.Keys.UP == event.getKeyCode()){
                return new MoveState(UP);
            }else if(Input.Keys.DOWN == event.getKeyCode()){
                return new MoveState(DOWN);
            }else if(Input.Keys.LEFT == event.getKeyCode()){
                return new MoveState(LEFT);
            }else if(Input.Keys.SPACE == event.getKeyCode()){
                return new AttackState(direction);
            }
            SkillComponent skillComponent = Mappers.skillCM.get(entity);
            if(skillComponent!=null){
                for (final Skill skill : skillComponent.skills) {
                    if (skill.getHotKey() == event.getKeyCode() && skill.isAvailable) {

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
            }else{
                return new MoveState(DOWN);
            }
        }
        return null;
    }


}
