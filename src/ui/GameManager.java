package ui;

import java.awt.Color;

public class GameManager {
	
	private static GameWindow gameWindow;
	private static GameScene sampleScene;
	
	public static void runGame() {
		sampleScene = new GameScene();
		sampleScene.setBackground(Color.GRAY);
		gameWindow = new GameWindow(sampleScene);
	}

}
