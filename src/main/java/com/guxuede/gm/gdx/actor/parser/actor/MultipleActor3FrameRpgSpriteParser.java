package com.guxuede.gm.gdx.actor.parser.actor;


public class MultipleActor3FrameRpgSpriteParser extends MultipleActor4FrameRpgSpriteParser {

    protected int[] getIdleFrameCycle() {
        return new int[]{1};
    }

    protected int[] getWalkFrameCycle() {
        return new int[]{0, 1, 2};
    }
}
