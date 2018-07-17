package com.tblf.DotCP;

import java.io.File;
import java.net.URI;

public class DotCPParserBuilder {

	private DotCPParser dotCPParser;
	
	/**
	 * Instanciate the builder
	 */
	public DotCPParserBuilder() {
		dotCPParser = new DotCPParser();
	}
	
	/**
	 * Set the M2 repository using a {@link URI}
	 * @param uri the {@link URI}
	 * @return the current builder
	 */
	public DotCPParserBuilder withM2URI(URI uri) {
		File file = new File(uri);
		if (file.exists() && file.isDirectory()) {
			dotCPParser.setM2Repository(file);
		}
		return this;
	}

	/**
	 * Set the M2 repository using a {@link File}
	 * @param file the {@link File}
	 * @return the current builder
	 */
	public DotCPParserBuilder withM2Directory(File file) {
		if (file.exists() && file.isDirectory()) {
			dotCPParser.setM2Repository(file);
		}
		return this;
	}
	
	/**
	 * Return the built {@link DotCPParser}
	 * @return
	 */
	public DotCPParser create() {
		return dotCPParser;
	}
}
