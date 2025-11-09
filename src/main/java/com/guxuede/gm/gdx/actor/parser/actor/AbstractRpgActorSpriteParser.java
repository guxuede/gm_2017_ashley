package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.actor.parser.ActorHolder;
import com.guxuede.gm.gdx.actor.parser.DefaultSpriteParser;
import com.guxuede.gm.gdx.actor.parser.ParseContext;

public class AbstractRpgActorSpriteParser extends DefaultSpriteParser {

    @Override
    public ActorHolder parseActor(JsonValue jsonValue) {
        ActorHolder actorHolder = super.parseActor(jsonValue);
        actorHolder.shadowWidth = actorHolder.animationHolder.width*0.7f;
        return actorHolder;
    }


    @Override
    protected void parseActorAttributes(JsonValue jsonValue, ParseContext parseContext, ActorHolder actorHolder) {
        actorHolder.age = 18;
        actorHolder.blood = 100;
        actorHolder.bounds = new Rectangle(0,0, parseContext.width, parseContext.height);
    }

    @Override
    protected ParseContext extendParentParseContext(ParseContext parseContext, JsonValue animJ) {
        ParseContext parseContext1 = super.extendParentParseContext(parseContext, animJ);
        parseContext1.drawOffSetY = parseContext1.height/2; // by default. RPG marker actor
        return parseContext1;
    }
}


