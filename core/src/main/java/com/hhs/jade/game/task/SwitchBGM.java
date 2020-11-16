package com.hhs.jade.game.task;

import com.hhs.jade.game.Task;
import com.hhs.jade.util.BGM;

public class SwitchBGM implements Task {

	private String bgmName;

	public SwitchBGM(String bgmName) {
		this.bgmName = bgmName;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void update(int t) {
		BGM.play(bgmName);
	}

	@Override
	public void init() {
		
	}

}
