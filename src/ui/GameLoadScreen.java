package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resources;
import javax.swing.JLabel;

import utility.ResourceUtility;
import config.GlobalConfiguration;
import exception.SimfileException;
import logic.IRenderable;
import logic.ResourceLoader;
import logic.Simfile;

public class GameLoadScreen extends GameScene {
	
	public GameLoadScreen() {
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.BLACK);
		g2.clearRect(0, 0, GlobalConfiguration.SCREEN_WIDTH, GlobalConfiguration.SCREEN_HEIGHT);
		g2.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.PLAIN, 20);
		FontMetrics metrics = g2.getFontMetrics(font);
		Rectangle2D rect = metrics.getStringBounds("RhythmCrusher is now loading", g2);
		g2.setFont(font);
		g2.drawString("RhythmCrusher is now loading",
					  GlobalConfiguration.SCREEN_WIDTH / 2 - (int)rect.getWidth() / 2,
					  GlobalConfiguration.SCREEN_HEIGHT / 2 - (int)rect.getHeight() / 2);
	}

	@Override
	public CopyOnWriteArrayList<IRenderable> getSortedRenderableObject() {
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
