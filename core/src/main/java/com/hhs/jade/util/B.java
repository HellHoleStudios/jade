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

package com.hhs.jade.util;

import com.hhs.jade.game.shot.BulletData;
import com.hhs.jade.game.shot.ShotSheet;
import com.hhs.jade.game.entity.Bullet;
import com.hhs.jade.game.entity.EnemyBullet;

public class B {

    public static ShotSheet defaultSheet;

    public static ShotSheet setSheet(ShotSheet sheet) {
        B.defaultSheet = sheet;
        return sheet;
    }

    public static void setSheet(String sheet) {
        B.defaultSheet = A.get(sheet);
    }

    public static BulletData get(int id) {
        return defaultSheet.findBullet(id);
    }

    public static BulletData get(String name) {
        return defaultSheet.findBullet(name);
    }

    public static Bullet setAngleSpeed(Bullet bullet, float x, float y, float angle, float speed) {
        angle = M.normalizeAngle(angle);
        bullet.sprite.setRotation(angle - bullet.data.rotation);
        bullet.setXY(x, y);
        bullet.setSpeed(speed);
        bullet.setAngle(angle);
        J.add(bullet);
        return bullet;
    }

    public static Bullet create(float x, float y, float angle, float speed, int id, int tag) {
        return setAngleSpeed(new EnemyBullet(get(id), tag), x, y, angle, speed);
    }

    public static Bullet towards(float x, float y, float angle, float speed, String name, int tag) {
        return create(x, y, angle, speed, defaultSheet.getId(name), tag);
    }

    public static Bullet towards(float x, float y, float targetX, float targetY, float speed, int id, int tag) {
        return create(x, y, M.atan2(x, y, targetX, targetY), speed, id, tag);
    }

    public static Bullet towards(float x, float y, float targetX, float targetY, float speed, String name, int tag) {
        return towards(x, y, targetX, targetY, speed, defaultSheet.getId(name), tag);
    }

    public static Bullet towards(float x, float y, float speed, int id, int tag) {
        return towards(x, y, J.playerX(), J.playerY(), speed, id, tag);
    }

    public static Bullet towards(float x, float y, float speed, String name, int tag) {
        return towards(x, y, speed, defaultSheet.getId(name), tag);
    }
}
