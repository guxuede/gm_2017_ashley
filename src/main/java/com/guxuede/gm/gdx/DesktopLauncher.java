package com.guxuede.gm.gdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gdx Game";
		config.width = 700;
		config.height = 500;
		new LwjglApplication(new GdxGame(), config);
	}
}
