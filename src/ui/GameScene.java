package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import logic.IRenderable;
import logic.IRenderableHolder;
import utility.InputUtility;
import config.GlobalConfiguration;

@SuppressWarnings("serial")
public abstract class GameScene extends JPanel implements IRenderableHolder {
	
	protected BufferedImage backGround;
	protected BufferedImage startS;
	protected BufferedImage optionS;
	
	public CopyOnWriteArrayList<RenderObject> renderList;
	
	protected GameScene() {
		super();
		applyResize();
		validate();
		setDoubleBuffered(true);
		addListener();
		setFocusable(true);
		requestFocus();
	}
	
	protected void addListener() {
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (InputUtility.getKeyPressed(arg0.getKeyCode())) {
					InputUtility.setKeyPressed(arg0.getKeyCode(), false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (!InputUtility.getKeyPressed(arg0.getKeyCode())) {
					InputUtility.setKeyPressed(arg0.getKeyCode(), true);
					InputUtility.setKeyTriggered(arg0.getKeyCode(), true);
				}
			}
		});
	}
	
	protected void applyResize() {
		setPreferredSize(new Dimension(GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT));
		validate();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
		if (GameManager.gameWindow.getCurrentScene().getSortedRenderableObject() != null) {
		CopyOnWriteArrayList<IRenderable> objects = GameManager.gameWindow.getCurrentScene().getSortedRenderableObject();
		for (IRenderable object : objects) {
			object.render(g2);
			((RenderObject) object).updateAnimation();
		}
		}
	}

	@Override
	public abstract CopyOnWriteArrayList<IRenderable> getSortedRenderableObject();
	
	public abstract void update();

}
