package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;


public class ParticleEffectManagerSystem  extends EntitySystem {

    public static IntMap<ParticleEffectPool> particleEffectPoolIntMap = new IntMap<>();

    public static Array<ParticleEffectPool.PooledEffect> effectsBorrowed = new Array();


    @Override
    public void update(float deltaTime) {
        for (int i = effectsBorrowed.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effectsBorrowed.get(i);
            effect.update(deltaTime);
            if (effect.isComplete()) {
                System.out.println("free effect:" + effect);
                effect.free();
                effectsBorrowed.removeIndex(i);
            }
        }
    }
}
