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

package com.hhs.jade.demo;

import com.hhs.jade.demo.ui.screen.DifficultySelectScreen;
import com.hhs.jade.demo.ui.screen.GameScreen;
import com.hhs.jade.demo.ui.screen.MusicRoomScreen;
import com.hhs.jade.demo.ui.screen.PlayerSelectScreen;
import com.hhs.jade.demo.ui.screen.OptionScreen;
import com.hhs.jade.demo.ui.screen.SpellSelectScreen;
import com.hhs.jade.demo.ui.screen.StageSelectScreen;
import com.hhs.jade.demo.ui.screen.TitleScreen;
import com.hhs.jade.music.BackgroundMusic;
import com.hhs.jade.ui.JadeApplication;
import com.hhs.jade.ui.screen.BlankScreen;
import com.hhs.jade.util.A;
import com.hhs.jade.util.B;
import com.hhs.jade.util.BGM;
import com.hhs.jade.util.SE;
import com.hhs.jade.util.U;

public class JadeDemoApplication extends JadeApplication {

    @Override
    public void onStart() {
        BGM.register(new BackgroundMusic("mus/Idea12.ogg", 0, 12));
        BGM.register(new BackgroundMusic("mus/E.0109.ogg", 2, 26));
        BGM.register(new BackgroundMusic("mus/Yet Another Tetris (Piano ver.).ogg", 0, Float.MAX_VALUE));

        SE.register("cancel", "snd/se_cancel00.wav");
        SE.register("invalid", "snd/se_invalid.wav");
        SE.register("ok", "snd/se_ok00.wav");
        SE.register("select", "snd/se_select00.wav");
        SE.register("pldead", "snd/se_pldead00.wav");
        SE.register("item", "snd/se_item00.wav");
        SE.register("graze", "snd/se_graze.wav");
        SE.register("shoot", "snd/se_plst00.wav");

        A.load(U.config().UIFont);
        A.load("font/LBRITE.ttf");
        A.load("font/LBRITEI.ttf");
        A.load("bg/blank.png");
        A.load("bg/title.png");
        A.load("default_shot.shot");
        A.finishLoading();

        B.setSheet(U.config().defaultShotSheet);

        screens.add(new BlankScreen());
        screens.add(new TitleScreen());
        screens.add(new GameScreen());
        screens.add(new DifficultySelectScreen());
        screens.add(new PlayerSelectScreen());
        screens.add(new StageSelectScreen());
        screens.add(new SpellSelectScreen());
        screens.add(new MusicRoomScreen());
        screens.add(new OptionScreen());

        U.switchScreen("blank");
        U.switchScreen("title", 0.5f);
    }
}
