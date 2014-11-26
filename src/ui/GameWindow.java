package ui;

import config.GlobalConfiguration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	private GameScene currentScene;
	public Media music;
	public MediaPlayer musicPlayer;
	public boolean isPlaying = false;
	
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
