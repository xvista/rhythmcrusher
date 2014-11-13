package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

import exception.SimfileException;
import exception.NoSimfileFolderException;
import exception.SimfileCountException;

public class Simfile {
	
	private File simfile = null;
	
	private String title = "Unknown";
	private String artist = "Unknown Artist";
	
	public Simfile(String simfileFolder) throws URISyntaxException, SimfileException, IOException {
		URL folder = Simfile.class.getClassLoader().getResource("simfiles/" + simfileFolder);
		if (folder == null) {
		     throw new NoSimfileFolderException("Simfile folder '" + simfileFolder + "' not found");
		}
	    File dir = new File(folder.toURI());
	    for (File nextFile : dir.listFiles()) {
	    	if (FilenameUtils.getExtension(nextFile.getPath()).equals("sm")) {
	    		if (simfile != null) {
	    			throw new SimfileCountException("This simfile folder must contains exactly 1 simfile");
	    		}
	    		simfile = nextFile;
	    	}
	    }
	    if (simfile == null) {
	    	throw new SimfileCountException("This simfile folder must contains exactly 1 simfile");
	    }
	    BufferedReader br = new BufferedReader(new FileReader(simfile));
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();
	    while (line != null) {
	    	line = line.trim();
	    	if (line.indexOf("#TITLE:") == 0) {
	    		title = line.substring("#TITLE:".length(), line.length() - 1).trim();
	    	} else if (line.indexOf("#ARTIST:") == 0) {
	    		artist = line.substring("#ARTIST:".length(), line.length() - 1).trim();
	    	} 
	    	//System.out.println(line);
	    	line = br.readLine();
	    }
	    br.close();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
}
