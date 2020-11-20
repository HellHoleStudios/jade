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

package com.hhs.jade.game.shot;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hhs.jade.util.Collision;
import com.hhs.jade.util.Collision.CollisionData;

public class BulletData {

    public int id;
    public String name;
    public String render;
    public float originX, originY;
    public BulletTexture texture;
    public TextureRegion delayTexture;
    public Color delayColor;
    public float spinVelocity;
    public CollisionData collision;
    public float rotation;

    public BulletData(ShotSheet parent, RawShotSheet.RawBulletData raw) {
        this.id = raw.id;
        this.name = raw.name;

        if (raw.render == null) {
            this.render = "ALPHA";
        } else {
            this.render = raw.render;
        }

        if (raw.spinVelocity == null) {
            this.spinVelocity = 0;
        } else {
            this.spinVelocity = raw.spinVelocity;
        }

        if (raw.rotation == null) {
            this.rotation = 0;
        } else {
            this.rotation = raw.rotation;
        }

        if (raw.frames == null) {
            this.texture = new BulletTexture(parent.atlas, name);
        } else {
            this.texture = new BulletTexture(parent.atlas, name, raw.frames);
        }

        if ("Circle".equals(raw.collisionMethod)) {
            this.collision = new Collision.Circle(raw.collisionData[0]);
        } else if ("Rectangle".equals(raw.collisionMethod)) {
            this.collision = new Collision.Rectangle(raw.collisionData[0], raw.collisionData[1]);
        } else {
            this.collision = new Collision.Circle(raw.collisionData[0]);
        }

        if (raw.originX == null) {
            this.originX = texture.getMaxWidth() / 2;
        } else {
            this.originX = raw.originX;
        }

        if (raw.originY == null) {
            this.originY = texture.getMaxHeight() / 2;
        } else {
            this.originY = raw.originY;
        }

        this.delayColor = Color.valueOf(raw.delayColor);
        this.delayTexture = parent.atlas.findRegion(raw.delaySrc);
    }
}