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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hhs.jade.game.Jade;
import com.hhs.jade.util.J;

public class GameFrame extends Actor {

    private static final Color tempColor1 = new Color(), tempColor2 = new Color();

    private Jade jade;

    public GameFrame() {

    }

    public GameFrame(Jade jade) {
        this.jade = jade;
    }

    public void setJade(Jade jade) {
        this.jade = jade;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (jade == null)
            jade = J.getSession();
        if (jade != null) {
            tempColor1.set(batch.getColor());
            tempColor2.set(getColor());
            tempColor2.a *= parentAlpha;
            batch.setColor(tempColor2);
            batch.draw(jade.getFrameTexture(), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
            if (jade.bossScene != null) {
                jade.bossScene.draw(batch, parentAlpha);
            }
            batch.setColor(tempColor1);
        }
    }
}
