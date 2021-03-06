package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.guxuede.gm.gdx.actions.DelayAction;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.actions.appearance.ScaleByAction;
import com.guxuede.gm.gdx.actions.movement.ActorMoveToPointAction;
import com.guxuede.gm.gdx.actions.movement.BlinkAction;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;
import com.guxuede.gm.gdx.component.SkillComponent;
import com.guxuede.gm.gdx.system.*;

import java.util.Arrays;

/**
 * Created by guxuede on 2017/5/30 .
 */
public class GdxGameScreen extends ScreenAdapter {

    SpriteBatch spriteBatch;
    PooledEngine engine;
    OrthographicCamera camera;
    Viewport viewport;
    InputMultiplexer inputMultiplexer;

    public GdxGameScreen(){
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        engine = SingletonPooledEngine.instance;
        engine.addSystem(new ScreenClearSystem(1));
        engine.addSystem(new MouseSystem(1000,spriteBatch,viewport,inputMultiplexer));
        engine.addSystem(new ActorStateSystem());
        engine.addSystem(new ActorStateActorAnimationSystem());
        engine.addSystem(new ActorAnimationSystem());
        engine.addSystem(new ActionsSystem());
        engine.addSystem(new StageSystem(spriteBatch,viewport,inputMultiplexer));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PresentableRenderingSystem(300,spriteBatch));
        engine.addSystem(new ActorShadowRenderingSystem(200,spriteBatch));
        engine.addSystem(new ActorLifeBarRenderingSystem(500,spriteBatch));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new DynamicDirectionSystem());
        engine.addSystem(new SensorSystem());
        /*MAP*/
        MapSystem mapSystem = new MapSystem("data/desert1.tmx",spriteBatch,camera);//24,14  27,5
        engine.addSystem(new MapCollisionSystem(mapSystem));
        engine.addSystem(mapSystem);
        //不能同時增加2個相同類型的system
        engine.addSystem(new MapRenderingSystem(101,mapSystem,new int[]{0,1}){});//优先级越低，先画到最底部
//        engine.addSystem(new MapRenderingSystem(600,mapSystem,new int[]{2}){});//优先级越低，先画到最底部
        addLayerAsPresntable(mapSystem.getTiledMapLayer(2));
        engine.addSystem(new AmbianceLightSystem(999));
        /*SOUND*/
        engine.addSystem(new SoundSystem());
        engine.addSystem(new Sound3DSystem(camera));
        engine.addSystem(new SoundOnAnimationSystem(camera));
//        E.create().presentable("Aquatic",-1).pos(100,100).buildToWorld();
//        createPresentableComponentEntity();
//        createPresentableComponentAnimationComponentEntity();
//        createPresentableComponentAnimationComponentActorAnimationComponentEntity();
//        createPresentableComponentAnimationComponentActorAnimationComponentActorStateComponentEntity();
        createActor("Aquatic",100,100);
        createActor("Undead",200,200);
        createActor("Farmer",300,300);
//        createPresentableComponentAnimationComponentActorAnimationComponentActorStateComponentEntity();
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    //测试只有一个PresentableComponent 时可以画出静态图片
    private Entity createPresentableComponentEntity() {
        return E.create().presentable("Aquatic",-1).pos(100,100).buildToWorld();
    }
    //测试只有一个PresentableComponent+一个AnimationComponent时可以画出动态动画
    private Entity createPresentableComponentAnimationComponentEntity() {
        return E.create().animation("Undead").pos(300,300).buildToWorld();
    }
    //测试只有一个PresentableComponent+一个AnimationComponent+一个ActorAnimationComponent时可以画出角色状态动态动画
    private Entity createPresentableComponentAnimationComponentActorAnimationComponentEntity() {
        return E.create().actorAnimation("Undead",ActorAnimationComponent.DOWN,true,0).pos(100,20).buildToWorld();
    }

    //测试只有一个PresentableComponent+一个AnimationComponent+一个ActorAnimationComponent+一个ActorStateComponent时可以画出以一定速度移动地角色状态动态动画
    private Entity createPresentableComponentAnimationComponentActorAnimationComponentActorStateComponentEntity() {
        return E.create().actorState(ActorAnimationComponent.RIGHT,true,0,0,-200f,-200f)
                .actorAnimation("Undead").pos(0,0).actions(new SequenceAction(new DelayAction(2),new BlinkAction(),new ScaleByAction(1,1,10f))).buildToWorld();
    }

    private void createActor(String name, float x,float y) {
        Entity entity = E.create().actorState().actorAnimation(name).asActor().pos(x,y).actions().bounds(32,32).blood(100,65).sensor().buildToWorld();
        SkillComponent skillComponent = engine.createComponent(SkillComponent.class);
        skillComponent.skills.add(ResourceManager.getSkillById("burstFire"));
        skillComponent.skills.add(ResourceManager.getSkillById("burstFire1"));
        skillComponent.skills.add(ResourceManager.getSkillById("meteorite"));
        skillComponent.skills.add(ResourceManager.getSkillById("fireBall"));
        skillComponent.skills.add(ResourceManager.getSkillById("blink"));
        entity.add(skillComponent);
        //Entity entity0 = E.create(engine).actorState().actorAnimation("Undead").asActor().pos(300,0).bounds().buildToWorld();
        //E.create(engine).animation("lightningLine1").pos(0,0).actions(new SequenceAction(new ScaleToLineAction(entity,entity0, 100), new RemoveActorAction())).buildToWorld();
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



    public void addLayerAsPresntable(TiledMapTileLayer layer){
        float unitScale = 1;
        final int layerWidth = layer.getWidth();
        final int layerHeight = layer.getHeight();

        final float layerTileWidth = layer.getTileWidth() * unitScale;
        final float layerTileHeight = layer.getTileHeight() * unitScale;

        final int minX = 0;
        final int maxX = layerWidth;

        final int minY = 0;
        final int maxY = layerHeight;

        for (int y = maxY - 1; y >= minY; y--) {
            for (int x = maxX - 1; x >= minX; x--) {
                final TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell == null) continue;
                final TiledMapTile tile = cell.getTile();

                if (tile != null) {
                    float x1 = x * layerTileWidth;
                    float y1 = y * layerTileHeight;
                    int zOffSet = tile.getProperties().get("zOffSet",0,Integer.class);
                    float z = (int) -(y1+ layerTileHeight) + (layerTileHeight*zOffSet);

                    EntityEditor editor = E.create().textureRegionPresentable(tile.getTextureRegion(), z).pos(x1, y1);
                    if(tile instanceof AnimatedTiledMapTile){
                        AnimatedTiledMapTile animatedTiledMapTile = (AnimatedTiledMapTile) tile;
                        StaticTiledMapTile[] frameTiles = animatedTiledMapTile.getFrameTiles();
                        Array<TextureRegion> textureRegions = new Array<TextureRegion>(frameTiles.length);
                        for(int i=0;i<frameTiles.length;i++){
                            StaticTiledMapTile frameTile = frameTiles[i];
                            textureRegions.add(frameTile.getTextureRegion());
                        }
                        Animation animation = new Animation(animatedTiledMapTile.getAnimationIntervals()[0]/1000f, textureRegions, Animation.PlayMode.LOOP);
                        editor.animation("tile-"+animatedTiledMapTile.getId(),animation);
                    }
                    editor.buildToWorld();
                }
            }
        }
    }

}
