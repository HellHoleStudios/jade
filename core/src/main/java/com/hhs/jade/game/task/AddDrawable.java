package com.hhs.jade.game.task;

import com.hhs.jade.game.Drawable;
import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

public class AddDrawable implements Task {

	public Drawable drawable;

	public AddDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void update(int t) {
		J.addDrawable(drawable);
	}

	@Override
	public void init() {

	}

}
