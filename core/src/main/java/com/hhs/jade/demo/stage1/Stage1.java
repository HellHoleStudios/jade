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

import com.hhs.jade.game.task.*;
import com.hhs.jade.util.A;
import com.hhs.jade.game.drawable.TiledScrollingBackground;

public class Stage1 extends Sequence {

    @Override
    public void init() {
        super.init();
        add(new AddDrawable(new TiledScrollingBackground(A.getRegion("bg/st3_water.png"), 1, 1, -2048)));
        add(new AddDrawable((new TiledScrollingBackground(A.getRegion("bg/st3_water_overlay.png"), 1.2f, 0, -2047)
                .setAlpha(0.5f))));
        add(new AddDrawable(new TiledScrollingBackground(A.getRegion("bg/st3_shore_left.png"), 1, 1, -2046, 0, 128)));
        add(new AddDrawable(
                new TiledScrollingBackground(A.getRegion("bg/st3_shore_right.png"), 1, 1, -2046, 256, 128)));
        add(new Wait(120));
        add(new SwitchBGM("mus/Idea12.ogg"));
		add(new Stage1Mid1());
		add(new WaitForBulletClear());
		add(new Wait(300));
		add(new Stage1Mid2());
		add(new WaitForBulletClear());
		add(new Wait(600));
        add(new AddBossScene(new Stage1BossScene()));
        add(new WaitForBossScene());
    }

}
