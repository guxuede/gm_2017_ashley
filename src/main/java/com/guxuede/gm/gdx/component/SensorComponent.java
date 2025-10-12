package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * 可以被控制器操控
 */
public class SensorComponent implements Component {

    public final Vector2 acceleration = new Vector2();//加速度，要有加速度才能有速度

}
