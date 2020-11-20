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

package com.hhs.jade.ui.grid;

import java.util.concurrent.Callable;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hhs.jade.util.A;

public class GridLabel extends Label implements GridComponent {

    public boolean active;
    protected Grid parent;
    protected int gridX, gridY;
    protected float staticX, staticY;
    protected LabelStyle activeStyle, inactiveStyle;
    protected Callable<? extends Action> activeAction, inactiveAction;

    public GridLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        this.activeStyle = style;
    }

    public GridLabel(CharSequence text, int fontSize, float x, float y, float width, float height, int gridX, int gridY,
                     Callable<? extends Action> activeAction, Callable<? extends Action> inactiveAction, LabelStyle activeStyle,
                     LabelStyle inactiveStyle) {
        super(text, A.getUILabelStyle(fontSize));
        this.staticX = x;
        this.staticY = y;
        this.gridX = gridX;
        this.gridY = gridY;
        this.active = false;
        this.activeStyle = activeStyle;
        this.inactiveStyle = inactiveStyle;
        this.activeAction = activeAction;
        this.inactiveAction = inactiveAction;
        setBounds(x, y, width, height);
    }

    public GridLabel(CharSequence text, int fontSize, float x, float y, float width, float height, int gridX,
                     int gridY) {
        this(text, fontSize, x, y, width, height, gridX, gridY, null, null, A.getUILabelStyle(fontSize),
                A.getUILabelStyle(fontSize));
    }

    @Override
    public GridComponent setParent(Grid parent) {
        return null;
    }

    @Override
    public Grid getPartent() {
        return parent;
    }

    @Override
    public GridComponent activate() {
        active = true;
        update();
        return this;
    }

    @Override
    public GridComponent deactivate() {
        active = false;
        update();
        return this;
    }

    @Override
    public GridComponent enable() {
        return this;
    }

    @Override
    public GridComponent disable() {
        return this;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void update() {
        if (active) {
            if (activeStyle != null)
                setStyle(activeStyle);
            getActions().clear();
            if (activeAction != null) {
                try {
                    addAction(activeAction.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (inactiveStyle != null)
                setStyle(inactiveStyle);
            getActions().clear();
            if (inactiveAction != null) {
                try {
                    addAction(inactiveAction.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getGridX() {
        return gridX;
    }

    @Override
    public int getGridY() {
        return gridY;
    }

    @Override
    public void trigger() {

    }

    @Override
    public boolean hasSound() {
        return false;
    }

}
