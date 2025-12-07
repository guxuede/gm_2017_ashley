
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;

public class ActorAnimationComponent implements Component , Pool.Poolable{

	public AnimationHolder animationHolder;
    public int direction;
    public boolean isMoving;

    public String adhotAnimation;// if not null, play this animation
    public float adhotAnimationDuration;// if not null, play this animation
    public float adhotAnimationTime;// if not null, play this animation

    public void setCurrentAnimation(String name, float adhotAnimationDuration){
        this.adhotAnimation = name;
        this.adhotAnimationDuration = adhotAnimationDuration;
        this.adhotAnimationTime = 0;
    }

    @Override
    public void reset() {
        animationHolder = null;
        direction = 0;
        isMoving = false;


        adhotAnimation = null;
        adhotAnimationDuration = 0;
        adhotAnimationTime = 0;
    }
}
