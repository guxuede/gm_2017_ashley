package com.guxuede.gm.gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by guxuede on 2016/6/1 .
 */
public class GdxSprite extends Sprite {
    private static AtomicLong atomicLong = new AtomicLong();
    public final long id = atomicLong.getAndIncrement();
    public float drawOffSetX, drawOffSetY;

    public GdxSprite() {
        super();
    }

    public GdxSprite(Texture texture) {
        super(texture);
    }

    public GdxSprite(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
    }

    public GdxSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public GdxSprite(TextureRegion region) {
        super(region);
    }

    public GdxSprite(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public GdxSprite(Sprite sprite) {
        super(sprite);
    }

    public void setDrawOffSetX(float offSetX, float offSetY) {
        this.drawOffSetX = offSetX;
        this.drawOffSetY = offSetY;
    }

    @Override
    public void setX(float x) {
        super.setX(x  - this.getRegionWidth() / 2 + drawOffSetX);
    }

    @Override
    public void setY(float y) {
        super.setY(y  - this.getRegionHeight() / 2 + drawOffSetY);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - this.getRegionWidth() / 2 + drawOffSetX, y - this.getRegionHeight()/2 + drawOffSetY);
    }


    public void draw(Batch batch, float alphaModulation, float rotation, float scaleX, float scaleY, Color color) {
        float oldRotation = getRotation();
        float oldScaleX = getScaleX();
        float oldScaleY = getScaleY();

        setColor(color);
        setRotation(rotation+oldRotation);
        setScale(oldScaleX * scaleX, oldScaleY * scaleY);

        draw(batch,alphaModulation);

        setRotation(oldRotation);
        setScale(oldScaleX, oldScaleY);
    }
}
