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

package com.hhs.jade.ui.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hhs.jade.util.A;
import com.hhs.jade.util.BGM;
import com.hhs.jade.util.SE;
import com.hhs.jade.util.U;
import com.hhs.jade.ui.KeyListener;

public class BasicScreen implements FadeableScreen {

    public Viewport viewport;
    public Stage st;
    public InputMultiplexer input;

    protected Image background;
    protected ScreenState state;

    public BasicScreen() {
        this.viewport = new ScalingViewport(U.config().windowScaling, U.config().screenWidth, U.config().screenHeight);
        this.state = ScreenState.HIDDEN;
    }

    @Override
    public void show() {

    }

    public void init(String bgm) {
        BGM.play(bgm);

        this.st = new Stage(viewport);
        st.setDebugAll(U.config().debugActorLayout);
        this.input = new InputMultiplexer();

        input.addProcessor(st);
        input.addProcessor(new KeyListener(U.config().keyCancel, this::onQuit));

        U.addProcessor(input);
    }

    public void init(String bgm, String backgroundName) {
        init(bgm, A.getRegion(backgroundName));
    }

    public void init(String bgm, TextureRegion background) {
        BGM.play(bgm);

        this.st = new Stage(viewport);
        st.setDebugAll(U.config().debugActorLayout);
        this.input = new InputMultiplexer();

        this.background = new Image(background);
        this.background.setZIndex(0);
        this.background.setBounds(0, 0, U.config().screenWidth, U.config().screenHeight);
        st.addActor(this.background);

        input.addProcessor(st);
        input.addProcessor(new KeyListener(U.config().keyCancel, () -> {
            onQuit();
        }));

        U.addProcessor(input);
    }

    protected void onFadeIn(float duration) {

    }

    protected void onFadeOut(float duration) {

    }

    protected void onQuit() {
        SE.play("cancel");
    }

    @Override
    public void render(float delta) {
        st.act(U.safeDeltaTime());
        st.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        U.removeProcessor(input);
        state = ScreenState.HIDDEN;
        if (st != null)
            st.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean fadeOut(float duration) {
        state = ScreenState.FADING_OUT;
        st.getRoot().addAction(Actions.sequence(Actions.fadeOut(duration), Actions.run(() -> {
            hide();
        })));
        onFadeOut(duration);
        return true;
    }

    @Override
    public boolean fadeIn(float duration) {
        state = ScreenState.FADING_IN;
        show();
        st.getRoot().getColor().a = 1;
        st.getRoot().addAction(Actions.sequence(Actions.delay(duration), Actions.run(() -> {
            state = ScreenState.SHOWN;
        })));
        onFadeIn(duration);
        return true;
    }

    @Override
    public ScreenState getState() {
        return state;
    }

    @Override
    public void setState(ScreenState state) {
        this.state = state;
    }

    @Override
    public String getName() {
        return "";
    }

}
