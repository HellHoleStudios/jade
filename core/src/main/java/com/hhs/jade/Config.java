/*
 * Java Danmaku Engine
 * Copyright (c) 2020-2020 Hell Hole Studios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hhs.jade;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Scaling;

public class Config {

    public static Config config;

    public int logLevel;
    public boolean autoload;

    public int fps;
    public int fpsLimit;
    public boolean vsyncEnabled;
    public String windowTitle;
    public float screenWidth;
    public float screenHeight;
    public int startupWindowWidth;
    public int startupWindowHeight;
    public int frameWidth;
    public int frameHeight;
    public float frameOffsetX;
    public float frameOffsetY;
    public boolean allowFullScreen;
    public boolean allowResize;
    public boolean startupFullScreen;
    public Scaling windowScaling;
    public TextureFilter textureMinFilter;
    public TextureFilter textureMagFilter;

    public float w;
    public float h;
    public float originX;
    public float originY;
    public float deleteDistance;
    public float safeDistance;
    public boolean orthoCircleCollision;
    public boolean invulnerable;
    public int cleanupBulletCount;
    public int cleanupBlankCount;
    public String defaultShotSheet;
    public boolean allowSpeedUpOutOfReplay;
    public int speedUpMultiplier;
    public float musicVolume;
    public float SEVolume;

    public int[] keyDown;
    public int[] keyUp;
    public int[] keyLeft;
    public int[] keyRight;
    public int[] keySelect;
    public int[] keyCancel;
    public int[] keyShot;
    public int[] keySlow;
    public int[] keyBomb;
    public int[] keyPause;
    public int[] keyCustom;

    public String UIFont;
    public Color UIFontColor;
    public float UIFontBorderWidth;
    public Color UIFontBorderColor;
    public boolean debugActorLayout;

    public Config() {

    }

    public void setDefault() {
        logLevel = Logger.DEBUG;
        autoload = true;

        fps = 60;
        fpsLimit = 0;
        vsyncEnabled = true;
        windowTitle = "\u6771\u65b9\u8abf\u8a66\u796d\u3000\uff5e Demonstration of the Jade Engine.";
        screenWidth = 1280;
        screenHeight = 960;
        startupWindowWidth = 960;
        startupWindowHeight = 720;
        frameWidth = 768;
        frameHeight = 896;
        frameOffsetX = 64;
        frameOffsetY = 32;
        allowFullScreen = true;
        allowResize = true;
        startupFullScreen = false;
        windowScaling = Scaling.fit;
        textureMagFilter = TextureFilter.Linear;
        textureMinFilter = TextureFilter.Linear;

        w = 384;
        h = 448;
        originX = w / 2;
        originY = h;
        deleteDistance = 256;
        safeDistance = 16;
        orthoCircleCollision = true;
        invulnerable = false;
        cleanupBulletCount = 8192;
        cleanupBlankCount = 512;
        defaultShotSheet = "default_shot.shot";
        allowSpeedUpOutOfReplay = true;
        speedUpMultiplier = 4;
        musicVolume = 1;
        SEVolume = 1;

        keyDown = new int[]{Keys.DOWN, Keys.S};
        keyUp = new int[]{Keys.UP, Keys.W};
        keyLeft = new int[]{Keys.LEFT, Keys.A};
        keyRight = new int[]{Keys.RIGHT, Keys.D};
        keySelect = new int[]{Keys.Z, Keys.ENTER};
        keyCancel = new int[]{Keys.X, Keys.ESCAPE};
        keyShot = new int[]{Keys.Z};
        keySlow = new int[]{Keys.SHIFT_LEFT};
        keyBomb = new int[]{Keys.X};
        keyPause = new int[]{Keys.ESCAPE};
        keyCustom = new int[]{Keys.C};

        UIFont = "font/SongSC.ttf";
        UIFontColor = Color.WHITE;
        UIFontBorderWidth = 4;
        UIFontBorderColor = Color.BLACK;
        debugActorLayout = false;
    }
}
