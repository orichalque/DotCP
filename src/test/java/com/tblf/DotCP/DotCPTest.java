package com.tblf.DotCP;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;


/**
 * Unit test for simple App.
 */
public class DotCPTest 
{
	
	@Test
	public void checkDOTCPParse() throws SAXException, IOException, ParserConfigurationException {
		File file = new File(".classpath");
		if (file.exists()) {			
			
			List<File> jars = new DotCPParserBuilder().withM2Directory(DotCPUtils.getStandardMavenLocation()).create().parse(file);
			Assert.assertEquals(2, jars.size());
		}
	}
}
