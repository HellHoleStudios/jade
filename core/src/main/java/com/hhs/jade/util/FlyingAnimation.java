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

package com.hhs.jade.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * @author Zzzyt, XGN
 *
 */
public class FlyingAnimation {

	public transient Array<? extends TextureRegion> left, center, right, toLeft, toRight;
	
	public int pos;
	public int frameLength;
	public int transitionFrameLength;
	
	public int timer1,timer2;
	
	
	public FlyingAnimation(Array<? extends TextureRegion> left, Array<? extends TextureRegion> center,
			Array<? extends TextureRegion> right, Array<? extends TextureRegion> toLeft,
			Array<? extends TextureRegion> toRight, int frameLength, int transitionFrameLength) {
		this.left = left;
		this.center = center;
		this.right = right;
		this.toLeft = toLeft;
		this.toRight = toRight;
		this.frameLength = frameLength;
		this.transitionFrameLength = transitionFrameLength;
	}

	public TextureRegion getTexture() {
		if (pos == -toLeft.size * transitionFrameLength) {
			return left.get(timer2);
		}
		if (pos < 0) {
			return toLeft.get((-pos + transitionFrameLength - 1) / transitionFrameLength - 1);
		}
		if (pos == 0) {
			return center.get(timer2);
		}
		if (pos == toRight.size * transitionFrameLength) {
			return right.get(timer2);
		}
		if (pos > 0) {
			return toRight.get((pos + transitionFrameLength - 1) / transitionFrameLength - 1);
		}
		return center.get(timer2);
	}
	
	public void update(int t,float dx) {
		timer1 = t % frameLength;
		if (dx < 0) {
			if (pos > -toLeft.size * transitionFrameLength) {
				pos--;
				timer2 = 0;
			} else {
				if (timer1 == 0) {
					timer2 = (timer2 + 1) % left.size;
				}
			}
		} else if (dx > 0) {
			if (pos < toRight.size * transitionFrameLength) {
				pos++;
				timer2 = 0;
			} else {
				if (timer1 == 0) {
					timer2 = (timer2 + 1) % right.size;
				}
			}
		} else {
			if (pos > 0) {
				pos--;
				timer2 = 0;
			} else if (pos < 0) {
				pos++;
				timer2 = 0;
			} else {
				if (timer1 == 0) {
					timer2 = (timer2 + 1) % center.size;
				}
			}
		}
	}
}
