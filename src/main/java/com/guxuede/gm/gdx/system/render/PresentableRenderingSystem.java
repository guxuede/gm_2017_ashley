package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.basic.libgdx.GdxSprite;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

import java.util.Comparator;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class PresentableRenderingSystem extends SortedIteratingSystem {

    public static final float alphaModulation = 1f;
    private static final Family family = Family.all(PresentableComponent.class, PositionComponent.class).get();
    private SpriteBatch batch;
    private Color tmpColor = new Color();

    private static final Comparator<Entity> comparator = new Comparator<Entity>() {
        @Override
        public int compare(Entity entityA, Entity entityB) {
            PresentableComponent pA = Mappers.presentableCM.get(entityA);
            PresentableComponent pB = Mappers.presentableCM.get(entityB);
            return (int) Math.signum((pA.zIndex + pA.baseZIndex) - (pB.zIndex + pB.baseZIndex));
        }
    };

    public PresentableRenderingSystem(int priority, SpriteBatch spriteBatch) {
        super(family, comparator);
        this.priority = priority;
        batch = spriteBatch;
    }

    @Override
    public void update(float deltaTime) {
        forceSort();
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent p = Mappers.positionCM.get(entity);
        PresentableComponent d = Mappers.presentableCM.get(entity);
        TextureRegion t = d.region;
        if (t != null) {
            if (t instanceof GdxSprite) {
                GdxSprite sprite = (GdxSprite) t;
                sprite.setPosition(p.position.x + d.drawOffSetX, p.position.y + d.drawOffSetY +  p.high);
                sprite.draw(batch, alphaModulation, d.rotation, d.scaleX, d.scaleY, d.color);
            } else {
                tmpColor.set(d.color).a*=alphaModulation;
                batch.setColor(tmpColor);
                batch.draw(t, p.position.x + d.drawOffSetX, p.position.y +  + d.drawOffSetY +  p.high, d.originX, d.originY, t.getRegionWidth(), t.getRegionHeight(), d.scaleX, d.scaleY, d.rotation);
            }
        }
    }

}
