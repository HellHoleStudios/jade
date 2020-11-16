package com.hhs.jade.demo.difficulty;

import com.hhs.jade.demo.stageextra.StageExtra;
import com.hhs.jade.game.task.Sequence;

public class DifficultyExtra extends Sequence {

	@Override
	public void init() {
		super.init();
		add(new StageExtra());
	}
}
