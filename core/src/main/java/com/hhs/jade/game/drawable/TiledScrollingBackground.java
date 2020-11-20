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

package com.hhs.jade.game.drawable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.hhs.jade.game.Drawable;
import com.hhs.jade.util.M;
import com.hhs.jade.util.U;

public class TiledScrollingBackground implements Drawable {

	private TiledDrawable tile;
	private float delta, scrollSpeed;
	private int zIndex;
	private float x, y;
	private float width;

	public TiledScrollingBackground(TextureRegion region, float scrollSpeed) {
		this(region, scrollSpeed, 0, -2048, 0, U.config().w);
	}

	public TiledScrollingBackground(TextureRegion region, float scrollSpeed, float transitionTime, int zIndex) {
		this(region, scrollSpeed, transitionTime, zIndex, 0, U.config().w);
	}

	public TiledScrollingBackground(TextureRegion region, float scrollSpeed, float transitionTime, int zIndex, float x,
			float width) {
		this.tile = new TiledDrawable(region);
		if (transitionTime <= 0) {
			this.delta = 0;
		} else {
			tile.getColor().a = 0;
			this.delta = 1 / transitionTime;
		}
		tile.setMinHeight(U.config().h);
		this.scrollSpeed = scrollSpeed;
		this.zIndex = zIndex;
		this.x = x;
		this.y = 0;
		this.width = width;
	}

	@Override
	public void draw(Batch batch) {
		tile.draw(batch, x - U.config().originX, y - U.config().originY - tile.getRegion().getRegionHeight(), width,
				U.config().h + tile.getRegion().getRegionHeight());
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	public TiledScrollingBackground setScrollSpeed(float scroolSpeed) {
		this.scrollSpeed = scroolSpeed;
		return this;
	}

	@Override
	public void update(int t) {
		tile.getColor().a = M.clamp(tile.getColor().a + delta, 0, 1);
		y = M.safeMod(y - scrollSpeed, tile.getRegion().getRegionHeight());
	}

	public TiledScrollingBackground setAlpha(float alpha) {
		tile.getColor().a = alpha;
		return this;
	}

}
