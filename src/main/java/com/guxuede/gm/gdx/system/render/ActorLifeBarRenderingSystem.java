package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.BloodComponent;
import com.guxuede.gm.gdx.component.PositionComponent;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class ActorLifeBarRenderingSystem extends IteratingSystem {

    public static final float BLOOD_WIDTH = 50;
    public static final float BLOOD_HEIGHT = 6;
    private static final Family family = Family.all(PositionComponent.class,BloodComponent.class).get();
    SpriteBatch batch;
    private ShapeRenderer shapes;

    public ActorLifeBarRenderingSystem(int priority,SpriteBatch spriteBatch) {
        super(family);
        this.priority = priority;
        batch = spriteBatch;
        shapes = new ShapeRenderer();
        shapes.setAutoShapeType(true);
    }

    @Override
    public void update(float deltaTime) {
        shapes.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        shapes.begin();
        super.update(deltaTime);
        shapes.end();
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionCM.get(entity);
        BloodComponent bloodComponent = Mappers.bloodCM.get(entity);

        //todo

        //if(!animationActor.isInScreen)continue;
        Vector2 p = positionComponent.position;
        float px = p.x - BLOOD_WIDTH/2;
        float py = p.y + 5;

        shapes.set(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.GRAY);
        shapes.rect(px, py, BLOOD_WIDTH, BLOOD_HEIGHT);
        shapes.set(ShapeRenderer.ShapeType.Line);
        shapes.setColor(Color.BLACK);
        shapes.rect(px, py, BLOOD_WIDTH, BLOOD_HEIGHT);

        shapes.set(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.GREEN);
        shapes.rect(px+ 1, py + 1, BLOOD_WIDTH * (bloodComponent.currentHitPoint / bloodComponent.hitPoint) - 1, 4f);
    }

}
