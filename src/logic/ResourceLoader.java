package logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import utility.ResourceUtility;
import config.GlobalConfiguration;
import exception.SimfileException;

public class ResourceLoader implements Runnable {

	@Override
	public void run() {
		// read simfiles
		System.out.println("Thread is running");
		URL folder = Simfile.class.getClassLoader().getResource(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR);
		//InputStream f = Simfile.class.getClassLoader().getResourceAsStream(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR);
		//URL ff = f.
		if (folder == null) {
			System.out.println("folder is null");
		}
		
		/*File dir;
		try {
			//dir = new File(folder.toURI());
			//System.out.println(folder.getFile());
			dir = new File(folder.getFile());
		    File[] fileList = dir.listFiles();
		    ResourceUtility.SIMFILES_COUNT = fileList.length;
		    ResourceUtility.SIMFILES = new Simfile[ResourceUtility.SIMFILES_COUNT];
		    System.out.println("Reading simfiles directory");
		    
		    int i = 0;
		    for (File nextFile : fileList) {
		    	try {
		    		System.out.println("Create simfile " + nextFile.getName());
					ResourceUtility.SIMFILES[i++] = new Simfile(nextFile.getName());
				} catch (SimfileException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Simfile exception");
				}
		    }
		    System.out.println("Finishing creating file");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File reading exception");
		}*/
		
		CodeSource src = ResourceLoader.class.getProtectionDomain().getCodeSource();
		ArrayList<String> simfileDir = new ArrayList<String>();
		try {
			if (src != null) {
				URL jar = src.getLocation();
				System.out.println(jar);
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				ZipEntry ze = null;
				
				while ((ze = zip.getNextEntry()) != null) {
					if (ze.getName().indexOf(GlobalConfiguration.URL_RESOURCE_SIMFILES_DIR) == 0) {
						//System.out.println(ze.getName());
						if (!simfileDir.contains(ze.getName().split("/")[1])) {
							simfileDir.add(ze.getName().split("/")[1]);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    ResourceUtility.SIMFILES_COUNT = simfileDir.size();
	    ResourceUtility.SIMFILES = new Simfile[ResourceUtility.SIMFILES_COUNT];
	    
	    int i = 0;
	    for (String dir : simfileDir) {
	    	try {
	    		System.out.println("Create simfile " + dir);
				ResourceUtility.SIMFILES[i++] = new Simfile(dir);
			} catch (SimfileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Simfile exception");
			}
	    }
	    System.out.println("Finishing creating file");
		
		
		System.out.println("Thread done");
	}

}
