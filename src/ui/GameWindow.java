package ui;

import config.GlobalConfiguration;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GameScene currentScene;
	
	protected GameWindow(GameScene scene) {
		super(GlobalConfiguration.GAME_NAME);
		
		currentScene = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		switchScene(scene);
		setLocationRelativeTo(null);
	}
	
	protected void switchScene(GameScene scene) {
		if (currentScene != null) {
			remove(currentScene);
		}
		currentScene = scene;
		add(currentScene);
		pack();
		validate();
		setVisible(true);
		currentScene.requestFocusInWindow();
	}
	
	protected GameScene getCurrentScene() {
		return currentScene;
	}
	
}
