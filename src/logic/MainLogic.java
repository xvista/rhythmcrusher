package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ui.GameBackground;
import utility.InputUtility;

public class MainLogic {
	
	private GameBackground background;
	
	private int zCounter = Integer.MIN_VALUE + 1;
	private boolean readyToRender = false; //For dealing with synchronization issue
	
	public void logicUpdate() {
		//InputUtility.postUpdate();
	}

}
