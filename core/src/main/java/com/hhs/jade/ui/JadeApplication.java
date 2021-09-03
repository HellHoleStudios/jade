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

package com.hhs.jade.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.WindowedMean;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hhs.jade.ui.grid.Grid;
import com.hhs.jade.ui.screen.FadeableScreen;
import com.hhs.jade.ui.screen.ScreenState;
import com.hhs.jade.util.A;
import com.hhs.jade.util.AutoLoader;
import com.hhs.jade.util.BGM;
import com.hhs.jade.util.U;

public class JadeApplication implements ApplicationListener {

    public Logger logger;
    public Array<FadeableScreen> screens;
    public InputMultiplexer input;
    public InputBlocker blocker;

    private Viewport viewport;
    private Stage st;
    private FPSDisplay fps;
    public WindowedMean fpsCounter;
    public Sync sync;

    public void onStart() {

    }

    public void setSync(Sync sync) {
        this.sync = sync;
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(U.config().logLevel);

        U.game = this;

        if (U.config().startupFullScreen) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }

        this.logger = new Logger("Main", U.config().logLevel);
        logger.info("Game start!");

        A.init();

        this.blocker = new InputBlocker();
        blocker.enable();

        this.input = new InputMultiplexer();
        input.addProcessor(blocker);
        Gdx.input.setInputProcessor(input);

        this.fpsCounter = new WindowedMean(10);
        this.screens = new Array<FadeableScreen>();

        this.viewport = new ScalingViewport(U.config().windowScaling, U.config().screenWidth, U.config().screenHeight);
        this.st = new Stage(viewport);
        st.setDebugAll(U.config().debugActorLayout);

        A.load("font/debug.fnt");
        A.finishLoading();
        this.fps = new FPSDisplay();
        st.addActor(fps);

        onStart();
    }

    @Override
    public void render() {
        BGM.update();

        if (U.config().allowFullScreen && Gdx.input.isKeyPressed(Keys.F4)) {
            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setWindowedMode(U.config().startupWindowWidth, U.config().startupWindowHeight);
            } else {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }
        }

        fpsCounter.addValue(Gdx.graphics.getDeltaTime());
        Grid.globalLock = false;

        U.glClear();

        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState() == ScreenState.FADING_IN) {
                flag1 = true;
                screens.get(i).render(U.safeDeltaTime());
            }
        }
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState() == ScreenState.SHOWN) {
                flag2 = true;
                screens.get(i).render(U.safeDeltaTime());
            }
        }
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState() == ScreenState.FADING_OUT) {
                flag1 = true;
                screens.get(i).render(U.safeDeltaTime());
            }
        }
        if (flag1) {
            blocker.enable();
        } else {
            blocker.disable();
        }
        if (!flag1 && !flag2) {
            Gdx.app.exit();
        }

        st.act(U.safeDeltaTime());
        st.draw();

        if (sync != null) {
            sync.sync(U.config().fps);
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState().isRendered()) {
                screens.get(i).hide();
            }
            screens.get(i).dispose();
        }
        st.dispose();
        A.dispose();
    }

    @Override
    public void resize(int width, int height) {
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState().isRendered()) {
                screens.get(i).resize(width, height);
            }
        }
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState().isRendered()) {
                screens.get(i).pause();
            }
        }
    }

    @Override
    public void resume() {
        for (int i = 0; i < screens.size; i++) {
            if (screens.get(i).getState().isRendered()) {
                screens.get(i).resume();
            }
        }
    }
}
