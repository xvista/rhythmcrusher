package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.GlobalConfiguration;

public class GamePlay extends GameScene {
	public GamePlay(){
		super();
		this.setBackground(Color.GREEN);
		this.picture();
		setFocusable(true);
		requestFocus();
		setDoubleBuffered(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paint(g);
		if (GlobalConfiguration.countScreen == 2) {
			g.clearRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
			System.out.println("hello1");
			g.drawImage(startS,0, 0, null);
		}else if (GlobalConfiguration.countScreen == 3) {
			g.clearRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
			System.out.println("hello2");
			g.drawImage(optionS,0, 0, null);
		}
	}
	public void picture(){
		try {
			startS = ImageIO.read(new File("startScreen.png"));
			optionS = ImageIO.read(new File("optionScreen.png"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}
