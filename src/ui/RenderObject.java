package ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import logic.IRenderable;

public class RenderObject implements IRenderable {
	
	private BufferedImage image = null;
	private int frameCount, frameDelay, frameRow, frameColumn;
	private int currentFrame, frameDelayCount;
	private int x, y, z, width, height;
	private int frameWidth, frameHeight;
	private boolean visible = false, playing = false, loop = false;
	
	public RenderObject(BufferedImage image) {
		this(image, 1, 1, 1, false);
		this.visible = true;
	}
	
	public RenderObject(BufferedImage image, int frameDelay, int frameRow, int frameColumn, boolean loop) {
		this.image = image;
		this.x = 0;
		this.y = 0;
		this.frameRow = frameRow;
		this.frameColumn = frameColumn;
		this.frameCount = frameRow * frameColumn;
		this.frameDelay = frameDelay;
		this.loop = loop;
		if (this.image != null) {
			this.frameWidth = image.getWidth();
			this.frameHeight = image.getHeight();
			this.width = this.frameWidth / frameColumn;
			this.height = this.frameHeight / frameRow;
		} else {
			this.frameWidth = 0;
			this.frameHeight = 0;
			this.width = 0;
			this.height = 0;
		}
		this.frameDelayCount = 0;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public int getFrameRow() {
		return frameRow;
	}

	public void setFrameRow(int frameRow) {
		this.frameRow = frameRow;
	}

	public int getFrameColumn() {
		return frameColumn;
	}

	public void setFrameColumn(int frameColumn) {
		this.frameColumn = frameColumn;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getFrameDelayCount() {
		return frameDelayCount;
	}

	public void setFrameDelayCount(int frameDelayCount) {
		this.frameDelayCount = frameDelayCount;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isLoop() {
		return loop;
	}
	
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	protected void topLeftAnimationAt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected void centerAnimationAt(int x, int y) {
		this.x = x - (width / 2);
		this.y = y - (height / 2);
	}
	
	public void play() {
		currentFrame = 0;
		playing = true;
		visible = true;
	}
	
	public void stop() {
		currentFrame = 0;
		playing = false;
		//visible = false;
	}
	
	public void updateAnimation() {
		if (playing) {
			if (frameDelayCount > 0) {
				frameDelayCount--;
			} else {
				currentFrame++;
				frameDelayCount = frameDelay;
			}
			if (currentFrame == frameCount) {
				if (!loop) {
					stop();
				} else {
					currentFrame = 0;
				}
			}
		}
	}
	
	public void render(Graphics2D g2) {
		if (visible && image != null) {
			BufferedImage imageFrame = image.getSubimage((currentFrame % frameColumn) * width, (currentFrame / frameColumn) * height, width, height);
			g2.drawImage(imageFrame, null, x, y);
		}
	}
	
	public void scale(int width, int height) {
		image = (BufferedImage)(image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
	}
	
}
