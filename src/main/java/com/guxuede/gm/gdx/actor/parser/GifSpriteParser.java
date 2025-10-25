package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;

public class GifSpriteParser extends DefaultSpriteParser {

    @Override
    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        Animation<TextureRegion> textureRegionAnimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL,null,Gdx.files.internal(parseContext.textureName).read());
        animationHolder.addAnimation(AnimationHolder.WALK_DOWN_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK_LEFT_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK_RIGHT_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.WALK_UP_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.STOP_DOWN_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.STOP_LEFT_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.STOP_RIGHT_ANIMATION,textureRegionAnimation);
        animationHolder.addAnimation(AnimationHolder.STOP_UP_ANIMATION,textureRegionAnimation);
    }
}
