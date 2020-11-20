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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScrollingCenteredGrid extends Grid {

	protected float centerX, centerY;
	protected float offsetX, offsetY;

	public ScrollingCenteredGrid(boolean cycle, float centerX, float centerY) {
		super(cycle);
		this.centerX = centerX;
		this.centerY = centerY;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	public ScrollingCenteredGrid(int gridX, int gridY, boolean cycle, boolean hasSound, float centerX, float centerY,
			Callable<? extends Action> activeAction, Callable<? extends Action> inactiveAction) {
		super(gridX, gridY, cycle, true, activeAction, inactiveAction);
		this.centerX = centerX;
		this.centerY = centerY;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	@Override
	public void act(float delta) {
		for (GridComponent component : grid) {
			if (component.isActive() && component instanceof Actor) {
				Actor actor = (Actor) component;
				float dx = actor.getX() + offsetX - centerX;
				float dy = actor.getY() + offsetY - centerY;
				if (dx == 0 && dy == 0)
					continue;
				offsetX -= dx;
				offsetY -= dy;
				break;
			}
		}
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		float tmpX1 = getX(), tmpY1 = getY();
		Rectangle cullingArea = getCullingArea();
		if (cullingArea != null) {
			float tmpX2 = cullingArea.getX(), tmpY2 = cullingArea.getY();
			setPosition(tmpX1 + offsetX, tmpY1 + offsetY);
			cullingArea.setPosition(tmpX2 - offsetX, tmpY2 - offsetY);
			super.draw(batch, parentAlpha);
			setPosition(tmpX1, tmpY1);
			cullingArea.setPosition(tmpX2, tmpY2);
		} else {
			setPosition(tmpX1 + offsetX, tmpY1 + offsetY);
			super.draw(batch, parentAlpha);
			setPosition(tmpX1, tmpY1);
		}
	}

}
