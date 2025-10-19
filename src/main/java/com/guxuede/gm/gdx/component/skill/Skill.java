package com.guxuede.gm.gdx.component.skill;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import static com.guxuede.gm.gdx.component.skill.SkillTargetTypeEnum.*;

/**
 * Created by guxuede on 2016/9/27 .
 */
public abstract class Skill implements Pool.Poolable,Cloneable {
    public String id;
    public String name;
    public Entity targetEntry;
    public Vector2 targetPos;
    public boolean isAvailable = true;

    public int getHotKey(){
        return Input.Keys.A;
    }

    public SkillTargetTypeEnum getTargetType(){
        return TARGET_TYPE_TARGET;
    }
    /**技能拥有者*/
    public Entity owner;
    public float stateTime;


    public boolean update(float delta) {

        return false;
    }

    public void enter(){

    }

    public void exit() {
        reset();
    }

    public boolean verifyTarget(Entity animationEntity, Vector2 center, float r){
        if(getTargetType() == TARGET_TYPE_TARGET && animationEntity!=null){
            return true;
        }else if(getTargetType() == TARGET_TYPE_AREA && center!=null){
            return true;
        }else if(getTargetType() == TARGET_TYPE_ALL_TARGET && (animationEntity!=null || center!=null)){
            return true;
        }
        return false;
    }


    @Override
    public void reset() {
        targetEntry = null;
        targetPos = null;
        stateTime = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
