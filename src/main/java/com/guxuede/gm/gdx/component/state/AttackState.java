package com.guxuede.gm.gdx.component.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.guxuede.gm.gdx.component.skill.Skill;

/**
 * Created by guxuede on 2016/6/16 .
 */
public class AttackState extends StandState {

    Entity target;
    public Skill skill;

    public AttackState(int direction){
        super(direction);
    }

    @Override
    public void enter(Entity entity, InputEvent event) {
        //entity.stop();
    }

    @Override
    public ActorState update(Entity entity,float delta) {
        boolean result = skill ==null?true:skill.update(delta);
        if(result){
            return new StandState(direction);
        }
        return null;
    }

    @Override
    public void exit(Entity entity) {
        if(skill!=null){
            skill.exit();
            skill = null;
        }
    }
}
