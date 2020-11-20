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
