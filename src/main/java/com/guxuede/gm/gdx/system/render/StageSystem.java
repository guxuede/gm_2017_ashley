package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.basic.libgdx.InputListenerMultiplexer;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.component.ActorComponent;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import org.apache.commons.lang3.StringUtils;

import static com.guxuede.gm.gdx.entityEdit.Mappers.actorCM;

/**
 * Created by guxuede on 2017/5/29 .
 */
public class StageSystem extends EntitySystem {

    private float speed = 5.0f;
    private static final Family family = Family.all(ActorComponent.class).get();

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Stage stage;
    private Entity viewActor;

    //~~~~~~~~~~~~~~~~~~UI
    private OrthographicCamera uiCamera;
    private FitViewport uiVviewport;
    private Stage uiStage;
    private Table chatHistoryBox;
    private TextField chatText;//pixthulhu kenney-pixel



    public StageSystem(int priority, SpriteBatch batch, Viewport viewport, InputMultiplexer inputMultiplexer) {
        this.priority = priority;
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.stage = new Stage(viewport,batch);
        this.stage.setDebugAll(false);

        this.spriteBatch = batch;
        initUI();
        inputMultiplexer.addProcessor(this.stage);
        inputMultiplexer.addProcessor(this.uiStage);


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

    public Entity getViewActor() {
        return viewActor;
    }

    public void setViewActor(Entity viewActor) {
        this.viewActor = viewActor;
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
        uiStage.act();
        uiStage.draw();
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


    public void onScreenResize(int width, int height){
        uiVviewport.setScreenSize(width,height);
        uiVviewport.setWorldSize(width,height);
        uiVviewport.update(width,height);
        uiCamera.setToOrtho(false, width, height);
        uiCamera.update();
    }

    private void initUI(){
        uiCamera = new OrthographicCamera();
        uiVviewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),uiCamera);
        uiVviewport.apply();
        this.uiStage = new Stage(uiVviewport);
        this.uiStage.setDebugAll(false);
        chatHistoryBox = new Table(ResourceManager.skin);
        chatHistoryBox.padLeft(15);
        chatHistoryBox.setWidth(400);
        chatHistoryBox.setHeight(600);
        chatHistoryBox.setBackground((Drawable) null);
        chatHistoryBox.setPosition(0,130);
        chatHistoryBox.align(Align.bottomLeft);

        chatText = new TextField("/login password=123 username=guxuede", ResourceManager.skin);
        chatText.setWidth(400);
        chatText.setHeight(30);
        chatText.setFillParent(true);
        chatText.setVisible(false);
        chatText.setPosition(15,100);

        chatHistoryBox.setVisible(true);
        uiStage.addActor(chatHistoryBox);
        uiStage.addActor(chatText);

        InputListenerMultiplexer inputListenerMultiplexer = new InputListenerMultiplexer();
        inputListenerMultiplexer.addListener(new InputListener() {

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ENTER){
                    if(chatText.isVisible()) {
                        String text = chatText.getText();
                        if(StringUtils.isNoneBlank(text)){
                            System.out.println("input box:" + text);
                            getEngine().getSystem(CommandSystem.class).executeCommand(viewActor, text);
                        }
                    }
                    chatText.setVisible(!chatText.isVisible());
                    return true;
                }
                return false;
            }
        });
        uiStage.addListener(inputListenerMultiplexer);
    }

    public void addUserMessageToUI(String text) {
        chatHistoryBox.row().left();
        Label label = new Label(text,ResourceManager.skin);
        Color color = label.getColor();
        color.a = 0;
        label.addAction(new SequenceAction(Actions.fadeIn(0.5f), new DelayAction(4), Actions.fadeOut(4),new RemoveActorAction()));
        chatHistoryBox.add(label).left();
    }
}
