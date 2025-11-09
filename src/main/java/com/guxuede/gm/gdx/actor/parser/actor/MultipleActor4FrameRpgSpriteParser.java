package com.guxuede.gm.gdx.actor.parser.actor;

import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.actor.parser.ParseContext;

//
public class MultipleActor4FrameRpgSpriteParser extends SingleActor4FrameRpgSpriteParser {

    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return (int) (ResourceManager.getTextureRegion(parseContext.textureName).getRegionWidth()/parseContext.width);
    }

    protected int getNumberOfActor(JsonValue jsonValue, ParseContext parseContext) {
        return jsonValue.getInt("actorNumber", 0);
    }
}
