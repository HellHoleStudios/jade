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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.hhs.jade.util.A;
import com.hhs.jade.util.Global;
import com.hhs.jade.util.J;
import com.hhs.jade.util.U;
import com.hhs.jade.ui.grid.Grid;
import com.hhs.jade.ui.grid.GridImage;
import com.hhs.jade.ui.screen.BasicScreen;

public class DifficultySelectScreen extends BasicScreen {

	private Grid grid;

	public DifficultySelectScreen() {
		super();
	}

	@Override
	public void show() {
		init("mus/E.0109.ogg", "bg/select.png");

		this.grid = new Grid(true);
		st.addActor(grid);

		final String switchTarget;
		if (J.isGameModeSpellPractice()) {
			switchTarget = "spellSelect";
		} else if (J.isGameModeStagePractice()) {
			switchTarget = "stageSelect";
		} else {
			switchTarget = "playerSelect";
		}
		if (J.isGameModeRegular() || J.isGameModeSpellPractice() || J.isGameModeStagePractice()) {
			grid.add(new GridImage(A.getRegion("diff/easy.png"), 120, 740, 0, 0, () -> {
				Global.put("_difficulty", J.EASY);
				U.switchScreen(switchTarget, 0.5f);
			}));
			grid.add(new GridImage(A.getRegion("diff/normal.png"), 120, 560, 0, 1, () -> {
				Global.put("_difficulty", J.NORMAL);
				U.switchScreen(switchTarget, 0.5f);
			}));
			grid.add(new GridImage(A.getRegion("diff/hard.png"), 120, 380, 0, 2, () -> {
				Global.put("_difficulty", J.HARD);
				U.switchScreen(switchTarget, 0.5f);
			}));
			grid.add(new GridImage(A.getRegion("diff/lunatic.png"), 120, 200, 0, 3, () -> {
				Global.put("_difficulty", J.LUNATIC);
				U.switchScreen(switchTarget, 0.5f);
			}));
		}

		if (J.isGameModeExtra() || J.isGameModeSpellPractice() || J.isGameModeStagePractice()) {
			grid.add(new GridImage(A.getRegion("diff/extra.png"), 120, 20, 0, 4, () -> {
				Global.put("_difficulty", J.EXTRA);
				U.switchScreen(switchTarget, 0.5f);
			}));
		}
		grid.activate();
		if (Global.get("_difficulty") == null) {
			grid.selectFirst();
		} else {
			grid.select(0, J.difficulty() - 1);
		}
		grid.updateComponent();
		grid.update();
		input.addProcessor(grid);
	}

	@Override
	protected void onFadeIn(float duration) {
		grid.clearActions();
		grid.setPosition(-400, 0);
		grid.addAction(Actions.moveTo(0, 0, duration, Interpolation.sineOut));
	}

	@Override
	protected void onFadeOut(float duration) {
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
		return "difficultySelect";
	}

}
