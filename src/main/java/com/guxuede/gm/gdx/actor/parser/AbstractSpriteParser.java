package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.utils.JsonValue;

public abstract class AbstractSpriteParser {

    public abstract ActorHolder parseActor(JsonValue jsonValue);
}
