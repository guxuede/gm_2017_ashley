
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;

public class ActorAnimationComponent implements Component {
    public static final int STOP=0, DOWN=1,LEFT=2,RIGHT=3,UP=4;

	public AnimationHolder animationHolder;
    public int direction;
    public boolean isMoving;

    public String hotAnimation;// if not null, play this animation


}
