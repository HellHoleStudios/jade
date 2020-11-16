package com.hhs.jade.demo.stageextra;

import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.util.M;
import com.hhs.jade.game.operator.AngularVelocity;
import com.hhs.jade.game.task.BasicTask;

public class StageExtraMid1 extends BasicTask {

	private float tmpf;

	@Override
	public void init() {
		super.init();
		setUpdateFunc((frame) -> {
			if (frame >= 15 * 60) {
				J.clearOperators();
				terminate();
				return;
			}
			if (frame == 8 * 60) {
				J.addOperator(0, new AngularVelocity(0.5f, 180));
			}
			if (frame % 3 == 0) {
				for (int i = 0; i < 7; i++) {
					B.create(0, -100, i * 360f / 7f + tmpf, M.min(frame % 60, 60 - frame % 60) / 60f + 1, i + 9, 0);
				}
			}
			tmpf += (float) (frame / 30) / 8 + 1;
		});
		this.tmpf = 0;
	}
}
