
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;
import com.guxuede.gm.gdx.AnimationHolder;
import com.guxuede.gm.gdx.GdxSprite;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.SoundUtils;

public class AnimationComponent implements Component {
    public static final int STOP=0, DOWN=1,LEFT=2,RIGHT=3,UP=4;

    public Animation animation;
    public float stateTime;
    public float maxStateTime = Float.MAX_VALUE;
    public String animationName;

    public TextureRegion currentFrameIndex;
    public long soundId = -1;
    public Sound sound = null;

    public TextureRegion getNextKeyFrame (float deltaTime, boolean looping) {
        stateTime += deltaTime;
        if(stateTime >= maxStateTime){
            return null;
        }
        TextureRegion textureRegion = this.animation.getKeyFrame(stateTime,looping);
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

    //解决位置为负时声音反而变大的问题
    public void threeDSound(Vector2 soundPos, Camera camera){
        if (sound != null && soundId != -1) {
            SoundUtils.set3dPan(sound, soundId, soundPos.x,soundPos.y,camera);
        }
    }
}
