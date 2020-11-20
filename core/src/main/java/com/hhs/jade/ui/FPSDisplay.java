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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hhs.jade.util.A;
import com.hhs.jade.util.U;

public class FPSDisplay extends Actor {

	private BitmapFont font;
	private int counter;
	private String text;

	public FPSDisplay() {
		this.font = A.get("font/debug.fnt");
		this.counter = 0;
		this.text = "----- fps";
		setBounds(U.config().screenWidth - 100, 0, 100, 16);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		counter++;
		if (counter >= 4) {
			if (U.game.fpsCounter.hasEnoughData()) {
				text = String.format("%.2f fps", 1 / U.game.fpsCounter.getMean());
			}
			counter = 0;
		}
		font.draw(batch, text, getX(), getY() + getHeight());
	}
}
