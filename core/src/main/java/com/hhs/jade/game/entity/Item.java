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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.hhs.jade.game.Entity;
import com.hhs.jade.game.Player;
import com.hhs.jade.util.Collision;
import com.hhs.jade.util.J;
import com.hhs.jade.util.M;
import com.hhs.jade.util.SE;
import com.hhs.jade.util.U;

/**
 * Base class for bullets
 */
public class Item extends Entity {

    public int tag;
    public int t;
    public float x, y;
    public Sprite sprite;
    public float boundingWidth, boundingHeight;
    public Collision.CollisionData collision;

    public Texture texture;

    public float angle, speed;

    public boolean canAutoCollect = true;
    public boolean follow;

    public Item() {

    }

    public Item(Texture t, int tag, float radius, float x, float y) {
        this.tag = tag;
        this.boundingWidth = t.getWidth();
        this.boundingHeight = t.getHeight();
        this.sprite = new Sprite(t);
        this.texture = t;
        this.collision = new Collision.Circle(radius);

        this.x = x;
        this.y = y;
        this.angle = 90;
        this.speed = 3;
    }

    public float getBoundingWidth() {
        return boundingWidth;
    }

    public float getBoundingHeight() {
        return boundingHeight;
    }

    @Override
    public Item setX(float nx) {
        x = nx;
        updateSpritePosition();
        return this;
    }

    @Override
    public Item setY(float ny) {
        y = ny;
        updateSpritePosition();
        return this;
    }

    @Override
    public Item setXY(float nx, float ny) {
        x = nx;
        y = ny;
        updateSpritePosition();
        return this;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public Item updateSpritePosition() {
        sprite.setPosition(x - sprite.getWidth() * sprite.getScaleX() / 2,
                y - sprite.getHeight() * sprite.getScaleY() / 2);
        return this;
    }

    public Item setRotaion(float degrees) {
        sprite.setRotation(degrees);
        return this;
    }

    public Item setScale(float scaleXY) {
        sprite.setScale(scaleXY);
        boundingWidth = texture.getWidth() * sprite.getScaleX();
        boundingHeight = texture.getHeight() * sprite.getScaleY();
        updateSpritePosition();
        return this;
    }

    public Item setColor(Color tint) {
        sprite.setColor(tint);
        return this;
    }

    public Item setAlpha(float a) {
        sprite.setAlpha(a);
        return this;
    }

    public float dist2(float x2, float y2) {
        return M.sqr(x - x2) + M.sqr(y - y2);
    }

    public void draw(Batch batch) {
        if (!U.outOfFrame(x, y, boundingWidth, boundingHeight)) {
            sprite.draw(batch);
        }
    }

    public boolean collide(Player player) {
        return Collision.collide(player.getX(), player.getY(), player.getCollisionData(null), x, y, collision);
    }

    public boolean closeTo(Player player) {
        return Collision.collide(player.getX(), player.getY(), player.getCollisionData(this), x, y, collision);
    }

    public void update(int frame) {
        if (collide(J.getPlayer())) { // collide with player
            onGet();
            J.remove(this);
        }
        if (closeTo(J.getPlayer())) { // collide with item sucking circle
            follow = true;
        }
        if (J.getPlayer().getY() >= J.getPlayer().getItemCollectionLineHeight() && canAutoCollect) {
            follow = true;
        }

        t++;

        if (follow) {
            speed = 10;
            angle = M.atan2(x, y, J.getPlayer().getX(), J.getPlayer().getY());
        } else {
            speed -= 0.05f;
        }

        x += speed * MathUtils.cosDeg(angle);
        y += speed * MathUtils.sinDeg(angle);

        if (U.outOfWorld(x, y, sprite.getWidth() * sprite.getScaleX(), sprite.getHeight() * sprite.getScaleY())) {
            J.remove(this);
            return;
        }

        updateSpritePosition();
    }

    /**
     * Implement this to make the item has super cool effects!!!
     */
    public void onGet() {
        J.getSession().event.onItem(this, J.getPlayer());
        SE.play("item");
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public Collision.CollisionData getCollisionData(Entity other) {
        return collision;
    }

}
