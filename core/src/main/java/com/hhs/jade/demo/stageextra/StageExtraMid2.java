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

import com.badlogic.gdx.math.MathUtils;
import com.hhs.jade.game.Operator;
import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.game.operator.Acceleration;
import com.hhs.jade.game.operator.AngularVelocity;
import com.hhs.jade.game.task.BasicTask;

public class StageExtraMid2 extends BasicTask {

	@Override
	public void init() {
		super.init();
		J.addOperator(0, new AngularVelocity(0.5f, 720));
		Operator tmp = J.addOperator(0, new Acceleration(-0.03f, 0.3f));
		setUpdateFunc((frame) -> {
			if (frame >= 15 * 60) {
				terminate();
				J.clearOperators();
				return;
			}
			if (frame == 8 * 60) {
				J.removeOperator(0, tmp);
				J.addOperator(0, new Acceleration(0.03f, 2f));
			}
			if (frame == 12 * 60) {
				J.clearOperators();
			}
			if (frame <= 5 * 60 && frame % 40 == 0) {
				genCircle(-50, -50, 4, MathUtils.random(-50, 50), 60, 9 + frame % 7);
				genCircle(50, -50, 4, MathUtils.random(-50, 50), 60, 9 + frame % 7);
			}
		});
	}

	private void genCircle(float x, float y, float speed, float off, int cnt, int bullet) {
		for (int i = 0; i < cnt; i++) {
			B.create(x, y, i * 360f / cnt + off, speed, bullet, 0);
		}
	}

}
