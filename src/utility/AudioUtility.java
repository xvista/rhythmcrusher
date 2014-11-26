package utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import config.GlobalConfiguration;

public class AudioUtility {
	
	private static MediaPlayer mp;
	
	public static void playMusic() {
		playMusic(GlobalConfiguration.URL_BGM);
	}
	
	public static void playMusic(String music) {
		URL musicURL = AudioUtility.class.getClassLoader().getResource(music);
		mp = new MediaPlayer(new Media(musicURL.toString()));
		//mp.setStartTime(new Duration(25000));
		mp.setVolume(0);
		mp.play();
		/*while (mp.getVolume() < 100) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mp.setVolume(mp.getVolume() + 10);
		}*/
	}
	
	public static MediaPlayer getMediaPlayer() {
		return mp;
	}
	
}
