package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.guxuede.gm.gdx.actions.DelayAction;
import com.guxuede.gm.gdx.actions.RemoveActorAction;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.actions.appearance.ScaleByAction;
import com.guxuede.gm.gdx.actions.appearance.ScaleToLineAction;
import com.guxuede.gm.gdx.actions.movement.BlinkAction;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;
import com.guxuede.gm.gdx.system.*;

/**
 * Created by guxuede on 2017/5/30 .
 */
public class GdxGameScreen extends ScreenAdapter {

    SpriteBatch spriteBatch;
    PooledEngine engine;
    OrthographicCamera camera;
    Viewport viewport;

    public GdxGameScreen(){
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        engine = new PooledEngine();
        engine.addSystem(new ActorAnimationSystem());
        engine.addSystem(new ActorStateActorAnimationSystem());
        engine.addSystem(new ActionsSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new ActorStateSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem( new StageSystem(spriteBatch,viewport));
        engine.addSystem(new PresentableRenderingSystem(300,spriteBatch));
        engine.addSystem(new ActorShadowRenderingSystem(200,spriteBatch));
        engine.addSystem(new ActorLifeBarRenderingSystem(500,spriteBatch));
        MapSystem mapSystem = new MapSystem("data/desert1.tmx",spriteBatch,camera);
        engine.addSystem(mapSystem);
        engine.addSystem(new MapRenderingSystem(100,mapSystem,new int[]{0}));//优先级越低，先画到最底部
        engine.addSystem(new MapRenderingSystem(100,mapSystem,new int[]{1}));
        engine.addSystem(new MapRenderingSystem(101,mapSystem,new int[]{2}));
        createPresentableComponentEntity();
        createPresentableComponentAnimationComponentEntity();
        createPresentableComponentAnimationComponentActorAnimationComponentEntity();
        createPresentableComponentAnimationComponentActorAnimationComponentActorStateComponentEntity();
        createActor();
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
        processKeyEvent();
    }

    //测试只有一个PresentableComponent 时可以画出静态图片
    private Entity createPresentableComponentEntity() {
        return E.create(engine).presentable("Aquatic",-1).pos(100,100).buildToWorld();
    }
    //测试只有一个PresentableComponent+一个AnimationComponent时可以画出动态动画
    private Entity createPresentableComponentAnimationComponentEntity() {
        return E.create(engine).animation("Undead").pos(300,300).buildToWorld();
    }
    //测试只有一个PresentableComponent+一个AnimationComponent+一个ActorAnimationComponent时可以画出角色状态动态动画
    private Entity createPresentableComponentAnimationComponentActorAnimationComponentEntity() {
        return E.create(engine).actorAnimation("Undead",ActorAnimationComponent.DOWN,true,0).pos(100,20).buildToWorld();
    }

    //测试只有一个PresentableComponent+一个AnimationComponent+一个ActorAnimationComponent+一个ActorStateComponent时可以画出以一定速度移动地角色状态动态动画
    private Entity createPresentableComponentAnimationComponentActorAnimationComponentActorStateComponentEntity() {
        return E.create(engine).actorState(ActorAnimationComponent.RIGHT,true,5f,5f,10f,10f)
                .actorAnimation("Undead").pos(0,0).actions(new SequenceAction(new DelayAction(2),new BlinkAction(),new ScaleByAction(1,1,10f))).buildToWorld();
    }

    private void createActor() {
        Entity entity = E.create(engine).actorState().actorAnimation("Undead").asActor().pos(0,0).buildToWorld();
        Entity entity0 = E.create(engine).actorState().actorAnimation("Undead").asActor().pos(300,0).buildToWorld();
        E.create(engine).animation("lightningLine1").pos(0,0).actions(new SequenceAction(new ScaleToLineAction(entity,entity0, 100), new RemoveActorAction())).buildToWorld();
    }


    private void processKeyEvent(){
        Application.ApplicationType appType = Gdx.app.getType();
        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        float accelX;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
                accelX = 5f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
                accelX = -5f;
            }else{
                accelX = 0;
            }
        }
        float accelY;
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelY = Gdx.input.getAccelerometerY();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
                accelY = 5f;
            }else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
                accelY = -5f;
            }else{
                accelY = 0;
            }
        }
       final Entity viewActor = engine.getSystem(StageSystem.class).getViewActor();
        if(viewActor!=null){
            Mappers.actorStateCM.get(viewActor).acceleration.set(accelX*11,accelY*11);
        }
    }

//    @Override
//    public void resize(int width, int height) {
//        TempObjects.temp0Vector3.set(camera.position);
//        viewport.setScreenSize(width,height);
//        viewport.setWorldSize(width,height);
//        viewport.update(width,height);
//        camera.setToOrtho(false, width, height);
//        camera.position.set(TempObjects.temp0Vector3);
//        camera.update();
//        spriteBatch.setProjectionMatrix(camera.combined);
//    }
}
