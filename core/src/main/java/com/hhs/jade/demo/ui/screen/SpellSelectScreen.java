package com.hhs.jade.demo.ui.screen;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.hhs.jade.demo.stage1.Stage1Mid1;
import com.hhs.jade.demo.stage1.Stage1Mid2;
import com.hhs.jade.game.Task;
import com.hhs.jade.util.Global;
import com.hhs.jade.util.J;
import com.hhs.jade.util.U;
import com.hhs.jade.demo.stageextra.StageExtraMid1;
import com.hhs.jade.demo.stageextra.StageExtraMid2;
import com.hhs.jade.game.task.Sequence;
import com.hhs.jade.game.task.Wait;
import com.hhs.jade.game.task.WaitForBulletClear;
import com.hhs.jade.ui.grid.Grid;
import com.hhs.jade.ui.grid.GridButton;
import com.hhs.jade.ui.screen.BasicScreen;

public class SpellSelectScreen extends BasicScreen {

	private Grid grid;

	private Array<String> names;
	private Array<Task> spells;

	public SpellSelectScreen() {
		super();
	}

	@Override
	public void show() {
		init("mus/E.0109.ogg", "bg/select.png");

		this.grid = new Grid(true);
		st.addActor(grid);

		this.names = new Array<String>();
		this.spells = new Array<Task>();

		if (J.difficulty() == J.EXTRA) {
			names.add("Extra Stage: Mid 1");
			spells.add(new StageExtraMid1());
			names.add("Extra Stage: Mid 2");
			spells.add(new StageExtraMid2());
		} else {
			names.add("Stage 1: Mid 1");
			spells.add(new Stage1Mid1());
			names.add("Stage 1: Mid 2");
			spells.add(new Stage1Mid2());
		}
		for (int i = 0; i < names.size; i++) {
			final Task tmp = constructStage(spells.get(i));
			grid.add(new GridButton(names.get(i), 48, 120, 720 - i * 60, 600, 60, 0, i, () -> {
				Global.put("_practice", tmp);
				U.switchScreen("playerSelect", 0.5f);
			}));
		}
		grid.selectFirst();
		grid.activate();
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
		U.switchScreen("difficultySelect", 0.5f);
		super.onQuit();
	}

	@Override
	public String getName() {
		return "spellSelect";
	}

	private Sequence constructStage(Task spell) {
		return new Sequence() {
			@Override
			public void init() {
				super.init();
				add(new Wait(120));
				add(spell);
				add(new WaitForBulletClear());
				add(new Wait(60));
			}
		};
	}

}
