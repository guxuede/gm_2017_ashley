package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.basic.libgdx.ParticleAnimation;
import com.guxuede.gm.gdx.basic.libgdx.ParticleSprite;

import static com.guxuede.gm.gdx.system.render.ParticleEffectManagerSystem.effectsBorrowed;
import static com.guxuede.gm.gdx.system.render.ParticleEffectManagerSystem.particleEffectPoolIntMap;

public class ParticleSpriteParser extends AbstractSpriteParser {


    public ActorHolder parseActor(JsonValue jsonValue){
        ActorHolder actorHolder = new ActorHolder();
        ParseContext parseContext = extendParentParseContext(null, jsonValue);
        String name = jsonValue.getString("name");
        float shadowWidth = jsonValue.getFloat("shadowWidth", 0);
        ///////////////////////////actor attributes///////////////////////////////////
        parseActorAttributes(jsonValue, parseContext, actorHolder);
        /////////////////////////animations/////////////////////////////

        AnimationHolder animationHolder = parseAnimations(jsonValue, parseContext, name);
        /////////////////////////sounds/////////////////////////////
//        parseSounds(jsonValue, animationHolder);
        actorHolder.animationHolder = animationHolder;
        actorHolder.name = name;
        actorHolder.shadowWidth = shadowWidth;
        return actorHolder;
    }

    private void parseActorAttributes(JsonValue jsonValue, ParseContext parseContext, ActorHolder actorHolder) {
    }


    protected AnimationHolder parseAnimations(JsonValue jsonValue, ParseContext parseContext, String name) {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal(parseContext.textureName+".p"), new TextureAtlas(Gdx.files.internal(parseContext.textureName+".atlas")));
//        particleEffect.setEmittersCleanUpBlendFunction(false);
        ParticleEffectPool effectPool = new ParticleEffectPool(particleEffect, 1, 2);

        particleEffectPoolIntMap.put(name.hashCode(), effectPool);

        AnimationHolder animationHolder= new AnimationHolder(){
            @Override
            public Animation getAnimation(int name, int direction) {
                ParticleEffectPool.PooledEffect effect = effectPool.obtain();
                effectsBorrowed.add(effect);
                ParticleSprite particleSprite = new ParticleSprite(effect);
                System.out.println("obtain effect:" + effect);
                return new ParticleAnimation(particleSprite);
            }
        };
        animationHolder.width = (int) parseContext.width;
        animationHolder.height = (int) parseContext.height;
        animationHolder.name = name;

        return animationHolder;
    }

    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext;
        parseContext = new ParseContext();
        parseContext.alpha = 1;
        parseContext.scaleX = 1;
        parseContext.scaleY = 1;
        parseContext.frameDuration = 0.1f;
        return parseContext;
    }

    protected ParseContext extendParentParseContext(ParseContext parseContext, JsonValue animJ){
        if(parseContext == null){
            parseContext = buildDefaultParserContext();
        }
        float frameDuration = animJ.getFloat("frameDuration", parseContext.frameDuration);
        String textureName = animJ.getString("texture", parseContext.textureName);
        float drawOffSetX = animJ.getFloat("drawOffSetX", parseContext.drawOffSetX);
        float drawOffSetY = animJ.getFloat("drawOffSetY", parseContext.drawOffSetY);
        float width = animJ.getFloat("width", parseContext.width);
        float height = animJ.getFloat("height", parseContext.height);
        float alpha = animJ.getFloat("alpha", parseContext.alpha);
        float rotation = animJ.getFloat("rotation", parseContext.rotation);
        float scaleX = parseContext.scaleX, scaleY = parseContext.scaleY;
        if(animJ.has("scale")){
            scaleX = scaleY = animJ.getFloat("scale", 1);
        }
        if(animJ.has("scaleX")){
            scaleX = animJ.getFloat("scaleX", scaleX);
        }
        if(animJ.has("scaleY")){
            scaleY = animJ.getFloat("scaleY", scaleY);
        }

        float textureOffSetX = animJ.getFloat("textureOffSetX",parseContext.textureOffSetX);
        float textureOffSetY = animJ.getFloat("textureOffSetY",parseContext.textureOffSetY);

        String sound = animJ.getString("sound",parseContext.sound);

        ParseContext localParseContext = new ParseContext();
        localParseContext.textureName = textureName;
        localParseContext.width = width;
        localParseContext.height = height;
        localParseContext.alpha = alpha;
        localParseContext.rotation =rotation;
        localParseContext.scaleX = scaleX;
        localParseContext.scaleY = scaleY;
        localParseContext.drawOffSetX =drawOffSetX;
        localParseContext.drawOffSetY =drawOffSetY;
        localParseContext.frameDuration = frameDuration;
        localParseContext.textureOffSetX = textureOffSetX;
        localParseContext.textureOffSetY = textureOffSetY;
        localParseContext.sound = sound;
        return localParseContext;
    }
}
