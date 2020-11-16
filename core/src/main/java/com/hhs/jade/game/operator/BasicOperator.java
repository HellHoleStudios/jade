package com.hhs.jade.game.operator;

import com.hhs.jade.game.Operator;
import com.hhs.jade.game.entity.Bullet;

public class BasicOperator implements Operator {

	private Appliable appliable;

	public BasicOperator(Appliable appliable) {
		this.appliable = appliable;
	}

	@Override
	public void apply(Bullet bullet, int t) {
		appliable.apply(bullet, t);
	}

	@FunctionalInterface
	public static interface Appliable {
		public void apply(Bullet bullet, int t);
	}

}
