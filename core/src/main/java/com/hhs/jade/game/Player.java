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

import com.hhs.jade.util.Collision.CollisionData;
import com.hhs.jade.game.entity.EnemyBullet;

public abstract class Player extends Entity {

    /**
     * Player state: PLOK, PLDB, PLRB <br/>
     */
    public PlayerState state;

    public enum PlayerState {
        /**
         * Normal player state
         */
        Normal,
        /**
         * Waiting for deadbombing state
         */
        DeathBombing,
        /**
         * Waiting for respawn state
         */
        Respwaning
    }

    /**
     * allow to trigger {@link #onShot()} {@link #onBomb()}?
     */
    public boolean canBomb, canShot;

    /**
     * Event shot
     */
    public abstract void onShot();

    /**
     * Event bomb
     */
    public abstract void onBomb();

    /**
     * Event hit <br/>
     * Player can still deathbomb
     */
    public abstract void onHit();

    /**
     * Event rebirth start <br/>
     * Player fails to deathbomb and now waiting for rebirth frame
     */
    public abstract void onRebirthStart();

    /**
     * Event rebirth end <br/>
     * Player has just rebirthed
     */
    public abstract void onRebirthEnd();

    /**
     * The item collection line height.
     *
     * @return
     */
    public float getItemCollectionLineHeight() {
        return 0;
    }

    public abstract void onGraze(EnemyBullet eb);

    public abstract CollisionData getGrazeCollisionData();

}
