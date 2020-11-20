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

import com.badlogic.gdx.utils.Array;
import com.hhs.jade.game.Task;

public class Sequence implements Task {

	private Array<Task> tasks;
	private int currentTask;
	private int loopCount;
	private int loopCounter;
	private boolean firstTime;

	public Sequence() {
		this(1);
	}

	public Sequence(int loopCount) {
		this.loopCount = loopCount;
	}

	public Array<Task> getTasks() {
		return tasks;
	}

	public Task getCurrentTask() {
		return tasks.get(currentTask);
	}

	public Sequence add(Task task) {
		tasks.add(task);
		return this;
	}

	public Sequence remove(Task task) {
		tasks.removeValue(task, true);
		return this;
	}

	public Sequence setLoopCount(int loopCount) {
		this.loopCount = loopCount;
		return this;
	}

	public int getLoopCount() {
		return loopCount;
	}

	@Override
	public boolean isFinished() {
		return loopCounter >= loopCount;
	}

	@Override
	public void update(int t) {
		if (!isFinished()) {
			if (firstTime) {
				getCurrentTask().init();
				firstTime = false;
			}
			while (true) {
				getCurrentTask().update(t);
				if (getCurrentTask().isFinished()) {
					currentTask++;
					if (currentTask >= tasks.size) {
						loopCounter++;
						if (loopCounter >= loopCount) {
							break;
						}
						currentTask = 0;
					}
					getCurrentTask().init();
				} else {
					break;
				}
			}
		}
	}

	@Override
	public void init() {
		this.tasks = new Array<Task>();
		this.currentTask = 0;
		this.loopCounter = 0;
		this.firstTime = true;
	}
}