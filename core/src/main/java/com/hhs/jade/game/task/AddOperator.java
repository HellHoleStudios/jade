package com.hhs.jade.game.task;

import com.hhs.jade.game.Operator;
import com.hhs.jade.game.Task;
import com.hhs.jade.util.J;

public class AddOperator implements Task {

	public int tag;
	public Operator operator;

	public AddOperator(int tag, Operator operator) {
		this.tag = tag;
		this.operator = operator;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void update(int t) {
		J.addOperator(tag, operator);
	}

	@Override
	public void init() {

	}

}
