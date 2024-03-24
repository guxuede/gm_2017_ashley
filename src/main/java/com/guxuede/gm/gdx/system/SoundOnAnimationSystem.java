package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.component.AnimationComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2017/9/7 .
 */
public class SoundOnAnimationSystem  extends IteratingSystem {

    OrthographicCamera camera;

    public SoundOnAnimationSystem(OrthographicCamera camera) {
        super(Family.all(AnimationComponent.class, PositionComponent.class).get());
        this.priority = 1;
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = Mappers.animationCM.get(entity);
        if(animationComponent.sound!=null && animationComponent.soundId !=-1){
            PositionComponent positionComponent = Mappers.positionCM.get(entity);
            animationComponent.threeDSound(positionComponent.position,camera);
        }
    }


    /**
     * Todo 移到另一个系统去
     * @param textureRegion
     */
    public void onFrameChange(TextureRegion textureRegion){
        if(textureRegion instanceof GdxSprite){
            Sound sound = ResourceManager.getSpriteSound(((GdxSprite) textureRegion).id);
            if(sound!=null){
                sound.play();
            }
        }

    }
}
