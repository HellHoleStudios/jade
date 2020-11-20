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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ObjectMap;
import com.hhs.jade.util.J;
import com.hhs.jade.util.U;

public class ShotSheet {
	public TextureAtlas atlas;
	public ObjectMap<Integer, BulletData> data;
	public ObjectMap<String, Integer> nameToId;

	public ShotSheet(String internalSheetFile) {
		this(Gdx.files.internal(internalSheetFile));
	}

	public ShotSheet(FileHandle sheetFile) {
		this(sheetFile, U.fromJson(sheetFile, RawShotSheet.class));
	}

	public ShotSheet(FileHandle sheetFile, RawShotSheet raw) {
		this(new TextureAtlas(sheetFile.parent().child(raw.atlas)), raw);
	}

	public ShotSheet(TextureAtlas atlas, RawShotSheet raw) {
		this.atlas = atlas;
		this.data = new ObjectMap<Integer, BulletData>();
		this.nameToId = new ObjectMap<String, Integer>();
		for (int i = 0; i < raw.data.length; i++) {
			BulletData tmp = new BulletData(this, raw.data[i]);
			data.put(tmp.id, tmp);
			if (tmp.name != null) {
				nameToId.put(tmp.name, tmp.id);
			}
		}
	}

	public int getId(String name) {
		Integer tmp = nameToId.get(name);
		if (tmp == null) {
			J.getLogger().error("Shot data of name\"" + name + "\" not found!");
		}
		return tmp;
	}

	public BulletData findBullet(int id) {
		return data.get(id);
	}

	public BulletData findBullet(String name) {
		return data.get(nameToId.get(name));
	}
}
