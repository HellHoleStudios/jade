package com.hhs.jade.game.task;

import com.hhs.jade.game.EntityArray;
import com.hhs.jade.game.Task;
import com.hhs.jade.game.entity.Bullet;
import com.hhs.jade.game.entity.EnemyBullet;
import com.hhs.jade.util.J;

public class WaitForBulletClear implements Task {

	@Override
	public boolean isFinished() {
		EntityArray<Bullet> bullets = J.getBullets();
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i) != null && bullets.get(i) instanceof EnemyBullet) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void update(int t) {

	}

	@Override
	public void init() {

	}

}
