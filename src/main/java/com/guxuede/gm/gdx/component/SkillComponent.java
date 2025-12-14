package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.component.skill.Skill;

/**
 * Created by guxuede on 2017/6/4 .
 */
public class SkillComponent implements Component , Pool.Poolable {
    public final Array<Skill> skills = new Array<Skill>();

    public Skill getSkill(String name){
        return skills.select(e->e.getName().equals(name)).iterator().next();
    }

    public Skill getRandomSkill(){
        return skills.random();
    }

    @Override
    public void reset() {
        skills.clear();
    }
}
