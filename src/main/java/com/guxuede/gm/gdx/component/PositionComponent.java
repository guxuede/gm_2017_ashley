package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class PositionComponent implements Component , Pool.Poolable {
    public final Vector2 position = new Vector2();
    public float height;
    public float degrees;

    @Override
    public void reset() {
        position.set(0,0);
        height = 0;
        degrees = 0;
    }
}
