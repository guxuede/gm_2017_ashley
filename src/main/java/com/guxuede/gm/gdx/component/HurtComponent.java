package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

//携带此Component的entity 可以对别的 entity 的BloodComponent 造成伤害2
public class HurtComponent implements Component, Pool.Poolable {

    public Array<Entity> hurtEntity = new Array<>();

    public float hurtDamage;
    public float hurtRadius;

    @Override
    public void reset() {
        hurtEntity = new Array<>();
        hurtDamage = 0;
        hurtRadius = 0;

    }
}
