package am.vector.VATReoprt.core;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Test {

	public static void main(String[] args) {
		Controller c = new Controller(true,12121);
		File XML = new File("src/am/vector/VATReoprt/core/ReceivedVAT.xml");
		c.load();
		c.readXMLFile(XML);
	

		
	}
}
