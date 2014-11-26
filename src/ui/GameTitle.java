package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.input.KeyCode;

import javax.annotation.Resources;
import javax.imageio.ImageIO;

import com.sun.swing.internal.plaf.synth.resources.synth;

import logic.IRenderable;
import logic.IRenderableHolder;
import utility.AudioUtility;
import utility.DrawingUtility;
import utility.InputUtility;
import utility.ResourceUtility;
import config.GlobalConfiguration;

public class GameTitle extends GameScene {
	
	private int menuSelected = 0;
	private final int MENU_COUNT = 3;
	
	private CopyOnWriteArrayList<IRenderable> renderList = new CopyOnWriteArrayList<IRenderable>();
	
	private RenderObject background = ResourceUtility.BACKGROUND;
	private RenderObject menuStart = ResourceUtility.MENU_START_SELECTED;
	private RenderObject menuOptions = ResourceUtility.MENU_OPTIONS;
	private RenderObject menuExit = ResourceUtility.MENU_EXIT;

	public GameTitle() {
		super();

		background.topLeftAnimationAt(0, 0);
		background.setZ(Integer.MIN_VALUE);
		background.play();
		
		AudioUtility.playMusic();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public synchronized CopyOnWriteArrayList<IRenderable> getSortedRenderableObject() {
		return renderList;
	}

	@Override
	public void update() {
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ESCAPE)) {
			GameManager.close();
		}
		
		if (InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)) {
			if (menuSelected == 0) {
				GameManager.gameWindow.switchScene(new GameSongSelector());
			} else if (menuSelected == 2) {
				GameManager.close();
			}
		}
		
		if (InputUtility.getKeyTriggered(KeyEvent.VK_DOWN)) {
			menuSelected++;
			if (menuSelected >= MENU_COUNT) {
				menuSelected = 0;
			}
		} else if (InputUtility.getKeyTriggered(KeyEvent.VK_UP)) {
			menuSelected--;
			if (menuSelected < 0) {
				menuSelected = MENU_COUNT - 1;
			}
		}
		
		menuStart = ResourceUtility.MENU_START;
		menuOptions = ResourceUtility.MENU_OPTIONS;
		menuExit = ResourceUtility.MENU_EXIT;
		
		if (menuSelected == 0) {
			menuStart = ResourceUtility.MENU_START_SELECTED;
		} else if (menuSelected == 1) {
			menuOptions = ResourceUtility.MENU_OPTIONS_SELECTED;
		} else if (menuSelected == 2) {
			menuExit = ResourceUtility.MENU_EXIT_SELECTED;
		}
		
		menuStart.centerAnimationAt(GlobalConfiguration.SCREEN_WIDTH_CENTER, 300);
		menuOptions.centerAnimationAt(GlobalConfiguration.SCREEN_WIDTH_CENTER, 350);
		menuExit.centerAnimationAt(GlobalConfiguration.SCREEN_WIDTH_CENTER, 400);
		
		renderList.clear();

		renderList.add(background);
		renderList.add(menuStart);
		renderList.add(menuOptions);
		renderList.add(menuExit);
	}

}
