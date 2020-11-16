package com.hhs.jade.ui.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hhs.jade.demo.JadeDemoApplication;
import com.hhs.jade.util.U;

public class Lwjgl3Launcher {

	public static void main(String[] args) {
		createApplication();
	}

	private static Lwjgl3Application createApplication() {
		JadeDemoApplication game = new JadeDemoApplication();
		game.setSync(new Lwjgl3Sync());
		return new Lwjgl3Application(game, getDefaultConfiguration());
	}

	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(U.config().startupWindowWidth, U.config().startupWindowHeight);
		config.useVsync(U.config().vsyncEnabled);
		config.setResizable(U.config().allowResize);
		config.setTitle(U.config().windowTitle);
		config.setWindowIcon("icon/icon_16x.png", "icon/icon_32x.png", "icon/icon_48x.png", "icon/icon_128x.png");
		return config;
	}

}