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

package com.hhs.jade.demo.stageextra;

import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.util.M;
import com.hhs.jade.game.operator.AngularVelocity;
import com.hhs.jade.game.task.BasicTask;

public class StageExtraMid1 extends BasicTask {

	private float tmpf;

	@Override
	public void init() {
		super.init();
		setUpdateFunc((frame) -> {
			if (frame >= 15 * 60) {
				J.clearOperators();
				terminate();
				return;
			}
			if (frame == 8 * 60) {
				J.addOperator(0, new AngularVelocity(0.5f, 180));
			}
			if (frame % 3 == 0) {
				for (int i = 0; i < 7; i++) {
					B.create(0, -100, i * 360f / 7f + tmpf, M.min(frame % 60, 60 - frame % 60) / 60f + 1, i + 9, 0);
				}
			}
			tmpf += (float) (frame / 30) / 8 + 1;
		});
		this.tmpf = 0;
	}
}
