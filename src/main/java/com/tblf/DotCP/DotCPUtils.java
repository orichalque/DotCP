package com.tblf.DotCP;

import java.io.File;

public class DotCPUtils {

	/**
	 * Get the standard .m2/repository/ location, depending on the OS and user name
	 * Only correct for Windows and Linux right now. No tests has been done on Mac, or Solaris distributions
	 * @return
	 */
	public static File getStandardMavenLocation() {
		String mvnRepoStr;
		
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			//Windows
			mvnRepoStr = String.format("%s%s%s", "C:\\Users\\", System.getProperty("user.name"),  "\\.m2\\repository");	
		} else {
			mvnRepoStr = "~/.m2/repository";
		}
		
		return new File(mvnRepoStr);
		
	}

}
