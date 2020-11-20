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

import java.util.function.Consumer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.hhs.jade.util.U;

/**
 * A wrapper for Array that allows simple modification
 *
 * @param <T>
 * @author XGN
 */
public class EntityArray<T extends Entity> {
    public Array<T> entities;
    /**
     * The number of blank elements
     */
    public int blankCount;

    /**
     * The number of active elements
     */
    public int count;

    public int size() {
        return entities.size;
    }

    public T get(int index) {
        return entities.get(index);
    }

    public void add(T t) {
        count++;
        t.internalId = entities.size;
        entities.add(t);
    }

    public void remove(T t) {
        if (t.internalId != entities.size - 1)
            blankCount++;
        count--;
        entities.set(t.internalId, null);
        t.internalId = -1;
    }

    public void set(int index, T value) {
        entities.set(index, value);
    }

    public EntityArray() {
        entities = new Array<>(false, 1024);
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
                entities.get(i).draw(batch);
            }
        }
    }

    public void update(int frame) {
        for (int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
                entities.get(i).update(frame);
            }
        }
    }

    public void cleanUp() {
        U.cleanupArray(entities);
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).internalId = i;
        }
        blankCount = 0;
    }

    public void forEach(Consumer<T> function) {
        for (int i = 0; i < entities.size; i++) {
            if (entities.get(i) != null) {
            }
        }
    }

}
