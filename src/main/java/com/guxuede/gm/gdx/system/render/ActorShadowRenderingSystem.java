package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.guxuede.gm.gdx.entityEdit.Mappers;
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

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        PresentableComponent presentableComponent = Mappers.presentableCM.get(entity);
        if(presentableComponent.region == null){
            return;
        }
        shadow.setCenter(positionComponent.position.x,positionComponent.position.y);
        shadow.setScale((presentableComponent.region.getRegionWidth()/shadow.getRegionWidth()),1);
        shadow.draw(spriteBatch,0.7f);
    }
}
