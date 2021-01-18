import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTest {

	public static void printNode(Node n) {
		System.out.println(n.getNodeName());
		NodeList nl = n.getChildNodes();
		for(int i=0; i<nl.getLength(); i++) {
			Node t = nl.item(i);
			if(t.getNodeType() == Node.ELEMENT_NODE) {
				if(t.getChildNodes().getLength()>1) {
					printNode(t);
				}else {
					System.out.println(t.getNodeName()+" : "+t.getTextContent());
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		File file = new File("game.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		String root = doc.getDocumentElement().getNodeName();
		NodeList nl = doc.getElementsByTagName(root);
		printNode(nl.item(0));
	}

}
