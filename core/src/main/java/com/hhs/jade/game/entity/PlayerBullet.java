/*
 * Java Danmaku Engine
 * Copyright (c) 2020-2020 Hell Hole Studios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hhs.jade.game.entity;

import com.hhs.jade.game.shot.BulletData;
import com.hhs.jade.util.Collision;
import com.hhs.jade.util.J;

/**
 * Player Bullet base class
 * 
 * @author XGN
 *
 */
public class PlayerBullet extends Bullet {

	/**
	 * The number of times the bullet can damage enemies. <br/>
	 * Calculated each frame. <br/>
	 */
	public int penetration;

	/**
	 * The damage <b>each</b> penetration.
	 */
	public float damage;

	public PlayerBullet() {
		super();
	}

	public PlayerBullet(BulletData data, int tag, int penetration, float damage) {
		super(data, tag);
		this.penetration = penetration;
		this.damage = damage;
	}

	@Override
	public boolean checkCollision() {

		boolean hit = false;

		for (int i = 0; i < J.getEnemies().size(); i++) {
			Enemy e = J.getEnemies().get(i);
			if (e == null) {
				continue;
			}
			if (penetration == 0) { // can't go forward anymore
				J.remove(this);
				return hit;
			}
			if (collide(e)) {
				hit = true;
				e.onHit(damage);
				penetration--;
				J.getEM().onEnemyDamage(this,e);
			}
		}

		return hit;
	}

	public boolean collide(Enemy e) {
		return Collision.collide(this, e);
	}
}
