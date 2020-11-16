package com.hhs.jade.demo.difficulty;

import com.hhs.jade.demo.stage1.Stage1;
import com.hhs.jade.util.J;
import com.hhs.jade.game.task.Sequence;

public class DifficultyRegular extends Sequence {

	private int difficulty;

	public DifficultyRegular(int difficulty) {
		super();
		this.difficulty = difficulty;
	}

	@Override
	public void init() {
		super.init();
		add(J.diffSelect(difficulty, new Stage1(), new Stage1(), new Stage1(), new Stage1()));
	}
}
