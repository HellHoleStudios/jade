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

package com.hhs.jade.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.hhs.jade.demo.DemoEventManager;
import com.hhs.jade.util.A;
import com.hhs.jade.util.J;
import com.hhs.jade.game.task.Spellcard;

/**
 * Timer Actor will display the timer of the spellcard on screen
 * @author XGN
 *
 */
public class TimerActor extends Label {
	
	public Spellcard sc;
	
	public TimerActor(Spellcard sc) {
		
		super("00.00", A.getUILabelStyle(30));
		setOrigin(Align.center);
		setPosition(200, 600);
		addAction(Actions.moveBy(0,-200,1f,Interpolation.sineOut));
		
		this.sc=sc;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		//update timer
		float realtime=sc.timeLeft/60f;
		setText(String.format("%02d", (int)realtime)
				+"."
				+String.format("%02d", (int)(realtime*100)%100)
				+"\nHP:"+sc.hp+"/"+sc.intitialHealth
				+"\nBonus:"+sc.getBonus()
				+"\nLife:"+((DemoEventManager)(J.getEM())).playerLife
				+"\nBomb:"+((DemoEventManager)(J.getEM())).playerBomb
				+"\nScore:"+((DemoEventManager)(J.getEM())).score
				+"\nValue:"+((DemoEventManager)(J.getEM())).maxPoint);
		
		if(realtime<=5) {
			getStyle().fontColor=Color.RED;
		}else if(realtime<=10) {
			getStyle().fontColor=Color.PINK;
		}

//		if(J.getPlayer().getY()>=-200) {
//			addAction(Actions.alpha(0.5f,0.5f));
//		}else {
//			addAction(Actions.alpha(1f,0.5f));
//		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
}
