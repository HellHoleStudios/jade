package com.hhs.jade.game.entity;

import com.hhs.jade.game.Player;
import com.hhs.jade.game.shot.BulletData;
import com.hhs.jade.util.Collision;
import com.hhs.jade.util.J;

/**
 * Enemy Bullet base class
 * 
 * @author XGN
 *
 */
public class EnemyBullet extends Bullet {

	public int grazeCount = 1;

	public EnemyBullet() {
		super();
	}

	public EnemyBullet(BulletData data, int tag) {
		super(data, tag);
	}

	@Override
	public boolean checkCollision() {
		if (collide(J.getPlayer())) {
			J.onHit();
			return true;
		}

		if (closeTo(J.getPlayer()) && grazeCount != 0 && J.getPlayer().state == Player.PlayerState.Normal) {
			J.getPlayer().onGraze(this);
			grazeCount--;
		}
		return false;
	}

	public boolean closeTo(Player player) {
		return Collision.collide(player.getX(), player.getY(), player.getGrazeCollisionData(), x, y, data.collision);
	}

	public boolean collide(Player player) {
		return Collision.collide(this, player);
	}
}
