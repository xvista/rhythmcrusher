package config;

public class GlobalConfiguration {
	public static final String GAME_NAME = "Rhythm Crusher";
	
	public static final int SCREEN_WIDTH = 854;
	public static final int SCREEN_HEIGHT = 480;
	public static int countScreen = 1;
	public static boolean key = false;
	public static int count = 1;
	
	public static int[][] playerKey = new int[2][4];
	
	public static final String URL_BGM = "music/Infection.mp3";
	public static final String URL_RESOURCE_SIMFILES_DIR = "simfiles/";
	public static final String URL_RESOURCE_IAMGES_DIR = "images/";
}
