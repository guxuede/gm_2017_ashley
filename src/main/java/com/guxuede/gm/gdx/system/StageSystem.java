package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.guxuede.gm.gdx.InputListenerMultiplexer;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.component.ActorComponent;
import com.guxuede.gm.gdx.component.ActorStateComponent;

import static com.guxuede.gm.gdx.Mappers.actorCM;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class StageSystem extends IteratingSystem {
    private float speed = 5.0f;
    private static final Family family = Family.all(ActorComponent.class).get();

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Stage stage;
    private Entity viewActor;

    public StageSystem(SpriteBatch batch, Viewport viewport) {
        super(family);
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.stage = new Stage(viewport,batch);
        this.stage.setDebugAll(true);
        this.spriteBatch = batch;
        Gdx.input.setInputProcessor(stage);
        InputListenerMultiplexer inputListenerMultiplexer = new InputListenerMultiplexer();
        inputListenerMultiplexer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor actor = event.getTarget();
                if(actor !=null && actor.getUserObject() != viewActor && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                    viewActor = (Entity) actor.getUserObject();
                    return true;
                }
                return false;
            }
        });
        inputListenerMultiplexer.addListener(new InputListener() {
            @Override
            public boolean handle(Event e) {
                if(viewActor != null && e instanceof InputEvent){
                    ActorStateComponent actorStateComponent = Mappers.actorStateCM.get(viewActor);
                    if(actorStateComponent!=null){
                        return actorStateComponent.handleInput(viewActor,(InputEvent) e);
                    }
                }
                return super.handle(e);
            }
        });
        stage.addListener(inputListenerMultiplexer);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void addedToEngine(final Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(family, 0, new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                ActorComponent actorComponent = actorCM.get(entity);
                actorComponent.setUserObject(entity);
                actorComponent.setWidth(50);
                actorComponent.setHeight(50);
                stage.addActor(actorComponent);
            }
            @Override
            public void entityRemoved(Entity entity) {
                actorCM.get(entity).remove();
            }
        });
    }

    @Override
    public void update(float deltaTime) {
        updateCamera();
        stage.act(deltaTime);
        stage.draw();
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    private void updateCamera(){
        Vector2 position = null;
        if(viewActor!=null){
            position = Mappers.positionCM.get(viewActor).position;
        }
        if(position!=null){
            camera.position.x = position.x;
            camera.position.y = position.y;
            camera.update();
            spriteBatch.setProjectionMatrix(camera.combined);
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
                camera.translate(0, speed);
                camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){
                camera.translate(0, -speed);camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.HOME)){
                camera.translate(speed, 0);camera.update();
            }else if(Gdx.input.isKeyPressed(Input.Keys.END)){
                camera.translate(-speed, 0);camera.update();
            }
        }
    }
}
