package com.hhs.jade.game.operator;

import com.hhs.jade.game.Operator;
import com.hhs.jade.game.entity.Bullet;

public class AngularVelocity implements Operator {

	private float delta, duration;

	public AngularVelocity(float delta, int duration) {
		this.delta = delta;
		this.duration = duration;
	}

	@Override
	public void apply(Bullet bullet, int t) {
		if (t < duration) {
			bullet.angle += delta;
		}
	}

}
