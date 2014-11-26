package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import logic.IRenderable;
import config.GlobalConfiguration;

public class GamePlay extends GameScene {
	public GamePlay(){
		super();
		System.out.println("constructor gamePlay");
		this.setBackground(Color.GREEN);
		this.picture();
		setFocusable(true);
		requestFocus();
		setDoubleBuffered(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("paint");
		g.fillRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
		/*if (GlobalConfiguration.countScreen == 2) {
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
	public void picture(){
		try {
			startS = ImageIO.read(new File("startScreen.png"));
			optionS = ImageIO.read(new File("optionScreen.png"));
			System.err.println("try");
		} catch (IOException ex) {
			// handle exception...
		}
	}
	@Override
	public CopyOnWriteArrayList<IRenderable> getSortedRenderableObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
