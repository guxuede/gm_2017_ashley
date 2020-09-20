package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.TempObjects;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.SensorComponent;

public class SensorSystem extends IteratingSystem {

    private static final Family family = Family.all(SensorComponent.class, ActorStateComponent.class).get();

    public SensorSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        Vector2 acc = processKeyEvent();
        ActorStateComponent sensorComponent = Mappers.actorStateCM.get(entity);
        sensorComponent.acceleration.set(acc);
    }

    public Vector2 processKeyEvent(){
        Application.ApplicationType appType = Gdx.app.getType();
        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        float accelX;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
                accelX = -5f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
                accelX = 5f;
            }else{
                accelX = 0;
            }
        }
        float accelY;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelY = Gdx.input.getAccelerometerY();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
                accelY = -5f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
                accelY = 5f;
            }else{
                accelY = 0;
            }
        }
        TempObjects.temp0Vector2.set(accelX*11,accelY*11);
        return TempObjects.temp0Vector2;
    }
}
