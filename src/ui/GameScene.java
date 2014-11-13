package ui;

import java.awt.Dimension;

import javax.swing.JPanel;

import config.GlobalConfiguration;

@SuppressWarnings("serial")
public class GameScene extends JPanel {
	
	protected GameScene() {
		super();
		applyResize();
		validate();
		setDoubleBuffered(true);
		addListener();
	}
	
	private void addListener() {
		
	}
	
	protected void applyResize() {
		setPreferredSize(new Dimension(GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT));
		validate();
	}

}
