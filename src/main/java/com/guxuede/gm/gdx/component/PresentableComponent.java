package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PresentableComponent implements Component {
    public boolean visible = true;
    public float scaleX = 1, scaleY = 1;
    public float rotation;
    public float originX, originY;
    public final Color color = new Color(1, 1, 1, 1);
    public TextureRegion region = null;
    public float zIndex; //值越大画的越靠前(越高，越在其他基色之上，不易遮挡)
    public float baseZIndex;

    //偏移值
    public float drawOffSetX;
    public float drawOffSetY;
}
