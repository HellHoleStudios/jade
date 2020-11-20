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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputBlocker implements InputProcessor {

    private boolean blocking;

    public InputBlocker() {
        this.blocking = false;
    }

    public void enable() {
        if (!blocking)
            Gdx.app.log("InputBlocker", "Blocking enabled.");
        this.blocking = true;
    }

    public void disable() {
        if (blocking)
            Gdx.app.log("InputBlocker", "Blocking disabled.");
        this.blocking = false;
    }

    public boolean isBlocking() {
        return blocking;
    }

    @Override
    public boolean keyDown(int keycode) {
        return blocking;
    }

    @Override
    public boolean keyUp(int keycode) {
        return blocking;
    }

    @Override
    public boolean keyTyped(char character) {
        return blocking;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return blocking;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return blocking;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return blocking;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return blocking;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return blocking;
    }

}
