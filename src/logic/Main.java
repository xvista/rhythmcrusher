package logic;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.embed.swing.JFXPanel;
import exception.SimfileException;
//import javafx.embed.swing.JFXPanel;
import ui.GameManager;
import utility.AudioUtility;

public class Main {

	public static void main(String[] args) throws InterruptedException, URISyntaxException, SimfileException, IOException {
		new JFXPanel();
		MainLogic logic = new MainLogic();
		GameManager.runGame(logic);
		//AudioUtility.playMusic();
		//Simfile s = new Simfile("Infection");
		//System.out.println(s.getTitle());
		//System.out.println(s.getSubtitle());
		//System.out.println(s.getArtist());
	}

}
