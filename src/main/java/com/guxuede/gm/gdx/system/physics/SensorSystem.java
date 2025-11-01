package com.guxuede.gm.gdx.system.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.component.SensorComponent;
import com.guxuede.gm.gdx.system.render.StageSystem;

public class SensorSystem extends EntitySystem {

    private static final Family family = Family.all(SensorComponent.class).get();
    public static final int ACC = 1;

    public SensorSystem() {

    }

    @Override
    public void update(float deltaTime) {
        Entity viewActor = getEngine().getSystem(StageSystem.class).getViewActor();
        if(viewActor!=null){
            Vector2 acc = processKeyEvent();
            SensorComponent sensorComponent = Mappers.sensorCM.get(viewActor);
            sensorComponent.acceleration.set(acc);
        }
    }


    public Vector2 processKeyEvent(){
        Application.ApplicationType appType = Gdx.app.getType();
        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        float accelX;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                accelX = -1f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.D)){
                accelX = 1f;
            }else{
                accelX = 0;
            }
        }
        float accelY;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelY = Gdx.input.getAccelerometerY();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.S)){
                accelY = -1f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
                accelY = 1f;
            }else{
                accelY = 0;
            }
        }
        TempObjects.temp0Vector2.set(accelX* ACC,accelY*ACC);
        return TempObjects.temp0Vector2;
    }
}
