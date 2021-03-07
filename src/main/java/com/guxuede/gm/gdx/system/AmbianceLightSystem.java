package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.TempObjects;
import com.guxuede.gm.gdx.component.*;

public class AmbianceLightSystem extends EntitySystem {

    private static final Family family = Family.all(AmbianceLightComponent.class).get();
    private SpriteBatch batch;
    private FrameBuffer frameBuffer;
    private TextureRegion lightBufferRegion;

    private Color ambianceColor;

    private TextureRegion lightRegion;



    private Array<Light> lights = new Array<Light>();

    public AmbianceLightSystem(int priority){
        this.priority= priority;
        Texture lightTex = ResourceManager.getTexture("data/light");
        lightRegion = new TextureRegion(lightTex);
        ambianceColor = new Color(0.3f, 0.38f, 0.6f, 1);
        createLight(0,0);
        createLight(100,100);
        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void update(float deltaTime) {
        frameBuffer.begin();
        batch.enableBlending();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        Gdx.gl.glClearColor(ambianceColor.r, ambianceColor.g, ambianceColor.b, ambianceColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawEntryLight();
        batch.end();
        frameBuffer.end();
        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
        batch.setColor(Color.WHITE);
        batch.begin();
        batch.draw(lightBufferRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }


    public void drawEntryLight(){
        final Vector2 tVector2 = TempObjects.temp1Vector2;
        for(Light actor : lights){
            batch.setColor(actor.color);
            tVector2.set(actor.x,actor.y);
            float visualRadius = actor.height;
            batch.draw(lightRegion ,tVector2.x-visualRadius/2, Gdx.graphics.getHeight()-tVector2.y-visualRadius/2, visualRadius, visualRadius);
        }
    }


    private void resize(int width, int height) {
        // Fakedlight system (alpha blending)
        // if frameBuffer was created before, dispose, we recreate a new one
        if (frameBuffer != null){
            frameBuffer.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
        batch = new SpriteBatch();
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        frameBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        lightBufferRegion = new TextureRegion(frameBuffer.getColorBufferTexture(), 0, 0, width, height);


        lightBufferRegion.flip(false, true);
    }

    private void createLight(float x, float y) {
        lights.add(new Light(x, y));
    }

    private static class Light {
        public float x = 0;
        public float y = 0;
        public float width = 16;
        public float height = 16;
        public float rotation;
        public Color color = new Color(0.9f, 0.4f, 0f, 1f);

        public Light (float x, float y) {
            this.x = x;
            this.y = y;
            width = height = MathUtils.random(100, 300);
            color.set(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
            rotation = MathUtils.random(360);
        }
    }

}
