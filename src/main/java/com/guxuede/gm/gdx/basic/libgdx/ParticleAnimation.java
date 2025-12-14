package com.guxuede.gm.gdx.basic.libgdx;

import com.badlogic.gdx.graphics.g2d.Animation;

public class ParticleAnimation extends Animation<GdxSprite> {

    private ParticleSprite particleSprite;

    public ParticleAnimation(ParticleSprite particleSprite){
        super(5, particleSprite);
        this.particleSprite = particleSprite;
    }

    @Override
    public GdxSprite getKeyFrame(float stateTime, boolean looping) {
        return particleSprite;
    }
}
