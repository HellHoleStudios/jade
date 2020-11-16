package com.hhs.jade.game.operator;

import com.hhs.jade.game.Operator;
import com.hhs.jade.game.entity.Bullet;

public class Acceleration implements Operator {

	private float acceleration, target;

	public Acceleration(float acceleration, float target) {
		this.acceleration = acceleration;
		this.target = target;
	}

	@Override
	public void apply(Bullet bullet, int t) {
		if (acceleration > 0) {
			if (bullet.speed < target) {
				bullet.speed += acceleration;
			}
		} else {
			if (bullet.speed > target) {
				bullet.speed += acceleration;
			}
		}
	}

}
