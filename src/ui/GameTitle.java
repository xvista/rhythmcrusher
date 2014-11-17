package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.GlobalConfiguration;

public class GameTitle extends GameScene {

	public GameTitle() {
		super();
		this.setPreferredSize(new Dimension(GlobalConfiguration.SCREEN_WIDTH,
				GlobalConfiguration.SCREEN_HEIGHT));
		// ===============addImage=====================
		this.button();
		setFocusable(true);
		requestFocus();
		setDoubleBuffered(true);
		// ========================keyListener=========================
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				GlobalConfiguration.key = false;

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN
						&& !GlobalConfiguration.key) {
					if (GlobalConfiguration.count == 3)
						GlobalConfiguration.count = 0;
					GlobalConfiguration.count++;
					if (GlobalConfiguration.count == 2) {
						start = "start.png";
						option = "chooseOption.png";
						exit = "exit.png";
						button();
					} else if (GlobalConfiguration.count == 3) {
						start = "start.png";
						option = "option.png";
						exit = "chooseExit.png";
						button();
					} else if (GlobalConfiguration.count == 1) {
						start = "chooseStart.png";
						option = "option.png";
						exit = "exit.png";
						button();
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_UP
						&& !GlobalConfiguration.key) {
					GlobalConfiguration.count--;
					if (GlobalConfiguration.count == 0)
						GlobalConfiguration.count = 3;
					if (GlobalConfiguration.count == 2) {
						start = "start.png";
						option = "chooseOption.png";
						exit = "exit.png";
						button();
					} else if (GlobalConfiguration.count == 3) {
						start = "start.png";
						option = "option.png";
						exit = "chooseExit.png";
						button();
					} else if (GlobalConfiguration.count == 1) {
						start = "chooseStart.png";
						option = "option.png";
						exit = "exit.png";
						button();
					}
					GlobalConfiguration.key = true;
				} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER
						&& !GlobalConfiguration.key) {
					if (GlobalConfiguration.count == 1) {
						GlobalConfiguration.countScreen = 2;
						GameManager.changeScreen();
					} else if (GlobalConfiguration.count == 2) {
						GlobalConfiguration.countScreen = 3;
						GameManager.changeScreen();
					} else if (GlobalConfiguration.count == 3) {
						GameManager.close();
					}
				}

			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, null);
		g.drawImage(startButton, 250, 230, null);
		g.drawImage(optionButton, 250, 300, null);
		g.drawImage(exitButton, 250, 370, null);
		/*
		  if (GlobalConfiguration.countScreen == 2) { g.clearRect(0, 0,
		  GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
		  System.out.println("hello1"); g.drawImage(startS,0, 0, null); }else
		  if (GlobalConfiguration.countScreen == 3) { g.clearRect(0, 0,
		  GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
		  System.out.println("hello2"); g.drawImage(optionS,0, 0, null); }
		*/ 

	}

	public void button() {
		try {
			backGround = ImageIO.read(new File("X.png"));
			startButton = ImageIO.read(new File(start));
			optionButton = ImageIO.read(new File(option));
			exitButton = ImageIO.read(new File(exit));
			startS = ImageIO.read(new File("startScreen.png"));
			optionS = ImageIO.read(new File("optionScreen.png"));
		} catch (IOException ex) {
			// handle exception...
		}
	}

}
