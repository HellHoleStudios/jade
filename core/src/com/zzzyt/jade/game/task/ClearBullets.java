package com.zzzyt.jade.game.task;

import com.zzzyt.jade.game.Task;
import com.zzzyt.jade.util.J;

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
