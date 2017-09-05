package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.guxuede.gm.gdx.SoundUtils;
import com.guxuede.gm.gdx.system.CameraSystem;

/**
 * Created by guxuede on 2017/9/3 .
 */
public class SoundComponent implements Component {
    public Sound sound;
    public boolean isLoop;
    public long soundId = -1;

    public SoundComponent(){

    }

    public SoundComponent(Sound sound, boolean isLoop) {
        this.sound = sound;
        this.isLoop = isLoop;
    }

    public void play(){
        soundId = sound.play();
        sound.setLooping(soundId,isLoop);
    }


    public void pause(){
        if(soundId!=-1){
            sound.pause(soundId);
        }
    }

    public void resume(){
        if(soundId!=-1){
            sound.resume(soundId);
        }
    }

    public void stop(){
        if(soundId!=-1){
            if(isLoop){
                sound.setLooping(soundId,false);
            }else {
                sound.stop(soundId);
            }
        }
        soundId = -1;
    }

    public void threeDSound(Vector2 soundPos, Camera camera){
        if (sound != null && soundId != -1) {
            SoundUtils.set3dPan(sound, soundId, soundPos.x,soundPos.y,camera);
        }
    }
}
