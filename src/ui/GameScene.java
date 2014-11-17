package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.misc.Cleaner;
import config.GlobalConfiguration;

@SuppressWarnings("serial")
public class GameScene extends JPanel {
	protected String start = "chooseStart.png";
	protected String option = "option.png";
	protected String exit = "exit.png";
	
	protected BufferedImage backGround;
	protected BufferedImage startButton;
	protected BufferedImage optionButton;
	protected BufferedImage exitButton;
	protected BufferedImage startS ;
	protected BufferedImage optionS ;
	
	protected GameScene() {
		super();
		applyResize();
		validate();
		setDoubleBuffered(true);
		addListener();
	}
	
	protected void addListener() {
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	protected void applyResize() {
		setPreferredSize(new Dimension(GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT));
		validate();
	}


}
