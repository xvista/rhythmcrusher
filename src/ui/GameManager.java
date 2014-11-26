package ui;

import java.awt.Color;

import javafx.scene.media.MediaPlayer;

import javax.swing.JPanel;

import utility.InputUtility;
import config.GlobalConfiguration;
import logic.MainLogic;
import logic.ResourceLoader;

public class GameManager {

	public static GameWindow gameWindow;
	private static GameScene nextScene = null;
	
	public MainLogic logic;

	public static void runGame(MainLogic logic) {
		gameWindow = new GameWindow(new GameLoadScreen());
		//titleScene = new GameTitle();
		//gameWindow.switchScene(titleScene);
		
		ResourceLoader loader = new ResourceLoader();
		Thread loaderThread = new Thread(loader);
		loaderThread.start();
		try {
			loaderThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!loaderThread.isAlive()) {
			System.out.println("Hell yeah");
			gameWindow.switchScene(new GameTitle());
		}
		
		while (true){
			try {
				Thread.sleep(GlobalConfiguration.REFRESH_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameWindow.getCurrentScene().repaint();
			logic.logicUpdate();
			gameWindow.getCurrentScene().update();
			InputUtility.postUpdate();
			if(nextScene != null){
				gameWindow.switchScene(nextScene);
				nextScene = null;
			}
		}
	}

	public static void close() {
		gameWindow.dispose();
		System.exit(0);
	}

}
