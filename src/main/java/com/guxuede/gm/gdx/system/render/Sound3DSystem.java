package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.SoundComponent;

/**
 * Created by guxuede on 2017/9/3 .
 */
public class Sound3DSystem extends IteratingSystem {

    OrthographicCamera camera;

    public Sound3DSystem(OrthographicCamera camera) {
        super(Family.all(SoundComponent.class, PositionComponent.class).get());
        this.priority = 1;
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SoundComponent soundComponent = Mappers.soundCM.get(entity);
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        if (soundComponent.soundId != -1) {
            soundComponent.threeDSound(positionComponent.position, camera);
        }
    }
}
