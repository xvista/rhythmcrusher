package logic;

import java.io.IOException;
import java.net.URISyntaxException;

import exception.SimfileException;
import javafx.embed.swing.JFXPanel;
import ui.GameManager;
import utility.AudioUtility;

public class Main {

	public static void main(String[] args) throws InterruptedException, URISyntaxException, SimfileException, IOException {
		new JFXPanel();
		GameManager.runGame();
		//AudioUtility.playMusic();
		Simfile s = new Simfile("Infection");
		System.out.println(s.getTitle());
		System.out.println(s.getArtist());
	}

}
