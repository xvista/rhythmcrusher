package config;

public class GlobalConfiguration {
	public static final String GAME_NAME = "RhythmCrusher";
	
	public static final int SCREEN_WIDTH = 853;
	public static final int SCREEN_HEIGHT = 480;
	public static final int SCREEN_WIDTH_CENTER = SCREEN_WIDTH / 2;
	public static final int SCREEN_HEIGHT_CENTER = SCREEN_HEIGHT / 2;
	
	public static final int REFRESH_DELAY = 10;
	
	public static int[] playerKey = new int[4];
	
	public static final String URL_BGM = "musics/title-loop.mp3";
	public static final String URL_RESOURCE_SIMFILES_DIR = "simfiles/";
	public static final String URL_RESOURCE_IMAGES_DIR = "images/";
	public static final String URL_RESOURCE_FONTS_DIR = "fonts/";
	
	public static final int SIMFILE_BEGINNER = 0;
	public static final int SIMFILE_EASY = 1;
	public static final int SIMFILE_MEDIUM = 2;
	public static final int SIMFILE_HARD = 3;
	public static final int SIMFILE_CHALLENGE = 4;
}
