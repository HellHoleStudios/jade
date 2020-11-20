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

package com.hhs.jade.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.hhs.jade.util.BGM;
import com.hhs.jade.util.U;

public class BackgroundMusic {
	public String name;
	public Music music;

	public boolean isPlaying, isLooping;
	public float loopStart, loopEnd;

	public BackgroundMusic(String name) {
		this.name = name;
		this.isLooping = false;
		this.isPlaying = false;
	}

	public BackgroundMusic(String name, float loopStart, float loopEnd) {
		this.name = name;
		this.isLooping = true;
		this.isPlaying = false;
		this.loopStart = loopStart;
		this.loopEnd = loopEnd;
	}

	public String getName() {
		return name;
	}

	public Music getMusic() {
		return music;
	}

	public void stop() {
		isPlaying = false;
		music.stop();
	}

	public void load() {
		BGM.getLogger().debug("Loading music file \"" + name + "\".");
		this.music = Gdx.audio.newMusic(Gdx.files.internal(name));
		music.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(Music music) {
				if (isLooping) {
					music.setVolume(U.config().musicVolume);
					music.play();
					music.setPosition(loopStart);
				}
			}
		});
	}

	public void dispose() {
		BGM.getLogger().debug("Disposing music file \"" + name + "\".");
		music.dispose();
		music = null;
	}

	public void play() {
		load();
		isPlaying = true;
		music.setLooping(false);
		music.setVolume(U.config().musicVolume);
		music.play();
	}

	public void pause() {
		isPlaying = false;
		music.pause();
	}

	public void resume() {
		isPlaying = true;
		music.setVolume(U.config().musicVolume);
		music.play();
	}

	public void update() {
		if (music != null && isPlaying && isLooping) {
			if (!music.isPlaying()) {
				music.setPosition(loopStart);
				music.setVolume(U.config().musicVolume);
				music.play();
			} else if (music.getPosition() >= loopEnd) {
				music.setPosition(loopStart + (music.getPosition() - loopEnd));
			}
		}
	}

	public void setVolume(float volume) {
		music.setVolume(volume);
	}
}
