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

package com.hhs.jade.game.shot;

import com.badlogic.gdx.files.FileHandle;
import com.hhs.jade.util.U;

public class RawShotSheet {
	public String atlas;
	public RawBulletData[] data;

	public RawShotSheet() {

	}

	public static RawShotSheet fromJson(FileHandle file) {
		return U.fromJson(file, RawShotSheet.class);
	}

	public static class RawBulletData {
		public int id;
		public String name;
		public int[] frames;
		public String render;
		public String delaySrc;
		public String delayColor;
		public String collisionMethod;
		public float[] collisionData;
		public Float rotation;
		public Float spinVelocity;
		public Float originX, originY;

		public RawBulletData() {

		}
	}
}
