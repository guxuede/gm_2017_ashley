
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;

public class ActorAnimationComponent implements Component , Pool.Poolable{

	public AnimationHolder animationHolder;
    public float directionInDegrees;
    public boolean isMoving;

    public String currentAnimation;// if not null, play this animation
    public float currentAnimationDuration;// 动画时长
    public float currentAnimationTime;// 当前动画已经播放了多长

    public void playAnimation(String name, float duration){
        this.currentAnimation = name;
        this.currentAnimationDuration = duration;
        this.currentAnimationTime = 0;
    }

    @Override
    public void reset() {
        animationHolder = null;
        directionInDegrees = 0;
        isMoving = false;


        currentAnimation = null;
        currentAnimationDuration = 0;
        currentAnimationTime = 0;
    }
}
