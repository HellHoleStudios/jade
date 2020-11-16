package com.hhs.jade.game.task;

import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

public class ClearBullets implements Task {

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void update(int t) {
		J.clearBullets(true);
	}

	@Override
	public void init() {
		
	}

}
