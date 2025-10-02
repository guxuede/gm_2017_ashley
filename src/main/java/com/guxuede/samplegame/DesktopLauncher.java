package com.guxuede.samplegame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.guxuede.gm.gdx.basic.libgdx.GdxGame;
import io.netty.util.ResourceLeakDetector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Demo Game";
		config.width = 1024;
		config.height = 800;
		new LwjglApplication(new GdxGame(), config);
	}
}
