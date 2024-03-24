
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;

public class ActorAnimationComponent implements Component , Pool.Poolable{
    public static final int STOP=0, DOWN=1,LEFT=2,RIGHT=3,UP=4;

	public AnimationHolder animationHolder;
    public int direction;
    public boolean isMoving;

    public String hotAnimation;// if not null, play this animation


    @Override
    public void reset() {
        animationHolder = null;
        direction = 0;
        isMoving = false;
        hotAnimation = null;
    }
}
