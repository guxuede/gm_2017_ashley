package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.PresentableComponent;

/**
 * Created by guxuede on 2017/5/31 .
 */
public class ActorShadowRenderingSystem extends IteratingSystem {

    private static final Family family = Family.all(ActorStateComponent.class, PositionComponent.class, PresentableComponent.class).get();

    SpriteBatch spriteBatch;
    Sprite shadow;

    public ActorShadowRenderingSystem(int priority,SpriteBatch spriteBatch){
        super(family);
        this.spriteBatch = spriteBatch;
        this.priority = priority;
        shadow = ResourceManager.shadow;
    }

    @Override
    public void update(float deltaTime) {
        spriteBatch.begin();
        super.update(deltaTime);
        spriteBatch.end();
    }

    static final float h = 32;


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        float scaleY = positionComponent.height /100;
        float scaleX = positionComponent.height /100;
//        spriteBatch.draw(ResourceManager.shadow, positionComponent.position.drawOffSetX-w/2, positionComponent.position.drawOffSetY-16-h, w/2, h/2, 64, 32, 1-scale, 1-scale, 0);
        drawTextureAtCenterPosWithOwnWH(spriteBatch,ResourceManager.shadow,positionComponent.position.x,positionComponent.position.y, presentableComponent.region.getRegionWidth(),h, 1-scaleY, 1-scaleX);
        //spriteBatch.draw(ResourceManager.shadow,positionComponent.position.drawOffSetX - w,positionComponent.position.drawOffSetY-h-16,64,32);
//        drawTextureAtCenterPos(spriteBatch,ResourceManager.shadow,positionComponent.position.drawOffSetX,positionComponent.position.drawOffSetY-16,3,2);
    }

    public static void drawTextureAtCenterPosWithOwnWH(SpriteBatch spriteBatch, TextureRegion textureRegion,float x,float y, float w,float h,  float scalex, float scaley){
        float regionWidth = w;
        float regionHeight = h;
        float halfWidth = regionWidth / 2;
        float halfHeight = regionHeight / 2;
        spriteBatch.draw(textureRegion, x- halfWidth, y- halfHeight, halfWidth, halfHeight, regionWidth, regionHeight, scalex,scaley, 0);
    }

    public static void drawTextureAtCenterPos(SpriteBatch spriteBatch, TextureRegion textureRegion,float x,float y, float scalex, float scaley){
        int regionWidth = textureRegion.getRegionWidth();
        int regionHeight = textureRegion.getRegionHeight();
        int halfWidth = regionWidth / 2;
        int halfHeight = regionHeight / 2;
        spriteBatch.draw(textureRegion, x- halfWidth, y- halfHeight, halfWidth, halfHeight, regionWidth, regionHeight, scalex,scaley, 0);
    }
}
