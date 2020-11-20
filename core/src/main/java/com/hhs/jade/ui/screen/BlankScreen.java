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

package com.hhs.jade.ui.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hhs.jade.util.A;
import com.hhs.jade.util.Global;
import com.hhs.jade.util.U;
import com.hhs.jade.ui.KeyListener;

public class BlankScreen extends BasicScreen {

	public BlankScreen() {
		super();
	}

	@Override
	public void show() {
		this.st = new Stage(viewport);
		st.setDebugAll(U.config().debugActorLayout);
		this.input = new InputMultiplexer();

		this.background = new Image(A.getTexture("bg/blank.png"));
		background.setZIndex(0);
		background.setSize(U.config().screenWidth, U.config().screenHeight);
		st.addActor(this.background);

		input.addProcessor(st);
		input.addProcessor(new KeyListener(U.config().keyCancel, () -> {
			onQuit();
		}));
	}

	@Override
	public boolean fadeIn(float duration) {
		state = ScreenState.FADING_IN;
		show();
		st.getRoot().getColor().a = 1;
		st.getRoot().addAction(Actions.sequence(Actions.delay(duration), Actions.run(() -> {
			state = ScreenState.SHOWN;
			if (Global.get("_redirect") != null) {
				if (Global.get("_redirectDelay") != null) {
					U.switchScreen((String) Global.get("_redirect"), (float) Global.get("_redirectDelay"));
					Global.remove("_redirectDelay");
				} else {
					U.switchScreen((String) Global.get("_redirect"));
				}
				Global.remove("_redirect");
			}
		})));
		onFadeIn(duration);
		return true;
	}

	@Override
	public String getName() {
		return "blank";
	}

}
