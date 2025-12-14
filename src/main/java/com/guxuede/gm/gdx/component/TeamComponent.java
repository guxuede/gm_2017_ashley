package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class TeamComponent implements Component, Pool.Poolable{
    public String name;
    public int id;

    @Override
    public void reset() {
        name = null;
        id = -1;
    }
}
