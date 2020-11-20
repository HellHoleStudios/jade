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

package com.hhs.jade.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hhs.jade.game.Updatable;

/**
 * Tasked enemy is an enemy which follows an updatable!
 *
 * @author XGN
 */
public class TaskedEnemy extends Enemy {

    public TaskedEnemy(TextureAtlas atlas, String regionName, int frameLength, int transitionFrameLength, float x,
                       float y, float hp, float radiusS, float radiusP, boolean isBoss) {
        super(atlas, regionName, frameLength, transitionFrameLength, x, y, hp, radiusS, radiusP, isBoss);
    }


    public Updatable task;

    public int creationFrame = -1;

    /**
     * A relative timestamp is given to the task<br/>
     * Please notice that!
     */
    @Override
    public void update(int t) {

        if (creationFrame == -1) {
            creationFrame = t;
        }
        super.update(t);

        task.update(t - creationFrame);
    }
}
