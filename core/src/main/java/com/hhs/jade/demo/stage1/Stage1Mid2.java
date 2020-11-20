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

package com.hhs.jade.demo.stage1;

import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.util.M;
import com.hhs.jade.game.task.BasicTask;

public class Stage1Mid2 extends BasicTask {

	private float tmpf;

	@Override
	public void init() {
		super.init();
		setUpdateFunc((frame) -> {
			if (frame >= 36 * 60) {
				terminate();
				return;
			}
			if (frame % J.diffSelect(16, 10, 5, 2) == 0) {
				for (int i = 0; i < 360; i += 72) {
					B.towards(0, -100, i + tmpf, 2, "DS_RICE_S_RED", 0);
					B.towards(0, -100, i - tmpf, 2, "DS_RICE_S_BLUE", 0);
				}
				tmpf += M.sin(frame / 2f) * 6;
			}
		});
		this.tmpf = 72;
	}

}
