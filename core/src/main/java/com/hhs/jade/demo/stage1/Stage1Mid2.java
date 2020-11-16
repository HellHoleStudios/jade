package com.hhs.jade.demo.stage1;

import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.util.M;
import com.hhs.jade.game.task.BasicTask;

public class Stage1Mid2 extends BasicTask {

	private float tmpf;

	@Override
	public void init() {
		super.init();
		setUpdateFunc((frame) -> {
			if (frame >= 36 * 60) {
				terminate();
				return;
			}
			if (frame % J.diffSelect(16, 10, 5, 2) == 0) {
				for (int i = 0; i < 360; i += 72) {
					B.towards(0, -100, i + tmpf, 2, "DS_RICE_S_RED", 0);
					B.towards(0, -100, i - tmpf, 2, "DS_RICE_S_BLUE", 0);
				}
				tmpf += M.sin(frame / 2f) * 6;
			}
		});
		this.tmpf = 72;
	}

}
