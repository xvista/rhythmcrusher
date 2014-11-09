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
		setLayout(new BorderLayout());
		applyResize();
		validate();
		setDoubleBuffered(true);
		addListener();
		screenTitle();
		
		newGame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				gameScreen();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	private void addListener() {
	}
	protected void screenTitle(){
		//---------------Label-----------------------
		JLabel up=new JLabel("RhyThmCrusher",SwingConstants.CENTER);
		up.setFont(new Font("Tahoma",Font.BOLD,30));
		up.setBackground(Color.pink);
		up.setOpaque(true);
		add(up,BorderLayout.NORTH);
		//---------------Button---------------------
		FlowLayout flowLayOut=new FlowLayout(FlowLayout.CENTER,GlobalConfiguration.screenWidth/5,5);
		JPanel down=new JPanel();
		down.setBackground(Color.blue);
		down.setOpaque(true);
		add(down,BorderLayout.SOUTH);
		down.setLayout(flowLayOut);
		newGame=new JButton("New Game");
		down.add(newGame);
	}
	
	protected void gameScreen(){
		this.removeAll();
		this.setBackground(Color.RED);
	}
	
	protected void applyResize() {
		setPreferredSize(new Dimension(GlobalConfiguration.screenWidth, GlobalConfiguration.screenHeight));
		validate();
	}


}
