package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.GdxSprite;
import com.guxuede.gm.gdx.Mappers;
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
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        TextureRegion textureRegion = presentableComponent.region;
        if (textureRegion != null) {
            if (textureRegion instanceof GdxSprite) {
                GdxSprite sprite = (GdxSprite) textureRegion;
                sprite.setPosition(positionComponent.position.x + presentableComponent.drawOffSetX, positionComponent.position.y + presentableComponent.drawOffSetY);
                sprite.draw(batch, alphaModulation, presentableComponent.rotation, presentableComponent.scaleX, presentableComponent.scaleY, presentableComponent.color);
            } else {
                tmpColor.set(presentableComponent.color).a*=alphaModulation;
                batch.setColor(tmpColor);
                batch.draw(textureRegion, positionComponent.position.x + presentableComponent.drawOffSetX, positionComponent.position.y +  + presentableComponent.drawOffSetY, presentableComponent.originX, presentableComponent.originY, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), presentableComponent.scaleX, presentableComponent.scaleY, presentableComponent.rotation);
            }
        }
    }

}
