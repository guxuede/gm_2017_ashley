package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;


/**
 * Entity has physical bounds.
 */
public class BoundsComponent implements Component , Pool.Poolable{

    public int minx;
    public int miny;
    public int maxx;
    public int maxy;

    public BoundsComponent(final int width, final int height) {
        this.minx =this.miny =0;
        this.maxx =width;
        this.maxy =height;
    }

    public BoundsComponent(final int minx, final int miny, final int maxx, final int maxy) {
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
    }

    public BoundsComponent(TextureRegion region) {
        this.minx = this.maxx =0;
        this.maxx = region.getRegionWidth();
        this.maxy = region.getRegionHeight();
    }

    public BoundsComponent() {
    }

    /** Center X */
    public int cx() { return minx + (maxx - minx)/2; }
    /** Center Y */
    public int cy() { return miny + (maxy - miny)/2; }

    @Override
    public void reset() {
        this.minx = 0;
        this.miny = 0;
        this.maxx = 0;
        this.maxy = 0;
    }
}
