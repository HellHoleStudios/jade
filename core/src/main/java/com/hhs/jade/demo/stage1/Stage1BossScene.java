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

import com.badlogic.gdx.utils.Array;
import com.hhs.jade.game.BossScene;
import com.hhs.jade.util.A;
import com.hhs.jade.util.J;
import com.hhs.jade.game.entity.Enemy;
import com.hhs.jade.game.task.Spellcard;

public class Stage1BossScene extends BossScene {

	public Stage1BossScene() {
		super();

		Array<Spellcard> phase1 = new Array<>();

		phase1.add(new Spellcard(1000, 15 * 60, false, 1000000000, new Stage1Mid1()));
		phase1.add(new Spellcard(1000, 60 * 60, false, 1000000000, new Stage1Mid2()));

		Array<Spellcard> phase2 = new Array<>();

		phase2.add(new Spellcard(1000, 60 * 60, false, 1000000000, new Stage1Mid1()));
		phase2.add(new Spellcard(1000, 60 * 60, true, 1000000000, new Stage1Mid2()));

		phases = Array.with(phase1, phase2);
	}

	@Override
	public void init() {
		Enemy e = new Enemy(A.get("th10_player.atlas"), "th10_marisa", 5, 2, 0, -100, 1000, 32, 16, true);
		J.add(e);

		super.init();
	}
}
