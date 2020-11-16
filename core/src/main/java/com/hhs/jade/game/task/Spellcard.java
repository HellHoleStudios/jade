package com.hhs.jade.game.task;

import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

/**
 * A spellcard
 * 
 * @author XGN
 *
 */
public class Spellcard {
	public int intitialHealth;
	public int hp;

	public int maxTime;
	public int timeLeft;

	public boolean isSurvival;
	public Task task;

	public long bonus;
	public boolean failBonus;

	private int firstT = -1;

	public Spellcard(int maxhp, int maxtime, boolean isSurvival, long bonus, Task task) {
		super();
		this.intitialHealth = maxhp;
		this.maxTime = maxtime;
		this.isSurvival = isSurvival;
		this.bonus = bonus;
		this.task = task;
	}

	public void init() {
		this.hp = this.intitialHealth;
		this.timeLeft = this.maxTime;
		this.failBonus = false;
		firstT = -1;

		task.init();
	}

	@Override
	public String toString() {
		return "Spellcard [initialHealth=" + intitialHealth + ", hp=" + hp + ", maxTime=" + maxTime + ", timeLeft="
				+ timeLeft + ", isSurvival=" + isSurvival + ", bonus=" + bonus + ", firstT=" + firstT + "]";
	}

	public void update(int t) {
		if (firstT == -1) {
			firstT = t;
		}
		task.update(t - firstT);

		timeLeft--;
	}

	public void onEnd() {
		J.clearBullets(true);
		J.clearEnemies(false);
	}

	public boolean isFinished() {
		return timeLeft <= 0 || hp <= 0;
	}

	/**
	 * Does not count if player died/bombed
	 * 
	 * @return - the current spellcard bonus score
	 */
	public long getBonus() {
		if (isSurvival) {
			return bonus;
		}

		/*
		 * according to DDC delta score=max÷(time-300f)x(2/3)
		 */
		if (this.maxTime - this.timeLeft <= 300) {
			return bonus;
		} else {
			double factor = 1 - (maxTime - timeLeft - 300d) / (maxTime - 300d) * (2 / 3.0);
			return Math.round(bonus * factor);
		}
	}
}
