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

import com.badlogic.gdx.math.MathUtils;
import com.hhs.jade.util.A;
import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.game.operator.Acceleration;
import com.hhs.jade.game.task.BasicTask;

public class Stage1Mid1 extends BasicTask {

	@Override
	public void init() {
		super.init();
		setUpdateFunc((frame) -> {
			if (frame >= 24 * 60) {
				terminate();
				J.clearOperators();
				return;
			}
			if (frame == 12 * 60) {
				J.addOperator(0, new Acceleration(0.01f, 5));
			}
			if (frame % J.diffSelect(60, 45, 30, 15) == 0) {
//				B.create(MathUtils.random(-150, 150), 50, MathUtils.random(-150, -30), 2, MathUtils.random(9, 16), 0);
				TestEnemy te = new TestEnemy(A.get("th10_player.atlas"), "th10_reimu", 5, 2, -200, 0, 30, 50, 5, false);

				te.task = (t) -> {

					if (t % 120 >= 60 && t % 120 <= 120) {

					} else {
						te.x += 1;
						te.y -= 0.5f;
					}

					if (t % J.diffSelect(30, 30, 10, 5) == 0) {
						B.create(te.x, te.y, MathUtils.random(0, 360), 2, MathUtils.random(9, 16), 0);
					}
				};

				J.add(te);
			}
		});
	}

}
