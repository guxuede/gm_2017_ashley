package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.SoundComponent;

/**
 * Created by guxuede on 2017/9/3 .
 */
public class SoundSystem extends IteratingSystem  {


    public SoundSystem() {
        super(Family.all(SoundComponent.class).get());
        this.priority = 1;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SoundComponent soundComponent = Mappers.soundCM.get(entity);
        if(soundComponent.soundId == -1){
            soundComponent.play();
        }
    }
}
