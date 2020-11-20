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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hhs.jade.util.A;

public class GridButton extends Label implements GridComponent {

	public Runnable runnable;
	public boolean active, enabled, hasSound;
	public float staticX, staticY;

	protected Grid parent;
	protected int gridX, gridY;
	protected LabelStyle activeStyle, inactiveStyle;
	protected Callable<? extends Action> activeAction, inactiveAction;

	public GridButton(CharSequence text, LabelStyle activeStyle) {
		super(text, activeStyle);
		this.activeStyle = activeStyle;
	}

	public GridButton(CharSequence text, int fontSize, float x, float y, float width, float height, int gridX,
			int gridY, boolean hasSound, Callable<? extends Action> activeAction,
			Callable<? extends Action> inactiveAction, LabelStyle activeStyle, LabelStyle inactiveStyle,
			Runnable runnable) {
		super(text, A.getUILabelStyle(fontSize));
		this.staticX = x;
		this.staticY = y;
		this.gridX = gridX;
		this.gridY = gridY;
		this.runnable = runnable;
		this.active = false;
		this.activeStyle = activeStyle;
		this.inactiveStyle = inactiveStyle;
		this.activeAction = activeAction;
		this.inactiveAction = inactiveAction;
		this.enabled = true;
		this.hasSound = hasSound;
		setBounds(x, y, width, height);
	}

	public GridButton(CharSequence text, int fontSize, float x, float y, float width, float height, int gridX,
			int gridY, Runnable runnable) {
		this(text, fontSize, x, y, width, height, gridX, gridY, true, null, null, A.getUILabelStyle(fontSize),
				A.getUILabelStyle(fontSize), runnable);
		setActiveAction(() -> Actions.parallel(
				Actions.sequence(Actions.color(Color.WHITE),
						Actions.moveTo(staticX + 2, staticY, 0.03f, Interpolation.sine),
						Actions.moveTo(staticX - 4, staticY, 0.06f, Interpolation.sine),
						Actions.moveTo(staticX, staticY, 0.03f, Interpolation.sine)),
				Actions.forever(Actions.sequence(Actions.color(new Color(0.9f, 0.9f, 0.9f, 1f), 0.5f),
						Actions.color(Color.WHITE, 0.5f)))));
		setInactiveAction(
				() -> Actions.parallel(Actions.alpha(1f), Actions.moveTo(staticX, staticY, 0.1f, Interpolation.sine),
						Actions.color(new Color(0.7f, 0.7f, 0.7f, 1))));
	}

	@Override
	public void trigger() {
		if (enabled && runnable != null) {
			runnable.run();
		}
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
		if (!enabled) {
			setStyle(new LabelStyle(inactiveStyle.font, inactiveStyle.fontColor.cpy().mul(0.5f, 0.5f, 0.5f, 1)));
		}
	}

	@Override
	public GridButton activate() {
		active = true;
		update();
		return this;
	}

	@Override
	public GridButton deactivate() {
		active = false;
		update();
		return this;
	}

	public GridButton setActiveStyle(LabelStyle style) {
		activeStyle = style;
		update();
		return this;
	}

	public GridButton setInactiveStyle(LabelStyle style) {
		inactiveStyle = style;
		update();
		return this;
	}

	public GridButton setActiveAction(Callable<? extends Action> action) {
		activeAction = action;
		update();
		return this;
	}

	public GridButton setInactiveAction(Callable<? extends Action> action) {
		inactiveAction = action;
		update();
		return this;
	}

	public Label setAction(Runnable action) {
		this.runnable = action;
		return this;
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
	public boolean isActive() {
		return active;
	}

	@Override
	public GridButton enable() {
		this.enabled = true;
		update();
		return this;
	}

	@Override
	public GridButton disable() {
		this.enabled = false;
		update();
		return this;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public GridButton setParent(Grid parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public Grid getPartent() {
		return parent;
	}

	@Override
	public boolean hasSound() {
		return hasSound;
	}

	public GridButton setSound(boolean hasSound) {
		this.hasSound = hasSound;
		return this;
	}

}
