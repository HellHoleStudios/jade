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

package com.hhs.jade.game.task;

import com.hhs.jade.game.BossScene;
import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

/**
 * A task that initalize the boss scene and add it to Jade
 *
 * @author think
 */
public class AddBossScene implements Task {

    public BossScene scene;

    public AddBossScene(BossScene scene) {
        this.scene = scene;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void update(int t) {
        if (J.getSession().bossScene != null) {
            throw new RuntimeException("Boss Scene Already Exists!");
        }
        scene.init();
        J.getSession().bossScene = scene;
    }

    @Override
    public void init() {

    }

}
