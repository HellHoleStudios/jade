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

import com.hhs.jade.game.Entity;

public class Collision {

    public static abstract class CollisionData {
        public abstract float getBoundingHeight();

        public abstract float getBoundingWidth();
    }

    public static class Circle extends CollisionData {
        public float radius;

        public Circle() {

        }

        public Circle(float radius) {
            this.radius = radius;
        }

        @Override
        public float getBoundingWidth() {
            return radius * 2;
        }

        @Override
        public float getBoundingHeight() {
            return radius * 2;
        }
    }

    public static class Rectangle extends CollisionData {
        public float width, height;

        public Rectangle() {

        }

        public Rectangle(float width, float height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public float getBoundingWidth() {
            return width;
        }

        @Override
        public float getBoundingHeight() {
            return height;
        }
    }

    public static boolean collide(float x1, float y1, CollisionData m1, float x2, float y2, CollisionData m2) {
        if (m1 instanceof Circle) {
            Circle tmp1 = (Circle) m1;
            if (m2 instanceof Circle) {
                Circle tmp2 = (Circle) m2;
                if (U.config().orthoCircleCollision) {
                    return circleCircleOrtho(x1, y1, tmp1.radius, x2, y2, tmp2.radius);
                } else {
                    return circleCircle(x1, y1, tmp1.radius, x2, y2, tmp2.radius);
                }
            } else if (m2 instanceof Rectangle) {
                Rectangle tmp2 = (Rectangle) m2;
                return circleRect(x1, y1, tmp1.radius, x2, y2, tmp2.width, tmp2.height);
            }
        } else if (m1 instanceof Rectangle) {
            Rectangle tmp1 = (Rectangle) m1;
            if (m2 instanceof Circle) {
                Circle tmp2 = (Circle) m2;
                return circleRect(x2, y2, tmp2.radius, x1, y1, tmp1.width, tmp1.height);
            } else if (m2 instanceof Rectangle) {
                Rectangle tmp2 = (Rectangle) m2;
                return rectRect(x1, y1, tmp1.width, tmp2.height, x2, y2, tmp2.width, tmp2.height);
            }
        }
        return false;
    }

    public static boolean collide(Entity e1, Entity e2) {
        return collide(e1.getX(), e1.getY(), e1.getCollisionData(e2), e2.getX(), e2.getY(), e2.getCollisionData(e1));
    }

    public static boolean circleCircle(float x1, float y1, float r1, float x2, float y2, float r2) {
        if(!squareSquare(x1,y1,r1*2,x2,y2,r2*2)){
            return false;
        }
        if (M.dist2(x1, y1, x2, y2) <= M.sqr(r1 + r2)) {
            return true;
        }
        return false;
    }

    public static boolean circleCircleOrtho(float x1, float y1, float r1, float x2, float y2, float r2) {
        if(!squareSquare(x1,y1,r1*2,x2,y2,r2*2)){
            return false;
        }
        if (M.dist2(x1, y1, x2, y2) <= M.sqr(r1) + M.sqr(r2)) {
            return true;
        }
        return false;
    }

    public static boolean circleRect(float x1, float y1, float r, float x2, float y2, float w, float h) {
        x2 = M.clamp(x1, x2 - w / 2, x2 + w / 2);
        y2 = M.clamp(y1, y2 - h / 2, y2 + h / 2);

        return M.dist2(x1, y1, x2, y2) <= M.sqr(r);
    }

    public static boolean circleSquare(float x1, float y1, float r, float x2, float y2, float s) {
        x2 = M.clamp(x1, x2 - s / 2, x2 + s / 2);
        y2 = M.clamp(y1, y2 - s / 2, y2 + s / 2);

        return M.dist2(x1, y1, x2, y2) <= M.sqr(r);
    }

    public static boolean rectRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        return Math.abs(x1 - x2) <= (w1 + w2) / 2 && Math.abs(y1 - y2) <= (h1 + h2) / 2;
    }

    public static boolean squareSquare(float x1, float y1, float s1, float x2, float y2, float s2) {
        return rectRect(x1, y1, s1, s1, x2, y2, s2, s2);
    }

}
