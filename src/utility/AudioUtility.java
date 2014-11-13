package utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import config.GlobalConfiguration;

public class AudioUtility {
	
	public static void playMusic() throws InterruptedException {
		URL musicURL = AudioUtility.class.getClassLoader().getResource(GlobalConfiguration.URL_BGM);
		MediaPlayer mp = new MediaPlayer(new Media(musicURL.toString()));
		mp.setStartTime(new Duration(25000));
		mp.setVolume(0);
		mp.play();
		while (mp.getVolume() < 100) {
			Thread.sleep(1000);
			mp.setVolume(mp.getVolume() + 10);
		}
	}
	
}
