package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParseContext {
    public String textureName;
    public TextureRegion texture;

    public float frameDuration;
    public float width;
    public float height;
    public float alpha;
    public float rotation;

    public float scaleX;
    public float scaleY;
    //精灵展示位置偏移
    public float drawOffSetX, drawOffSetY;

    public float textureOffSetX = 0;
    public float textureOffSetY = 0;

    public String sound;

    

}
