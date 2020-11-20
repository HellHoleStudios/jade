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

package com.hhs.jade.demo.ui.screen;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.hhs.jade.util.A;
import com.hhs.jade.util.BGM;
import com.hhs.jade.util.U;
import com.hhs.jade.music.BackgroundMusic;
import com.hhs.jade.ui.grid.GridButton;
import com.hhs.jade.ui.grid.ScrollingGrid;
import com.hhs.jade.ui.screen.BasicScreen;

public class MusicRoomScreen extends BasicScreen {

	private ScrollingGrid grid;

	private Label comment;

	private Array<BackgroundMusic> bgms;
	private Array<String> names;
	private Array<String> comments;

	public MusicRoomScreen() {
		super();
	}

	@Override
	public void show() {
		init("mus/E.0109.ogg", "bg/select.png");

		this.comment = new Label("", A.getUILabelStyle(36));
		comment.setBounds(640, 64, 576, 832);
		comment.setWrap(true);
		comment.setAlignment(Align.topLeft);
		st.addActor(comment);

		this.bgms = new Array<BackgroundMusic>();
		this.comments = new Array<String>();
		this.names = new Array<String>();
		bgms.add(BGM.getBGM("mus/E.0109.ogg"));
		names.add("E.0109");
		comments.add("E.0109\nby Zzzyt\nTitle theme.");

		bgms.add(BGM.getBGM("mus/Idea12.ogg"));
		names.add("Desire Drive");
		comments.add("Desire Drive\nby Team Shanghai Alice, arranged by Zzzyt\nStage 1 theme.");

		bgms.add(BGM.getBGM("mus/Yet Another Tetris (Piano ver.).ogg"));
		names.add("Yet Another Tetris");
		comments.add("Yet Another Tetris\nArranged by Zzzyt\nStage Extra theme.");

		this.grid = new ScrollingGrid(true, new Rectangle(64, 64, 512, 832));
		st.addActor(grid);
		for (int i = 0; i < bgms.size; i++) {
			final int tmpint = i;
			grid.add(new GridButton((i + 1) + ". " + names.get(i), 36, 80, 840 - i * 40, 500, 40, 0, i, () -> {
				setComment(tmpint);
				BGM.stop();
				BGM.play(bgms.get(tmpint).getName());
			}));
		}
		setComment(0);

		grid.selectFirst();
		grid.activate();
		grid.updateComponent();
		grid.update();
		input.addProcessor(grid);
	}

	@Override
	protected void onFadeIn(float duration) {
		comment.clearActions();
		comment.setPosition(1000, 64);
		comment.addAction(Actions.moveTo(640, 64, duration, Interpolation.sine));

		grid.clearActions();
		grid.setPosition(-400, 0);
		grid.addAction(Actions.moveTo(0, 0, duration, Interpolation.sineOut));
	}

	@Override
	protected void onFadeOut(float duration) {
		comment.clearActions();
		comment.setPosition(640, 64);
		comment.addAction(Actions.moveTo(1000, 64, duration, Interpolation.sine));

		grid.clearActions();
		grid.setPosition(0, 0);
		grid.addAction(Actions.moveTo(-400, 0, duration, Interpolation.sineOut));
	}

	@Override
	protected void onQuit() {
		U.switchScreen("title", 0.5f);
		super.onQuit();
	}

	@Override
	public String getName() {
		return "musicRoom";
	}

	private void setComment(int id) {
		comment.setText(comments.get(id));
	}

}