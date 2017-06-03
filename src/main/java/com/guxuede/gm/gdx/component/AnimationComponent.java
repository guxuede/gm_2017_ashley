
package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;
import com.guxuede.gm.gdx.AnimationHolder;

public class AnimationComponent implements Component {
    public static final int STOP=0, DOWN=1,LEFT=2,RIGHT=3,UP=4;
	public Animation animation;
    public float stateTime;
    public final Vector2 animationPosition = new Vector2();
}
