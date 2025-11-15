package com.guxuede.gm.gdx.actor.parser.actor;

import com.guxuede.gm.gdx.actor.parser.ParseContext;

//
public class SingleActor3FrameRpgSpriteParser extends MultipleActor4FrameRpgSpriteParser {

    @Override
    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return 3;
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2};
    }
}
