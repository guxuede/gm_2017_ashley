
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.basic.libgdx.SoundUtils;

public class AnimationComponent implements Component , Pool.Poolable{
    public Animation<TextureRegion> animation;
    public boolean loop;
    public float stateTime;

    public TextureRegion currentFrameIndex;
    public long soundId = -1;
    public Sound sound = null;

    public TextureRegion getNextKeyFrame (float deltaTime) {
        stateTime += deltaTime;
        if(animation == null){
            return null;
        }
        TextureRegion textureRegion = this.animation.getKeyFrame(stateTime, loop);
        TextureRegion currentFrameIndex = textureRegion;
        if(currentFrameIndex!=this.currentFrameIndex){
            this.currentFrameIndex = currentFrameIndex;
            onFrameChange(textureRegion);
        }
        return textureRegion;
    }

    /**
     * Todo 移到另一个系统去
     * @param textureRegion
     */
    public void onFrameChange(TextureRegion textureRegion){
        if(textureRegion instanceof GdxSprite){
            sound = ResourceManager.getSpriteSound(((GdxSprite) textureRegion).id);
            if(sound!=null){
                soundId = sound.play();
            }
        }
    }

    public void threeDSound(Vector2 soundPos, Camera camera){
        if (sound != null && soundId != -1) {
            SoundUtils.set3dPan(sound, soundId, soundPos.x,soundPos.y,camera);
        }
    }

    @Override
    public void reset() {
        animation = null;
        stateTime = 0;
        loop = false;
        currentFrameIndex = null;
        soundId = -1;
        sound = null;
    }
}
