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

package com.hhs.jade.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Logger;
import com.hhs.jade.game.shot.ShotSheet;

public class AutoLoader {

    public static void loadAll(String path) {
        Logger logger = new Logger("BackgroundLoader", U.config().logLevel);
        if (!path.endsWith("/")) {
            path += '/';
        }
        logger.debug("Loading absolute directory: " + path);
        for (FileHandle i : Gdx.files.absolute(path).list()) {
            loadRecursive(Gdx.files.absolute(path), path.length());
        }
    }

    private static void loadRecursive(FileHandle fh, int rootLength) {
        if (fh.isDirectory()) {
            if ("com".equals(fh.name())) {
                return;
            }
            for (FileHandle s : fh.list()) {
                loadRecursive(s, rootLength);
            }
        } else {
            loadSafe(Gdx.files.internal(fh.toString().substring(rootLength)));
        }
    }

    public static void loadSafe(FileHandle fh) {
        String extension = fh.extension();
        if ("png".equals(extension))
            A.load(fh.path(), Texture.class, A.defaultTextureParameter());
        else if ("jpg".equals(extension))
            A.load(fh.path(), Texture.class, A.defaultTextureParameter());
        else if ("jpeg".equals(extension))
            A.load(fh.path(), Texture.class, A.defaultTextureParameter());
        else if ("bmp".equals(extension))
            A.load(fh.path(), Texture.class, A.defaultTextureParameter());
        else if ("gif".equals(extension))
            A.load(fh.path(), Texture.class, A.defaultTextureParameter());
        else if ("atlas".equals(extension))
            A.load(fh.path(), TextureAtlas.class);
        else if ("fnt".equals(extension))
            A.load(fh.path(), BitmapFont.class, A.defaultBitmapFontParameter());
        else if ("ttf".equals(extension))
            A.load(fh.path(), FreeTypeFontGenerator.class);
        else if ("shot".equals(extension))
            A.load(fh.path(), ShotSheet.class);
        else if ("p".equals(extension))
            A.load(fh.path(), ParticleEffect.class);
    }
}
