package com.tblf.DotCP;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DotCPParser {
	private File m2repository;
	private List<File> jars;
	
	/**
	 * Constructor initializing the attributes
	 */
	public DotCPParser() {
		jars = new ArrayList<>();
	}
	
	/**
	 * Parse the .classpath file to get all the absolute URIs of the jars
	 * @param dotCPFile
	 * @return a {@link String}[] with jars 
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public List<File> parse(File dotCPFile) throws SAXException, IOException, ParserConfigurationException {
		
		if (m2repository == null) {
			m2repository = DotCPUtils.getStandardMavenLocation();
		}
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(dotCPFile);
		
		NodeList list =document.getChildNodes();
		
		list = list.item(0).getChildNodes();
		
		for (int i = 0; i < list.getLength(); ++i) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String kind = new String(element.getAttribute("kind").getBytes());
				
				switch (kind) {
					case "lib":
						//jar in the local repository. Assume that .classpath is a the root of the project where the jars are 
						File libJar = new File(dotCPFile.getAbsoluteFile().getParent()+"/"+element.getAttribute("path"));
						
						if (libJar.exists()) {
							jars.add(libJar);
						}
						
						break;
					case "var":
						//jar in the .m2 repository
						
						String path = element.getAttribute("path");
						if (path.contains("M2_REPO")) {
							path = path.replace("M2_REPO", m2repository.getAbsolutePath());
						}
						
						File mvnJar = new File(path);
						
						if (mvnJar.exists()) {
							jars.add(mvnJar);
						}
										
						break;
					case "default":
						
						break;
				}
			}			
		}
				
		return jars;
	}

	/**
	 * Set the maven repository location
	 * @param file
	 */
	protected void setM2Repository(File file) {		
		m2repository = file;		
	}
}
