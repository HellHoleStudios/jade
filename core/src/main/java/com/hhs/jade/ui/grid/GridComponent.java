package com.hhs.jade.ui.grid;

public interface GridComponent {
	public GridComponent setParent(Grid parent);
	
	public Grid getPartent();
	
	public GridComponent activate();

	public GridComponent deactivate();

	public GridComponent enable();

	public GridComponent disable();

	public boolean isActive();
	
	public boolean isEnabled();

	public void update();

	public int getGridX();

	public int getGridY();

	public void trigger();
	
	public boolean hasSound();
}
