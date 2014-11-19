package utility;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.GlobalConfiguration;

public class DrawingUtility {
	
	private static BufferedImage getImageFromResource(String directory) {
		try {
			return ImageIO.read(DrawingUtility.class.getClassLoader().getResource(GlobalConfiguration.URL_RESOURCE_IAMGES_DIR + directory));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static final BufferedImage BACKGROUND = getImageFromResource("background-sprite.jpg");
	
}
