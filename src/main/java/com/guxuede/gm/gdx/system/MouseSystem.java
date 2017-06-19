package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.TempObjects;

/**
 * Created by guxuede on 2017/6/11 .
 */
public class MouseSystem extends EntitySystem {
    public static float MAP_CELL_W = 32;
    public static float MAP_CELL_H = 32;

    public static final int MOUSE_STTUS_NORMAL = 0, MOUSE_STTUS_AREA_Indicator = 1, MOUSE_STTUS_TARGET_Indicator = 2;
    public int status = MOUSE_STTUS_NORMAL;

    private Batch batch;
    private Viewport viewport;

    public Sprite mouseSprite;
    public float r;
    public MouseIndicatorListener listener;
    ShapeRenderer shapes;

    public MouseSystem(int priority,Batch batch, Viewport viewport,InputMultiplexer inputMultiplexer) {
        inputMultiplexer.addProcessor(this.inputProcessor);
        this.priority = priority;
        this.batch = batch;
        this.viewport = viewport;
        shapes = new ShapeRenderer();
        shapes.setColor(Color.GREEN);
        cancelIfNeed();
        enterToAreaChoiceStatus(100,null);
    }

    public void setDefaultCursor() {
        Gdx.graphics.setCursor(ResourceManager.customCursor);
        mouseSprite = null;
    }

    public void setAreaCursor() {
        setDefaultCursor();
        mouseSprite = ResourceManager.mouseAreaIndicator;
    }

    public void setTargetCursor() {
        Gdx.graphics.setCursor(ResourceManager.customCursor);
        mouseSprite = ResourceManager.mouseTargetIndicator;
    }


    public void enterToAreaChoiceStatus(float r, MouseIndicatorListener listener) {
        cancelIfNeed();
        this.listener = listener;
        this.r = r;
        status = MOUSE_STTUS_AREA_Indicator;
        setAreaCursor();
    }

    public void enterToTargetChoiceStatus(MouseIndicatorListener listener) {
        cancelIfNeed();
        this.listener = listener;
        this.r = 0;
        status = MOUSE_STTUS_TARGET_Indicator;
        setTargetCursor();
    }


    public void cancelIfNeed() {
        cancel();
        if (listener != null) {
            listener.onCancel();
            listener = null;
        }
    }

    public void cancel() {
        this.r = 0;
        status = MOUSE_STTUS_NORMAL;
        setDefaultCursor();
    }

    private InputProcessor inputProcessor = new InputAdapter() {

        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            viewport.unproject(TempObjects.temp0Vector2.set(x,y));
            if (status != MOUSE_STTUS_NORMAL) {
                if (button == 1) {
                    cancelIfNeed();
                    return true;
                } else if (button == 0 && listener!=null) {
                    Entity animationTarget =  null;
                    if (status == MOUSE_STTUS_AREA_Indicator) {
                        if (listener != null && listener.onHoner(animationTarget, TempObjects.temp0Vector2, r)) {
                            listener.onActive(animationTarget, TempObjects.temp0Vector2, r);
                            cancel();
                            return true;
                        }
                    } else if (status == MOUSE_STTUS_TARGET_Indicator) {
                        if (listener != null && listener.onHoner(animationTarget, null, r)) {
                            listener.onActive(animationTarget, TempObjects.temp0Vector2, r);
                            cancel();
                            return true;
                        }
                    }
                }else if(listener == null){
                    cancel();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean keyDown(int keycode) {
            if (status != MOUSE_STTUS_NORMAL && Input.Keys.ESCAPE == keycode) {
                cancelIfNeed();
                return true;
            }
            return false;
        }

    };

    @Override
    public void update(float deltaTime) {
        render(batch, deltaTime);
    }

    public void render(Batch batch, float delta) {
        Vector2 pos = viewport.unproject(TempObjects.temp1Vector2.set(Gdx.input.getX(), Gdx.input.getY()));
        if (mouseSprite != null) {
            batch.begin();
            mouseSprite.setPosition(pos.x, pos.y);
            mouseSprite.rotate(1f);
            mouseSprite.draw(batch);
            batch.end();
        }

        shapes.setProjectionMatrix(batch.getProjectionMatrix());
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.rect((int) (pos.x / MAP_CELL_W) * MAP_CELL_W, (int) (pos.y / MAP_CELL_H) * MAP_CELL_H, MAP_CELL_W, MAP_CELL_H);
        shapes.end();
//        if(ActorMoveToPathAction.path!=null){
//            IntArray path = ActorMoveToPathAction.path;
//            for (int i = 0, n = path.size; i < n; i += 2) {
//                int x = path.get(i);
//                int y = path.get(i + 1);
//                shapes.rect(x, y, MAP_CELL_W, MAP_CELL_H);
//            }
//        }
    }

    public static class MouseIndicatorListener {
        public boolean onHoner(Entity animationEntity, Vector2 center, float r) {
            return false;
        }

        public void onActive(Entity animationEntity, Vector2 center, float r) {

        }

        public void onCancel() {

        }
    }
}
