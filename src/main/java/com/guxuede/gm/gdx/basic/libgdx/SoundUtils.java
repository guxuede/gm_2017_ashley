package com.guxuede.gm.gdx.basic.libgdx;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by guxuede on 2016/9/3 .
 */
@Slf4j
public class SoundUtils {

    public static final long play(Sound sound, Actor actor) {
        long soundId = sound.play();
        set3dPan(sound,soundId,actor.getX(),actor.getY(),actor.getStage().getCamera());
        return soundId;
    }

    public static final void set3dPan(Sound sound, long soundId, Actor actor) {
        set3dPan(sound,soundId,actor.getX(),actor.getY(),actor.getStage().getCamera());
    }

    public static final void set3dPan(Sound sound, long soundId, float soundX, float soundY, Camera camera){
        set3dPan(sound,soundId,soundX,soundY,camera.position.x,camera.position.y,camera.viewportHeight);
    }

    public static final void set3dPan(Sound sound, long soundId, float soundX, float soundY, float screenX, float screenY, float screenR) {
        float pan = (soundX - screenX) / screenR;
        float volume = (float) (1f - MathUtils.distance(soundX, soundY, screenX, screenY) / screenR);
        //log.info("volume = " + volume + "，pan = " + pan);
        sound.setPan(soundId, pan, volume < 0 ? 0 : volume);
    }
}
