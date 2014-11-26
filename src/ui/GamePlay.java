package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.imageio.ImageIO;

import utility.DrawingUtility;
import utility.InputUtility;
import logic.IRenderable;
import logic.Simfile;
import config.GlobalConfiguration;

public class GamePlay extends GameScene {
	
	private CopyOnWriteArrayList<IRenderable> renderList = new CopyOnWriteArrayList<IRenderable>();
	
	private GameSongSelector previousScene = null;
	private int difficulty = -1;
	private Simfile simfile = null;
	
	private Timer beatTimer;
	private Media clapSound;
	private MediaPlayer clapSoundPlayer;
	
	private RenderObject background = null;
	
	public GamePlay(Simfile simfile, int difficulty, GameSongSelector previousScene) {
		super();
		this.simfile = simfile;
		this.difficulty = difficulty;
		this.previousScene = previousScene;
		
		GameManager.gameWindow.isPlaying = true;
		
		background = new RenderObject(DrawingUtility.getImage(simfile.getFolderPath() + simfile.getBackgroundName()));
		background.topLeftAnimationAt(0, 0);
		
		if (GameManager.gameWindow.musicPlayer != null) {
			GameManager.gameWindow.musicPlayer.stop();
		}
		try {
			GameManager.gameWindow.music = new Media(GamePlay.class.getResource("/" + simfile.getFolderPath() + simfile.getMusicName()).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameManager.gameWindow.musicPlayer = new MediaPlayer(GameManager.gameWindow.music);
		GameManager.gameWindow.musicPlayer.play();
		
		try {
			clapSound = new Media(GamePlay.class.getResource("/musics/clap.mp3").toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//clapSoundPlayer = new MediaPlayer(clapSound);
		
		beatTimer = new Timer();
		beatTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				clapSoundPlayer = new MediaPlayer(clapSound);
				clapSoundPlayer.play();
			}
		}, (long)(60.0 / 170 * 1000), (long)(60.0 / 170 * 1000));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*System.out.println("paint");
		g.fillRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
		if (GlobalConfiguration.countScreen == 2) {
			g.clearRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
			System.out.println("paint1");
			g.drawImage(startS,0, 0, null);
		}
		else if (GlobalConfiguration.countScreen == 3) {
			g.clearRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
			System.out.println("paint2");
			g.drawImage(optionS,0, 0, null);
		}*/
	}
	
	@Override
	public CopyOnWriteArrayList<IRenderable> getSortedRenderableObject() {
		return renderList;
	}
	
	@Override
	public void update() {
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)) {
			GameManager.gameWindow.isPlaying = false;
			beatTimer.cancel();
			GameManager.gameWindow.switchScene(previousScene);
		}
		renderList.clear();
		renderList.add(background);
	}
}
