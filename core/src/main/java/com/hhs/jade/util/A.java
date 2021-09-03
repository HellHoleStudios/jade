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

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.hhs.jade.game.shot.ShotSheet;
import com.hhs.jade.game.shot.ShotSheetLoader;

public class A {

    private static A self;

    public AssetManager am;

    private ObjectMap<Texture, String> textureReflect;
    private ObjectMap<String, BitmapFont> fontCache;

    private A() {
        am = new AssetManager();
        am.getLogger().setLevel(U.config().logLevel);
        am.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        am.setLoader(ShotSheet.class, new ShotSheetLoader(new InternalFileHandleResolver()));

        fontCache = new ObjectMap<String, BitmapFont>();
        textureReflect = new ObjectMap<Texture, String>();
    }

    public static void init() {
        self = new A();
    }

    public static <T> T get(String fileName) {
        return self.am.get(fileName);
    }

    public static <T> T get(String fileName, Class<T> type) {
        return self.am.get(fileName, type);
    }

    public static Texture getTexture(String fileName) {
        return self.am.get(fileName, Texture.class);
    }

    public static <T> TextureRegion getRegion(String fileName) {
        T tmp = self.am.get(fileName);
        if (tmp instanceof TextureRegion) {
            return (TextureRegion) tmp;
        } else if (tmp instanceof Texture) {
            return new TextureRegion((Texture) tmp);
        } else {
            self.am.getLogger().error("[A] getRegion() requires a Texture-like asset!");
            return (TextureRegion) tmp;
        }
    }

    public static void load(String fileName) {
        String extension = self.am.getFileHandleResolver().resolve(fileName).extension();
        if ("png".equals(extension))
            load(fileName, Texture.class, defaultTextureParameter());
        else if ("jpg".equals(extension))
            load(fileName, Texture.class, defaultTextureParameter());
        else if ("jpeg".equals(extension))
            load(fileName, Texture.class, defaultTextureParameter());
        else if ("bmp".equals(extension))
            load(fileName, Texture.class, defaultTextureParameter());
        else if ("gif".equals(extension))
            load(fileName, Texture.class, defaultTextureParameter());
        else if ("mp3".equals(extension))
            load(fileName, Music.class);
        else if ("ogg".equals(extension))
            load(fileName, Music.class);
        else if ("atlas".equals(extension))
            load(fileName, TextureAtlas.class);
        else if ("fnt".equals(extension))
            load(fileName, BitmapFont.class, defaultBitmapFontParameter());
        else if ("ttf".equals(extension))
            load(fileName, FreeTypeFontGenerator.class);
        else if ("otf".equals(extension))
            load(fileName, FreeTypeFontGenerator.class);
        else if ("shot".equals(extension))
            load(fileName, ShotSheet.class);
        else if ("p".equals(extension))
            load(fileName, ParticleEffect.class);
    }

    public static <T> void load(String fileName, Class<T> type) {
        if (self.am.isLoaded(fileName, type)) {
            self.am.getLogger().debug("[A] " + fileName + " Already loaded, aborting.");
        }
        if (type == Texture.class) {
            AssetLoaderParameters<T> parameter = new AssetLoaderParameters<T>();
            parameter.loadedCallback = new TextureReflectCallback();
            self.am.load(fileName, type, parameter);
        } else {
            self.am.load(fileName, type);
        }
    }

    public static <T> void load(String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
        if (self.am.isLoaded(fileName, type)) {
            self.am.getLogger().debug("[A] " + fileName + " Already loaded, aborting.");
            return;
        }
        if (type == Texture.class) {
            LoadedCallback tmp = parameter.loadedCallback;
            parameter.loadedCallback = new TextureReflectCallback(tmp);
        }
        self.am.load(fileName, type, parameter);
    }

    public static boolean isLoaded(String fileName) {
        return self.am.isLoaded(fileName);
    }

    public static <T> boolean isLoaded(String fileName, Class<T> type) {
        return self.am.isLoaded(fileName, type);
    }

    public static void finishLoading() {
        self.am.finishLoading();
    }

    public static void update() {
        self.am.update();
    }

    public static void update(int times) {
        for (int i = 0; i < times; i++) {
            self.am.update();
        }
    }

    public static void dispose() {
        self.am.dispose();
        for (BitmapFont font : self.fontCache.values()) {
            font.dispose();
        }
    }

    public static BitmapFont getFont(String name, int size, Color color, float borderWidth, Color borderColor) {
        StringBuilder tmp = new StringBuilder(64);
        tmp.append(name).append(':').append(size).append(':').append(color).append(':');
        if (borderWidth != 0) {
            tmp.append(borderWidth).append(':').append(borderColor.toString());
        }
        String key = tmp.toString();
        if (self.fontCache.containsKey(key)) {
            return self.fontCache.get(key);
        }
        FreeTypeFontGenerator generator = get(name);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        if (borderWidth != 0) {
            parameter.borderWidth = borderWidth;
            parameter.borderColor = borderColor;
        }
        parameter.minFilter = U.config().textureMinFilter;
        parameter.magFilter = U.config().textureMagFilter;
        BitmapFont font = generator.generateFont(parameter);
        self.fontCache.put(key, font);
        return font;
    }

    public static BitmapFont getFont(String name, int size) {
        return getFont(name, size, Color.WHITE, 0, null);
    }

    public static LabelStyle getUILabelStyle(int fontSize) {
        return new LabelStyle(A.getFont(U.config().UIFont, fontSize, U.config().UIFontColor,
                U.config().UIFontBorderWidth, U.config().UIFontBorderColor), Color.WHITE);
    }

    public static TextureParameter defaultTextureParameter() {
        TextureParameter tmp = new TextureParameter();
        tmp.minFilter = U.config().textureMinFilter;
        tmp.magFilter = U.config().textureMagFilter;
        return tmp;
    }

    public static BitmapFontParameter defaultBitmapFontParameter() {
        BitmapFontParameter tmp = new BitmapFontParameter();
        tmp.minFilter = U.config().textureMinFilter;
        tmp.magFilter = U.config().textureMagFilter;
        return tmp;
    }

    public static AtlasRegion findRegion(String atlasName, String regionName) {
        return ((TextureAtlas) A.get(atlasName)).findRegion(regionName);
    }

    public static Array<AtlasRegion> findRegions(String atlasName, String regionName) {
        return ((TextureAtlas) A.get(atlasName)).findRegions(regionName);
    }

    public static AtlasRegion findRegion(String atlasName, String regionName, int index) {
        return ((TextureAtlas) A.get(atlasName)).findRegion(regionName, index);
    }

    public static void putTextureReflect(Texture texture, String fileName) {
        self.am.getLogger().debug("[A] Texture reflect info: " + texture.hashCode() + " <- " + fileName);
        self.textureReflect.put(texture, fileName);
    }

    private static class TextureReflectCallback implements LoadedCallback {
        private LoadedCallback original;

        public TextureReflectCallback() {

        }

        public TextureReflectCallback(LoadedCallback original) {
            this.original = original;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
            A.putTextureReflect(assetManager.get(fileName), fileName);
            if (original != null) {
                original.finishedLoading(assetManager, fileName, type);
            }
        }
    }
}
