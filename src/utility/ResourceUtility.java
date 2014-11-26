package utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.im.InputSubset;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import config.GlobalConfiguration;
import logic.Simfile;
import ui.RenderObject;

public class ResourceUtility {
	
	// Fonts
	
	public static Font FONT_HELVETICA_ROUND = getFontFromResource("HelveticaRoundedLTStd-Bd.otf");
	
	// Simfiles
	
	public static int SIMFILES_COUNT;
	public static Simfile[] SIMFILES;
	
	// Game Title
	
	public static final RenderObject BACKGROUND = new RenderObject(DrawingUtility.getImageFromResource("background-sprite.jpg"), 2, 75, 4, true);

	public static final RenderObject MENU_START = new RenderObject(DrawingUtility.getImageFromResource("menu-start.png"));
	public static final RenderObject MENU_START_SELECTED = new RenderObject(DrawingUtility.getImageFromResource("menu-start-selected.png"));
	
	public static final RenderObject MENU_OPTIONS = new RenderObject(DrawingUtility.getImageFromResource("menu-options.png"));
	public static final RenderObject MENU_OPTIONS_SELECTED = new RenderObject(DrawingUtility.getImageFromResource("menu-options-selected.png"));

	public static final RenderObject MENU_EXIT = new RenderObject(DrawingUtility.getImageFromResource("menu-exit.png"));
	public static final RenderObject MENU_EXIT_SELECTED = new RenderObject(DrawingUtility.getImageFromResource("menu-exit-selected.png"));

	public static final RenderObject SCREEN_SELECTOR_BACKGROUND = new RenderObject(DrawingUtility.getImageFromResource("play-selector-background.png"));
	public static final RenderObject SCREEN_SELECTOR_TITLE = new RenderObject(DrawingUtility.getImageFromResource("play-selector-title.png"));
	public static final RenderObject SCREEN_SELECTOR_KEY = new RenderObject(DrawingUtility.getImageFromResource("play-selector-key.png"));
	
	public static final RenderObject SCREEN_SELECTOR_DIFFICULTY_BEGINNER = new RenderObject(DrawingUtility.getImageFromResource("play-selector-difficulty-beginner.png"));
	public static final RenderObject SCREEN_SELECTOR_DIFFICULTY_EASY = new RenderObject(DrawingUtility.getImageFromResource("play-selector-difficulty-easy.png"));
	public static final RenderObject SCREEN_SELECTOR_DIFFICULTY_MEDIUM = new RenderObject(DrawingUtility.getImageFromResource("play-selector-difficulty-medium.png"));
	public static final RenderObject SCREEN_SELECTOR_DIFFICULTY_HARD = new RenderObject(DrawingUtility.getImageFromResource("play-selector-difficulty-hard.png"));
	public static final RenderObject SCREEN_SELECTOR_DIFFICULTY_CHALLENGE = new RenderObject(DrawingUtility.getImageFromResource("play-selector-difficulty-challenge.png"));
	
	public static Font getFontFromResource(String file) {
		Font font = null;
		try {
			File fontFile = new File(ResourceUtility.class.getClassLoader().getResource(GlobalConfiguration.URL_RESOURCE_FONTS_DIR + file).getFile());
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (Exception e) {
			e.printStackTrace();
			font = new Font("sans-serif", Font.PLAIN, 24);
		}
		return font;
	}
	
}
