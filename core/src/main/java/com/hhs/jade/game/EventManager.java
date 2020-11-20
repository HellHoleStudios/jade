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

package com.hhs.jade.game;

import com.hhs.jade.game.entity.BasicPlayer;
import com.hhs.jade.game.entity.Enemy;
import com.hhs.jade.game.entity.EnemyBullet;
import com.hhs.jade.game.entity.Item;
import com.hhs.jade.game.entity.PlayerBullet;
import com.hhs.jade.game.task.Spellcard;

/**
 * An event manager receives event and you can do custom things! Hooray <br/>
 * This class implements an empty event manager
 *
 * @author XGN
 */
public class EventManager {

    public void onBomb(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    public void onRebirthStart(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    public void onRebirthEnd(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    public void onPlayerHit(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    public void onPlayerShoot(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    /**
     * Please note that onBomb will also be fired along with this event
     *
     * @param basicPlayer
     */
    public void onPlayerDeathbomb(BasicPlayer basicPlayer) {
        // TODO Auto-generated method stub

    }

    public void onUpdate(int frame) {
        // TODO Auto-generated method stub

    }

    public void onDraw() {
        // TODO Auto-generated method stub

    }

    public void onItem(Item item, Player player) {
        // TODO Auto-generated method stub

    }

    public void onGraze(BasicPlayer basicPlayer, EnemyBullet eb) {
        // TODO Auto-generated method stub

    }

    public void onEnemyDamage(PlayerBullet playerBullet, Enemy e) {
        // TODO Auto-generated method stub

    }

    public void onPhaseFinish(BossScene bossScene) {
        // TODO Auto-generated method stub

    }

    public void onSpellcardFinish(Spellcard currentSpellcard, BossScene bossScene) {
        // TODO Auto-generated method stub

    }

}
