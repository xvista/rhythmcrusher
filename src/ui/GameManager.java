package ui;

import java.awt.Color;

public class GameManager {
	
	public static GameWindow gameWindow;
	public static GameScene sampleScene;
	
	public static void runGame() {
		//sampleScene = new GameScene();
		//sampleScene.setBackground(Color.GREEN);
		sampleScene=new GameTitle();
		gameWindow = new GameWindow(sampleScene);

		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sampleScene.repaint();
			if(sampleScene instanceof GamePlay)
				System.out.println("LoL");
		}
	}

}
