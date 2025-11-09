package com.guxuede.gm.gdx.actor.parser;

//
public class SingleActor3FrameRpgSpriteParser extends SingleActor4FrameRpgSpriteParser {

    @Override
    protected int getNumberOfFrameOfOneLine(ParseContext parseContext) {
        return 3;
    }

    protected int[] getIdleFrameCycle() {
        return new int[]{1, 2};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2};
    }
}
