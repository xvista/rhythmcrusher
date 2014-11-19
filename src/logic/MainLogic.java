package logic;

import java.util.List;

import ui.GameBackground;

public class MainLogic implements IRenderableHolder {
	
	private GameBackground background;
	
	private int zCounter = Integer.MIN_VALUE + 1;
	private boolean readyToRender = false; //For dealing with synchronization issue

	@Override
	public synchronized List<IRenderable> getSortedRenderableObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
