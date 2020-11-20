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

package com.hhs.jade.demo.player;

import com.hhs.jade.util.A;
import com.hhs.jade.util.B;
import com.hhs.jade.util.J;
import com.hhs.jade.util.SE;
import com.hhs.jade.game.entity.BasicPlayer;
import com.hhs.jade.game.entity.PlayerBullet;

public class PlayerMarisa extends BasicPlayer {
	
	public PlayerMarisa() {
		super(A.get("th10_player.atlas"), "th10_marisa", 5, 2, 3.5f, 5, 2,30,300);
	}
	
	private int shotTimer;

	@Override
	public void onShot() {
		super.onShot();
		
		shotTimer++;
		if (shotTimer >= 2) {

			SE.play("shoot");
			shotTimer = 0;

			B.setAngleSpeed(new PlayerBullet(B.get(153), 1024, 1000, 0.5f), x - 10, y, 90, 25);
			B.setAngleSpeed(new PlayerBullet(B.get(156), 1024, 1000, 0.5f), x + 10, y, 90, 25);
		}
	}
	
	@Override
	public float getItemCollectionLineHeight() {
		return -50;
	}
	
	@Override
	public void onBomb() {
		super.onBomb();
		
		J.clearBullets(true);
	}
}
