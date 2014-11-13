package utility;

public class InputUtility {
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];
	
	public static boolean getKeyPressed(int key) {
		if (key < 0 || key > 255) {
			return false;
		}
		return keyPressed[key];
	}
	public static void setKeyPressed(int key, boolean pressed) {
		if (key < 0 || key > 255) {
			return;
		}
		keyPressed[key] = pressed;
	}
	
	public static boolean getKeyTriggered(int key) {
		if (key < 0 || key > 255) {
			return false;
		}
		return keyTriggered[key];
	}
	public static void setKeyTriggered(int key, boolean pressed) {
		if (key < 0 || key > 255) {
			return;
		}
		keyTriggered[key] = pressed;
	}
}
