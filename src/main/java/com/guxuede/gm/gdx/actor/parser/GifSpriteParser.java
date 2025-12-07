package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;

public class GifSpriteParser extends DefaultSpriteParser {

    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        Animation<TextureRegion> textureRegionAnimation = GifDecoder.loadGIFAnimation(parseContext, Animation.PlayMode.NORMAL,null,Gdx.files.internal(parseContext.textureName).read());


        animationHolder.addAnimation(AnimationHolder.WALK, AnimationHolder.DIRECTION_DOWN,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_LEFT,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_RIGHT,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK,AnimationHolder.DIRECTION_UP,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_DOWN,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_LEFT,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_RIGHT,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.IDLE,AnimationHolder.DIRECTION_UP,textureRegionAnimation);
    }
}
