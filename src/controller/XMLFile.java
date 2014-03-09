package controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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















import model.Landscape;
import model.Playfield;
import model.City;
import model.Resource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFile {

	public Playfield readXmlFile(Playfield playf){

		Landscape[][] map=new Landscape[12][8];

		try {
			//File fXmlFile = new File("http://localhost/java/map.xml");


	        //URL oracle = new URL("http://localhost/java/map.xml");
	        InputStream fXmlFile = new URL("http://localhost/java/map.xml").openStream();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			/*
			 * START LOOKING FOR RESOURCES
			 */
			NodeList nList = doc.getElementsByTagName("Row");			
			System.out.println("Found rows: "+nList.getLength());

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
						String chip=e2Element.getElementsByTagName("Chip").item(0).getTextContent();

						Resource res = Resource.valueOf(type);
						boolean isChip=Boolean.parseBoolean(chip);
						
						Point indexes=new Point(entry,row);
						
						playf.lands[entry][row]=new Landscape(playf,res,Integer.parseInt(value),indexes,isChip);
					}
				}
			} //End of main for-loop resources

			/*
			 * START LOOKING FOR POSITIONS
			 */
			NodeList cityList = doc.getElementsByTagName("City");			
			System.out.println("Found Cities: "+cityList.getLength());

			for (int row = 0; row < cityList.getLength(); row++) {

				Node cityNode = cityList.item(row);


				
					if (cityNode.getNodeType() == Node.ELEMENT_NODE) {

						Element e2Element = (Element) cityNode;
						String name=e2Element.getElementsByTagName("Name").item(0).getTextContent();
						String posX=e2Element.getElementsByTagName("PosX").item(0).getTextContent();
						String posY=e2Element.getElementsByTagName("PosY").item(0).getTextContent();
						String gold=e2Element.getElementsByTagName("Gold").item(0).getTextContent();
						String starter=e2Element.getElementsByTagName("Start").item(0).getTextContent();

						int intX = Integer.parseInt(posX);
						int intY = Integer.parseInt(posY);
						int intGold = Integer.parseInt(gold);
						boolean start = Boolean.parseBoolean(starter);

						playf.cities[intX][intY]=new City(playf,new Point(intX,intY), intGold,start,name);
					}
				
			} //End of main for-loop resources





		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileWrite: File not found. Load empty ArrayList");
		} catch (ParserConfigurationException|IOException|SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FileWrite: Something wrong with the parsing of XML file");
		}
		return playf;
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