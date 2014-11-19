package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

import config.GlobalConfiguration;

import exception.SimfileException;
import exception.NoSimfileFolderException;
import exception.SimfileCountException;
import exception.SimfileParsingException;

public class Simfile {
	
	private File simfile = null;
	
	private String title = "Unknown";
	private String subtitle = null;
	private String artist = "Unknown Artist";
	private File banner = null;
	private File background = null;
	private File music = null;
	private double offset = 0;
	
	public Simfile(String simfileFolder) throws URISyntaxException, SimfileException, IOException {
		URL folder = Simfile.class.getClassLoader().getResource(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR + simfileFolder);
		if (folder == null) {
		     throw new NoSimfileFolderException("Simfile folder '" + simfileFolder + "' not found");
		}
		
	    File dir = new File(folder.toURI());
	    File[] fileList = dir.listFiles();
	    for (File nextFile : fileList) {
	    	if (FilenameUtils.getExtension(nextFile.getPath()).equals("sm")) {
	    		if (simfile != null) {
	    			throw new SimfileCountException("Simfile folder '" + simfileFolder + "' must contains exactly 1 simfile");
	    		}
	    		simfile = nextFile;
	    	}
	    }
	    
	    if (simfile == null) {
			throw new SimfileCountException("Simfile folder '" + simfileFolder + "' must contains exactly 1 simfile");
	    }
	    
	    BufferedReader br = new BufferedReader(new FileReader(simfile));
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();
	    String readValue = null;
	    boolean isNotePath = false;
	    
	    // Simfile parser
	    while (line != null) {
	    	if (line.indexOf("//") != -1) {
	    		line = line.substring(0, line.indexOf("//"));
	    	}	    	
	    	line = line.trim();
	    	
	    	if (line.equals("")) {
	    		continue;
	    	}
	    	if (line.indexOf("#NOTES:") == 0) {
	    		isNotePath = true;
	    	}
	    	
	    	if (!isNotePath) {
	    		if (line.indexOf(";") != line.length() - 1) {
	    			throw new SimfileParsingException("Illegal simfile format");
	    		}
		    	if (line.indexOf("#TITLE:") == 0) {
		    		readValue = line.substring("#TITLE:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			title = readValue;
		    		}
		    	} else if (line.indexOf("#SUBTITLE:") == 0) {
		    		readValue = line.substring("#SUBTITLE:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			subtitle = readValue;
		    		}
		    	} else if (line.indexOf("#ARTIST:") == 0) {
		    		readValue = line.substring("#ARTIST:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			artist = readValue;
		    		}
		    	} else if (line.indexOf("#BANNER:") == 0) {
		    		readValue = line.substring("#BANNER:".length(), line.length() - 1).trim();
		    		for (File eachFile : fileList) {
		    	    	if (eachFile.getName().equals(readValue)) {
		    	    		banner = eachFile;
		    	    		break;
		    	    	}
		    	    }
		    		if (banner == null && !readValue.equals("")) {
		    			throw new SimfileParsingException("Not found '" + readValue + "' in simfile directory");
		    		}
		    	} else if (line.indexOf("#BACKGROUND:") == 0) {
		    		readValue = line.substring("#BACKGROUND:".length(), line.length() - 1).trim();
		    		for (File eachFile : fileList) {
		    	    	if (eachFile.getName().equals(readValue)) {
		    	    		background = eachFile;
		    	    		break;
		    	    	}
		    	    }
		    		if (background == null && !readValue.equals("")) {
		    			throw new SimfileParsingException("Not found '" + readValue + "' in simfile directory");
		    		}
		    	} else if (line.indexOf("#MUSIC:") == 0) {
		    		readValue = line.substring("#MUSIC:".length(), line.length() - 1).trim();
		    		if (readValue.equals("")) {
		    			throw new SimfileParsingException("Music property does not specified");
		    		}
		    		for (File eachFile : fileList) {
		    	    	if (eachFile.getName().equals(readValue)) {
		    	    		music = eachFile;
		    	    		break;
		    	    	}
		    	    }
		    		if (music == null) {
		    			throw new SimfileParsingException("Not found '" + readValue + "' in simfile directory");
		    		}
		    	} else if (line.indexOf("#OFFSET:") == 0) {
		    		readValue = line.substring("#OFFSET:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			offset = Double.parseDouble(readValue);
		    		}
		    	}
	    	} else {
	    		// Note path
	    	}
	    	
	    	line = br.readLine();
	    }
	    br.close();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getSubtitle() {
		return subtitle;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public File getMusic() {
		return music;
	}
	
	public double getOffset() {
		return offset;
	}
	
}
