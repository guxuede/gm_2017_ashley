package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.ResourceManager;

public class ParseContext {
    protected String textureName;
    protected TextureRegion texture;

    protected float frameDuration;
    protected float width;
    protected float height;
    protected float alpha;
    protected float rotation;

    protected float scaleX;
    protected float scaleY;
    //精灵展示位置偏移
    protected float drawOffSetX, drawOffSetY;

    protected float textureOffSetX = 0;
    protected float textureOffSetY = 0;

    protected String sound;


}
