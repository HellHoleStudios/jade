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
