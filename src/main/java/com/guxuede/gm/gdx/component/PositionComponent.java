package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by guxuede on 2017/6/3 .
 */
public class PositionComponent implements Component {
    public final Vector2 position = new Vector2();
    public float height;
    public float degrees;

}
