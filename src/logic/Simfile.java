package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;

import config.GlobalConfiguration;
import exception.SimfileException;
import exception.NoSimfileFolderException;
import exception.SimfileCountException;
import exception.SimfileParsingException;

public class Simfile {
	
	//private File simfile = null;
	private InputStream simfile = null;
	private String folderPath = null;
	
	private String title = "Unknown";
	private String subtitle = null;
	private String artist = "Unknown Artist";
	private File banner = null;
	private File background = null;
	private File music = null;
	private double offset = 0;
	private double sampleStart = 0;
	private double sampleLength = 16;
	private double[][] bpms;
	private String[] notes = new String[5];
	private int[] difficulty = {0, 0, 0, 0, 0};
	
	public Simfile(String simfileFolder) throws SimfileException, IOException {
		/*folderPath = GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR + simfileFolder + "/";
		URL folder = Simfile.class.getClassLoader().getResource(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR + simfileFolder);
		if (folder == null) {
		     throw new NoSimfileFolderException("Simfile folder '" + simfileFolder + "' not found");
		}*/
		
		CodeSource src = ResourceLoader.class.getProtectionDomain().getCodeSource();
		ArrayList<String> fileList = new ArrayList<String>();
		try {
			if (src != null) {
				URL jar = src.getLocation();
				System.out.println(jar);
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				ZipEntry ze = null;
				
				while ((ze = zip.getNextEntry()) != null) {
					if (ze.getName().indexOf(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR + simfileFolder) == 0) {
						//System.out.println(ze.getName());
						if (!fileList.contains(ze.getName().split("/")[2])) {
							fileList.add(ze.getName().split("/")[2]);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (fileList.size() == 0) {
			throw new NoSimfileFolderException("Simfile folder '" + simfileFolder + "' not found");
		}
		
	    /*File dir = null;
		try {
			dir = new File(folder.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    File[] fileList = dir.listFiles();*/
	    for (String nextFile : fileList) {
	    	if (FilenameUtils.getExtension(nextFile).equals("sm")) {
	    		if (simfile != null) {
	    			throw new SimfileCountException("Simfile folder '" + simfileFolder + "' must contains exactly 1 simfile");
	    		}
	    		//simfile = nextFile;
	    		simfile = Simfile.class.getClassLoader().getResourceAsStream(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR + simfileFolder)
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

		int currentNote = GlobalConfiguration.SIMFILE_BEGINNER;
		boolean readNote = false;
		boolean readNoteType = false;
		boolean readDifficulity = false;
		boolean readNoteStream = false;
	    
	    // Simfile parser
	    while (line != null) {
	    	if (line.indexOf("//") != -1) {
	    		line = line.substring(0, line.indexOf("//"));
	    	}
	    	line = line.trim();

	    	if (line.equals("")) {
	    		line = br.readLine();
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
		    	} else if (line.indexOf("#SAMPLESTART:") == 0) {
		    		readValue = line.substring("#SAMPLESTART:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			sampleStart = Double.parseDouble(readValue);
		    		}
		    	} else if (line.indexOf("#SAMPLELENGTH:") == 0) {
		    		readValue = line.substring("#SAMPLELENGTH:".length(), line.length() - 1).trim();
		    		if (!readValue.equals("")) {
		    			sampleLength = Double.parseDouble(readValue);
		    		}
		    	} else if (line.indexOf("#BPMS:") == 0) {
		    		readValue = line.substring("#BPMS:".length(), line.length() - 1).trim();
		    		if (readValue.equals("")) {
		    			throw new SimfileParsingException("BPMs data does not specified");
		    		} else {
		    			String[] bpmData = readValue.split(",");
		    			bpms = new double[bpmData.length][2];
		    			int i = 0;
		    			for (String eachBPMData : bpmData) {
		    				String[] bpmSplit = eachBPMData.split("=");
		    				bpms[i][0] = Double.parseDouble(bpmSplit[0]);
		    				bpms[i][1] = Double.parseDouble(bpmSplit[1]);
		    				i++;
		    			}
		    		}
		    	}
	    	} else {
	    		readValue = line;
	    		if (readNoteStream && !readValue.equals("#NOTES:")) {
	    			notes[currentNote] += readValue;
	    		}
	    		if (readNoteType) {
		    		if (!readValue.equals("dance-single:")) {
		    			readNote = false;
		    		}
	    			readNoteType = false;
	    		}
	    		//System.out.println(readValue);
	    		if (readValue.equals("#NOTES:")) {
	    			readNote = true;
	    			readNoteType = true;
	    			readNoteStream = false;
	    		}
	    		if (!readNote) {
	    			line = br.readLine();
	    			continue;
	    		}
	    		if (readDifficulity) {
	    			difficulty[currentNote] = Integer.parseInt(readValue.replace(":", ""));
	    			readDifficulity = false;
	    			System.out.println(currentNote + " " + difficulty[currentNote]);
	    			br.readLine();
	    			readNoteStream = true;
	    			notes[currentNote] = "";
	    		}
	    		if (readValue.equals("Beginner:")) {
	    			currentNote = GlobalConfiguration.SIMFILE_BEGINNER;
	    			readDifficulity = true;
	    		} else if (readValue.equals("Easy:")) {
	    			currentNote = GlobalConfiguration.SIMFILE_EASY;
	    			readDifficulity = true;
	    		} else if (readValue.equals("Medium:")) {
	    			currentNote = GlobalConfiguration.SIMFILE_MEDIUM;
	    			readDifficulity = true;
	    		} else if (readValue.equals("Hard:")) {
	    			currentNote = GlobalConfiguration.SIMFILE_HARD;
	    			readDifficulity = true;
	    		} else if (readValue.equals("Challenge:")) {
	    			currentNote = GlobalConfiguration.SIMFILE_CHALLENGE;
	    			readDifficulity = true;
	    		}
	    	}
	    	
	    	line = br.readLine();
	    }
	    br.close();
	    for (int i = 0; i < 5; i++) {
	    	System.out.println(notes[i]);
	    }
	}
	
	public String getFolderPath() {
		return folderPath;
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
	
	public File getBanner() {
		return banner;
	}
	
	public int[] getDifficulty() {
		return difficulty;
	}
	
}
