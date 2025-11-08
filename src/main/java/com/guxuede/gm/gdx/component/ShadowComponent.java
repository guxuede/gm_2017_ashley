package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ShadowComponent implements Component, Pool.Poolable {

    public float width;

    @Override
    public void reset() {
        width = 0;
    }
}
