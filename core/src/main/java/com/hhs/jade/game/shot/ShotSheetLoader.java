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

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class ShotSheetLoader extends SynchronousAssetLoader<ShotSheet, ShotSheetLoader.ShotSheetParameter> {
	public ShotSheetLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	RawShotSheet raw;

	@Override
	public ShotSheet load(AssetManager assetManager, String fileName, FileHandle file, ShotSheetParameter parameter) {
		ShotSheet sheet = new ShotSheet(assetManager.get(raw.atlas, TextureAtlas.class), raw);
		raw = null;
		return sheet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle sheetFile, ShotSheetParameter parameter) {
		FileHandle imgDir = sheetFile.parent();

		raw = RawShotSheet.fromJson(sheetFile);

		Array<AssetDescriptor> dependencies = new Array();
		dependencies.add(new AssetDescriptor(imgDir.child(raw.atlas), TextureAtlas.class));
		return dependencies;
	}

	static public class ShotSheetParameter extends AssetLoaderParameters<ShotSheet> {
		public ShotSheetParameter() {

		}
	}
}