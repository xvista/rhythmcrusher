package logic;

import java.awt.Graphics2D;

public interface IRenderable {
	
	public abstract boolean isVisible();
	public abstract int getZ();
	public abstract void render(Graphics2D g2);
	
}
