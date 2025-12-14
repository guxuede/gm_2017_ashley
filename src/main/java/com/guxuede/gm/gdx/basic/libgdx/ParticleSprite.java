package com.guxuede.gm.gdx.basic.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by guxuede on 2016/6/1 .
 */
public class ParticleSprite extends GdxSprite {

    private ParticleEffectPool.PooledEffect effect;;

    public ParticleSprite(ParticleEffectPool.PooledEffect effect) {
        this.effect = effect;
    }

    public void draw(Batch batch, float alphaModulation, float rotation, float scaleX, float scaleY, Color color) {
        effect.setPosition(getX(),getY());
        effect.draw(batch);
    }
}
