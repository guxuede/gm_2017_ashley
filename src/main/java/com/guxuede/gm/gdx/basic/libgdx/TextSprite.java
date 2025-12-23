package com.guxuede.gm.gdx.basic.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.guxuede.gm.gdx.ResourceManager;

public class TextSprite extends GdxSprite {

    private String text;

    public TextSprite(String text) {
        this.text = text;
    }

    @Override
    public void draw(Batch batch, float alphaModulation, float rotation, float scaleX, float scaleY, Color color) {
        ResourceManager.myFont.setColor(color); // Set the font color to red
        ResourceManager.myFont.getData().setScale(scaleX,scaleY);
        ResourceManager.myFont.draw(batch, text, getX(), getY());
    }
}
