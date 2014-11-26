package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import config.GlobalConfiguration;
import utility.DrawingUtility;
import utility.InputUtility;
import utility.ResourceUtility;
import logic.IRenderable;
import logic.Simfile;

public class GameSongSelector extends GameScene {
	
	private CopyOnWriteArrayList<IRenderable> renderList = new CopyOnWriteArrayList<IRenderable>();
	
	private RenderObject background = ResourceUtility.BACKGROUND;
	private RenderObject screenSelectorBackground = ResourceUtility.SCREEN_SELECTOR_BACKGROUND;
	private RenderObject screenSelectorTitle = ResourceUtility.SCREEN_SELECTOR_TITLE;
	private RenderObject screenSelectorKey = ResourceUtility.SCREEN_SELECTOR_KEY;
	private RenderObject[] screenDifficulties = new RenderObject[] { ResourceUtility.SCREEN_SELECTOR_DIFFICULTY_BEGINNER,
																	 ResourceUtility.SCREEN_SELECTOR_DIFFICULTY_EASY, 
																	 ResourceUtility.SCREEN_SELECTOR_DIFFICULTY_MEDIUM, 
																	 ResourceUtility.SCREEN_SELECTOR_DIFFICULTY_HARD, 
																	 ResourceUtility.SCREEN_SELECTOR_DIFFICULTY_CHALLENGE };
	private RenderObject screenDifficulty = null;
	
	private int[] simfilesShowOrder = new int[7];
	
	private Simfile currentSimfile;
	private int[] difficulty;
	private int difficultyCount;
	private int difficultySelected = -1;
	
	private RenderObject previewBanner;
	
	private CopyOnWriteArrayList<TextInfo> textInfo = new CopyOnWriteArrayList<TextInfo>();
	
	public GameSongSelector() {
		super();
		
		GameManager.gameWindow.isPlaying = false;
		
		int simfileIndex = 0;
		for (int i = 0; i < 3; i++) {
			simfileIndex--;
			if (simfileIndex < 0) {
				simfileIndex = ResourceUtility.SIMFILES_COUNT - 1;
			}
		}
		for (int i = 0; i < 7; i++) {
			simfilesShowOrder[i] = simfileIndex;
			simfileIndex++;
			if (simfileIndex == ResourceUtility.SIMFILES_COUNT) {
				simfileIndex = 0;
			}
		}

		currentSimfile = ResourceUtility.SIMFILES[simfilesShowOrder[3]];
		previewBanner = new RenderObject(DrawingUtility.getImage(currentSimfile.getFolderPath() + currentSimfile.getBannerName()));
		updateDifficultyFlag();
		
		background.topLeftAnimationAt(0, 0);
		background.setZ(Integer.MIN_VALUE);
		background.play();
		//renderList.add(background);
		
		screenSelectorBackground.topLeftAnimationAt(0, 0);
		//renderList.add(screenSelectorBackground);
		
		screenSelectorKey.topLeftAnimationAt(0, 370);
		//renderList.add(screenSelectorKey);
		
		screenSelectorTitle.topLeftAnimationAt(30, 30);
		//renderList.add(screenSelectorTitle);
		
		previewBanner.topLeftAnimationAt(39, 106);
		//previewBanner.scale(previewBanner.getWidth(), 100);
		//renderList.add(previewBanner);
		
		for (RenderObject eachScreenDifficulties : screenDifficulties) {
			eachScreenDifficulties.topLeftAnimationAt(109, 289);
		}
		
		musicPreview();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Composite ori = g2.getComposite();
		for (TextInfo text : textInfo) {
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, text.opacity);
		    g2.setComposite(c);
			g2.setColor(text.color);
			g2.setFont(text.font.deriveFont(text.fontSize));
			g2.drawString(text.text, text.x, text.y);
		}
		g2.setComposite(ori);
	}

	@Override
	public synchronized CopyOnWriteArrayList<IRenderable> getSortedRenderableObject() {
		return renderList;
	}

	@Override
	public void update() {
		float[] listOpacity = {0.7f, 0.8f, 0.9f, 1.0f, 0.9f, 0.8f, 0.7f};
		int[] listX = {551, 534, 517, 500, 517, 534, 551};
		int[] listY = {36, 104, 174, 242, 310, 378, 446};
		
		textInfo.clear();
		for (int i = 0; i < simfilesShowOrder.length; i++) {
			textInfo.add(new TextInfo(ResourceUtility.SIMFILES[simfilesShowOrder[i]].getTitle(), listX[i], listY[i], ResourceUtility.FONT_HELVETICA_ROUND, 26, new Color(0x222644), listOpacity[i]));
			textInfo.add(new TextInfo(ResourceUtility.SIMFILES[simfilesShowOrder[i]].getArtist(), listX[i], listY[i] + 18, ResourceUtility.FONT_HELVETICA_ROUND, 16, new Color(0x38385b), listOpacity[i]));
		}
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			GamePlay gamePlay = new GamePlay(currentSimfile, difficultySelected, this);
			GameManager.gameWindow.switchScene(gamePlay);
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)) {
			GameManager.gameWindow.switchScene(new GameTitle());
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_UP)) {
			listRoll(KeyEvent.VK_UP);
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_DOWN)) {
			listRoll(KeyEvent.VK_DOWN);
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_LEFT)) {
			difficultyRoll(KeyEvent.VK_LEFT);
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_RIGHT)) {
			difficultyRoll(KeyEvent.VK_RIGHT);
		}
		
		renderList.clear();
		
		renderList.add(background);
		renderList.add(screenSelectorBackground);
		renderList.add(screenSelectorKey);
		renderList.add(screenSelectorTitle);
		renderList.add(previewBanner);
		renderList.add(screenDifficulty);
	}
	
	private void listRoll(int key) {
		int simfileIndex = simfilesShowOrder[3];
		if (key == KeyEvent.VK_DOWN) {
			simfileIndex++;
			if (simfileIndex == ResourceUtility.SIMFILES_COUNT) {
				simfileIndex = 0;
			}
		} else if (key == KeyEvent.VK_UP) {
			simfileIndex--;
			if (simfileIndex < 0) {
				simfileIndex = ResourceUtility.SIMFILES_COUNT - 1;
			}
		} 
		for (int i = 0; i < 3; i++) {
			simfileIndex--;
			if (simfileIndex < 0) {
				simfileIndex = ResourceUtility.SIMFILES_COUNT - 1;
			}
		}
		for (int i = 0; i < 7; i++) {
			simfilesShowOrder[i] = simfileIndex;
			simfileIndex++;
			if (simfileIndex == ResourceUtility.SIMFILES_COUNT) {
				simfileIndex = 0;
			}
		}
		currentSimfile = ResourceUtility.SIMFILES[simfilesShowOrder[3]];
		previewBanner = new RenderObject(DrawingUtility.getImage(currentSimfile.getFolderPath() + currentSimfile.getBannerName()));
		previewBanner.topLeftAnimationAt(39, 106);
		updateDifficultyFlag();
		musicPreview();
	}
	
	private void difficultyRoll(int key) {
		if (key == KeyEvent.VK_LEFT) {
			do {
				difficultySelected--;
				if (difficultySelected < 0) {
					difficultySelected = difficulty.length - 1;
				}
			} while (difficulty[difficultySelected] == 0);
		} else if (key == KeyEvent.VK_RIGHT) {
			do {
				difficultySelected++;
				if (difficultySelected == difficulty.length) {
					difficultySelected = 0;
				}
			} while (difficulty[difficultySelected] == 0);
		}
		screenDifficulty = screenDifficulties[difficultySelected];
	}
	
	private void updateDifficultyFlag() {
		difficulty = currentSimfile.getDifficulty();
		difficultyCount = 0;
		int min = -1;
		for (int i = 0; i < difficulty.length; i++) {
			if (difficulty[i] > 0) {
				difficultyCount++;
				if (min == -1) {
					min = i;
				}
			}
		}
		if (difficultySelected == -1) {
			difficultySelected = min;
		}
		if (difficulty[difficultySelected] == 0) {
			difficultySelected = min;
		}
		screenDifficulty = screenDifficulties[difficultySelected];
	}
	
	private void musicPreview() {
		if (GameManager.gameWindow.musicPlayer != null) {
			GameManager.gameWindow.musicPlayer.stop();
		}
		try {
			GameManager.gameWindow.music = new Media(GamePlay.class.getResource("/" + currentSimfile.getFolderPath() + currentSimfile.getMusicName()).toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		GameManager.gameWindow.musicPlayer = new MediaPlayer(GameManager.gameWindow.music);
		GameManager.gameWindow.musicPlayer.setStartTime(new Duration(currentSimfile.getSampleStart() * 1000));
		GameManager.gameWindow.musicPlayer.setStopTime(new Duration((currentSimfile.getSampleStart() + currentSimfile.getSampleLength()) * 1000));
		GameManager.gameWindow.musicPlayer.setCycleCount(Integer.MAX_VALUE);
		GameManager.gameWindow.musicPlayer.play();
	}
	
	private class TextInfo {
		public String text;
		public int x, y;
		public Font font;
		public float fontSize;
		public Color color;
		public float opacity;
		
		public TextInfo(String text, int x, int y, Font font, float fontSize, Color color) {
			this(text, x, y, font, fontSize, color, 1.0f);
		}
		
		public TextInfo(String text, int x, int y, Font font, float fontSize, Color color, float opacity) {
			this.text = text;
			this.x = x;
			this.y = y;
			this.font = font;
			this.fontSize = fontSize;
			this.color = color;
			this.opacity = opacity;
		}
	}

}
