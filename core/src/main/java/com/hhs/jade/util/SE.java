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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Sound Effect Library Class
 *
 * @author XGN
 */
public class SE {

    private static ObjectMap<String, Sound> SEs = new ObjectMap<>();
    private static Logger logger = new Logger("SE", U.config().logLevel);

    public static void play(String name) {
//		logger.debug("Playing \"" + name + "\".");
        Sound se = SEs.get(name);
        if (se == null) {
            logger.error("SE with this name doesn't exist!");
        } else {
            long id = se.play();
            se.setVolume(id, U.config().SEVolume);
        }
    }

    /**
     * @param name
     * @param music -path
     * @return the sound file
     */
    public static Sound register(String name, String snd) {
        logger.debug("Registering sound with internal name:" + name + " path:" + snd);
        Sound sd = Gdx.audio.newSound(Gdx.files.internal(snd));

        SEs.put(name, sd);
        return sd;
    }


    public static Logger getLogger() {
        return logger;
    }

}
