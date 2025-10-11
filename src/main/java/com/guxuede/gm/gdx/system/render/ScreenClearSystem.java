package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by guxuede on 2017/10/22 .
 */
public class ScreenClearSystem extends EntitySystem {

    Color CLEAR_COLOR = Color.valueOf("969291");

    public ScreenClearSystem(int priority){
        this.priority = priority;
    }

    @Override
    public void update(float deltaTime) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
