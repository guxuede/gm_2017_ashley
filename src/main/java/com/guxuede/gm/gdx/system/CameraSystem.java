package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;

/**
 * Created by guxuede on 2017/6/10 .
 */
public class CameraSystem extends EntitySystem {

    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    public CameraSystem(OrthographicCamera camera, SpriteBatch spriteBatch){
        this.spriteBatch = spriteBatch;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        updateCamera();
        Batch batch = this.spriteBatch;
        batch.setProjectionMatrix(camera.combined);
    }

    private float speed = 5.0f;
    private void updateCamera(){
            if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
                camera.translate(0, speed);
                camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){
                camera.translate(0, -speed);camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.HOME)){
                camera.translate(speed, 0);camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.END)){
                camera.translate(-speed, 0);camera.update();
            }
        }
}
