package com.hhs.jade.game.task;

import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

public class WaitForBossScene implements Task {

	@Override
	public boolean isFinished() {
		return J.getSession().bossScene==null;
	}

	@Override
	public void update(int t) {

	}

	@Override
	public void init() {

	}

}
