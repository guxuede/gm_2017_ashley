package com.guxuede.gm.gdx.basic.libgdx;

import com.badlogic.ashley.core.PooledEngine;

/**
 * Created by guxuede on 2017/6/11 .
 */
public class SingletonPooledEngine extends PooledEngine {

    public static SingletonPooledEngine instance = new SingletonPooledEngine();
}
