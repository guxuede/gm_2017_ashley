package com.guxuede.samplegame;

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
import com.guxuede.gm.gdx.basic.libgdx.SingletonPooledEngine;
import com.guxuede.gm.gdx.basic.libgdx.TempObjects;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.entityEdit.EntityEditor;
import com.guxuede.gm.gdx.system.physics.*;
import com.guxuede.gm.gdx.system.render.*;
import com.guxuede.gm.net.system.EntityNetClientPackSystem;
import com.guxuede.gm.net.system.GlobalNetPackSystem;

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
        engine.addSystem(new AttributeModifierSystemBefore(0));
        engine.addSystem(new MouseSystem(1000,spriteBatch,viewport,inputMultiplexer));
        engine.addSystem(new ActorStateSystem());
        engine.addSystem(new ActorStateActorAnimationSystem());
        engine.addSystem(new ActorAnimationSystem());
        engine.addSystem(new ActionsSystem());
        engine.addSystem(new StageSystem(2000, spriteBatch,viewport,inputMultiplexer));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PresentableRenderingSystem(300,spriteBatch));
        engine.addSystem(new ActorShadowRenderingSystem(200,spriteBatch));
        engine.addSystem(new ActorLifeBarRenderingSystem(500,spriteBatch));
        engine.addSystem(new HurtSystem());
        engine.addSystem(new SensorMovementSystem());
        engine.addSystem(new DynamicDirectionSystem());
        engine.addSystem(new SensorSystem());
        engine.addSystem(new EntityNetClientPackSystem());
        /*MAP*/
        engine.addSystem(new TiledMapManagerSystem(spriteBatch,camera));
        engine.addSystem(new TiledMapRenderingSystem(101,new int[]{0,1}){});//优先级越低，先画到最底部         //不能同時增加2個相同類型的system
        engine.addSystem(new TiledMapRenderingSystem(600,new int[]{2}){});//优先级越低，先画到最底部
        engine.addSystem(new MapCollisionSystem());

//        addLayerAsPresntable(mapDataSystem.getTiledMapLayer(2));
//        engine.addSystem(new AmbianceLightSystem(999));
        /*SOUND*/
        engine.addSystem(new SoundSystem());
        engine.addSystem(new Sound3DSystem(camera));
        engine.addSystem(new SoundOnAnimationSystem(camera));
        engine.addSystem(new GlobalNetPackSystem(engine));
        engine.addSystem(new CommandSystem(engine));
        engine.addSystem(new AttributeModifierSystemAfter(10000));
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        TempObjects.temp0Vector3.set(camera.position);
        viewport.setScreenSize(width,height);
        viewport.setWorldSize(width,height);
        viewport.update(width,height);
        camera.setToOrtho(false, width, height);
        camera.position.set(TempObjects.temp0Vector3);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        engine.getSystem(StageSystem.class).onScreenResize(width, height);
    }



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
