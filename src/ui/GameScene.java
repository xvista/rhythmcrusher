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
<<<<<<< HEAD
		
		
	}
	
	private void addListener() {
	}
	
	protected void gameScreen(){
		this.removeAll();
		this.setBackground(Color.RED);
=======
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
>>>>>>> ee18094828bbc141a7a1158ae21a7464c45187e3
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;		
		
		g2.setBackground(Color.BLACK);
		
		Dimension dim = getSize();
		g2.clearRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		// reder all the objects
		//for(IRenderableObject renderable : renderableHolder.getSortedRenderableObject()){
		//	renderable.render(g2);
		//}
	}
	
	protected void applyResize() {
		setPreferredSize(new Dimension(GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT));
		validate();
	}


}
