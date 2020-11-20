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

package com.hhs.jade.demo;

import com.badlogic.gdx.utils.Logger;
import com.hhs.jade.game.BossScene;
import com.hhs.jade.game.EventManager;
import com.hhs.jade.game.Player;
import com.hhs.jade.util.J;
import com.hhs.jade.util.U;
import com.hhs.jade.game.entity.BasicPlayer;
import com.hhs.jade.game.entity.Enemy;
import com.hhs.jade.game.entity.EnemyBullet;
import com.hhs.jade.game.entity.Item;
import com.hhs.jade.game.entity.PlayerBullet;
import com.hhs.jade.game.task.Spellcard;

public class DemoEventManager extends EventManager {
	public int playerLife = 3;
	public int playerBomb = 3;
	public long score = 0;
	public long maxPoint = 100000;

	public Logger logger = new Logger("DemoEventManager", U.config().logLevel);

	@Override
	public void onUpdate(int frame) {
		J.getPlayer().canBomb = (playerBomb != 0);
	}

	@Override
	public void onBomb(BasicPlayer basicPlayer) {
		playerBomb--;
	}

	@Override
	public void onRebirthStart(BasicPlayer basicPlayer) {
		playerLife--;
		playerBomb = 3;
	}

	@Override
	public void onItem(Item item, Player player) {
		score += maxPoint;
	}

	@Override
	public void onGraze(BasicPlayer basicPlayer, EnemyBullet eb) {
		maxPoint += 20;
		score += 20;
	}

	@Override
	public void onEnemyDamage(PlayerBullet playerBullet, Enemy e) {
		score += 10 * playerBullet.damage;
	}

	@Override
	public void onSpellcardFinish(Spellcard currentSpellcard, BossScene bossScene) {
		if (!bossScene.getCurrentSpellcard().failBonus) {
			score += currentSpellcard.getBonus();
			logger.info("Get Spellcard Bonus");
		}
	}
}
