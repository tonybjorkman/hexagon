package io;

import hexagon.Landscape;
import hexagon.Playfield;
import hexagon.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;




import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFile {

	public Landscape[][] readXmlFile(Playfield p){
		
		Landscape[][] map=new Landscape[12][8];

		try {
			File fXmlFile = new File("map.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			//Make a list of each "Row" child
			NodeList nList = doc.getElementsByTagName("Row");



			System.out.println("Found rows: "+nList.getLength());

			//

			for (int row = 0; row < nList.getLength(); row++) {
				
				

				Node nNode = nList.item(row);

				NodeList entryList = ((Element)nNode).getElementsByTagName("Entry");
				System.out.println("Found entries: "+entryList.getLength());

				for (int entry = 0; entry < entryList.getLength(); entry++) {

					Node entryNode = entryList.item(entry);


					System.out.println("\nCurrent Element :" + entryNode.getNodeName());

					if (entryNode.getNodeType() == Node.ELEMENT_NODE) {

						Element e2Element = (Element) entryNode;
						String name=e2Element.getElementsByTagName("Name").item(0).getTextContent();
						String type=e2Element.getElementsByTagName("Type").item(0).getTextContent();
						String value=e2Element.getElementsByTagName("Value").item(0).getTextContent();

						
						
						Resource res = Resource.valueOf(type);
						if(res==Resource.SALT){
							System.out.println("We are SALT-kings!");
						}
						
						System.out.print("Name " + name);
						System.out.print("Value " + value);
						System.out.println("Type " + type);
						
						map[entry][row]=new Landscape(p,res,Integer.parseInt(value));
						
					}

					//**create the new Landscape here
				}
			}
			//return returnList;

		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileWrite: File not found. Load empty ArrayList");
		} catch (ParserConfigurationException|IOException|SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FileWrite: Something wrong with the parsing of XML file");
		}
		return map;
		//return returnList;
	}

	/*public void writeXmlFile(ArrayList<TaskItem> list) {

		try {

			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();

			Element root = doc.createElement("Root");
			doc.appendChild(root);




			for(int i=0; i<list.size(); i++ ) {
				Element entry = doc.createElement("Entry");
				root.appendChild(entry);

				Element name = doc.createElement("Name");
				name.appendChild(doc.createTextNode(list.get(i).getDescription()));
				entry.appendChild(name);
				//Save date
				Element id = doc.createElement("Date");
				id.appendChild(doc.createTextNode(list.get(i).getDate()));
				entry.appendChild(id);
				//Save category
				Element cat = doc.createElement("Cat");
				cat.appendChild(doc.createTextNode(list.get(i).getCategory()));
				entry.appendChild(cat);

				//Save priority
				Element prio = doc.createElement("Prio");
				prio.appendChild(doc.createTextNode(list.get(i).getPriority()));
				entry.appendChild(prio);

				//save progressSlider Value. convert from int to string
				Element prog = doc.createElement("Progress");
				prog.appendChild(doc.createTextNode(""+list.get(i).getProgress()));
				entry.appendChild(prog);

				Element done = doc.createElement("Done");
				done.appendChild(doc.createTextNode(""+list.get(i).getDone()));
				entry.appendChild(done);
			}


			// Save the document to the disk file
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();

			// format the XML nicely
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

			aTransformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");



			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fos = new FileWriter("database.xml");
				StreamResult result = new StreamResult(fos);
				aTransformer.transform(source, result);

			} catch (IOException e) {

				e.printStackTrace();
			}



		} catch (TransformerException ex) {
			System.out.println("Error outputting document");

		} catch (ParserConfigurationException ex) {
			System.out.println("Error building document");
		}
	}*/
}