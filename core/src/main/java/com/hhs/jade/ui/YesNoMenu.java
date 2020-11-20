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

import java.util.concurrent.Callable;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.hhs.jade.ui.grid.GridButton;
import com.hhs.jade.ui.grid.GridLabel;
import com.hhs.jade.ui.grid.Grid;

public class YesNoMenu extends Grid {

    private Runnable yesRunnable, noRunnable;
    private GridLabel label;
    private GridButton yes, no;

    public YesNoMenu(float x, float y) {
        this(null, null, 0, 0, true, null, null);
        setNo(() -> {
            exit();
        });
        this.activeAction = () -> Actions.parallel(Actions.fadeIn(0.1f),
                Actions.moveTo(x, y, 0.1f, Interpolation.sine));
        this.inactiveAction = () -> Actions.parallel(Actions.fadeOut(0.1f),
                Actions.moveTo(x - 30, y, 0.1f, Interpolation.sine));
        setPosition(x, y);
    }

    public YesNoMenu(Runnable yesRunnable, Runnable noRunnable, int gridX, int gridY, boolean hasSound,
                     Callable<? extends Action> activeAction, Callable<? extends Action> inactiveAction) {
        super(gridX, gridY, true, hasSound, activeAction, inactiveAction);
        this.yesRunnable = yesRunnable;
        this.noRunnable = noRunnable;
        this.label = new GridLabel("Really?", 48, 0, 140, 400, 60, 0, 0);
        this.yes = new GridButton("Yes!", 36, 10, 60, 400, 40, 0, 1, this.yesRunnable);
        this.no = new GridButton("No!", 36, 20, 0, 400, 40, 0, 2, this.noRunnable);
        yes.setSound(false);
        no.setSound(false);
        add(no);
        add(yes);
        add(label);
        updateComponent();
        selectFirst();
    }

    public YesNoMenu setYes(Runnable yesRunnable) {
        this.yesRunnable = yesRunnable;
        this.yes.runnable = yesRunnable;
        return this;
    }

    public YesNoMenu setNo(Runnable noRunnable) {
        this.noRunnable = noRunnable;
        this.no.runnable = noRunnable;
        return this;
    }

}
