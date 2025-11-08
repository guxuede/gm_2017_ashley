package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by guxuede on 2017/9/10 .
 */
public class BloodComponent implements Component  , Pool.Poolable{
    public float hitPoint = 100;
    public float currentHitPoint = 100;


    //draw
    public float lastModifyTime;


    public void modify(float delta){
        hitPoint = hitPoint + delta;
        lastModifyTime = System.nanoTime();
    }


    @Override
    public void reset() {
        hitPoint = 0;
        currentHitPoint = 0;
    }
}
