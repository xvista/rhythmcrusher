package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.misc.Cleaner;
import config.GlobalConfiguration;

@SuppressWarnings("serial")
public class GameScene extends JPanel {
	JPanel optionPanel=new JPanel(new BorderLayout());
	JButton newGame;
	
	protected GameScene() {
		super();
		applyResize();
		validate();
		setDoubleBuffered(true);
		addListener();
		
		
	}
	
	private void addListener() {
	}
	
	protected void gameScreen(){
		this.removeAll();
		this.setBackground(Color.RED);
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
