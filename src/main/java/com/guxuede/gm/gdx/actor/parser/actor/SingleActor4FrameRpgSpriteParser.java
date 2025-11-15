package com.guxuede.gm.gdx.actor.parser.actor;

import com.guxuede.gm.gdx.actor.parser.ParseContext;

//
public class SingleActor4FrameRpgSpriteParser extends SingleActor3FrameRpgSpriteParser {

    @Override
    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return 4;
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1,2};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2, 3};
    }
}
