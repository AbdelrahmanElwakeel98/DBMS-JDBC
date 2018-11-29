package eg.edu.alexu.csd.oop.db.cs03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Read_table {

	public static void main(String[] args) {
		File file = new File("file.dtd");
		BufferedReader br;
		int count = 0;
		ArrayList<String> col_names = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				if (count > 1) {

					String pattern = "^<!ELEMENT+\\s+(\\w*+)\\s+(\\()+(#PCDATA)+(\\))+(\\>)$";
					// Create a Pattern object
					Pattern r = Pattern.compile(pattern);
					// Now create matcher object.
					Matcher m = r.matcher(st);
					if (m.find()) {
						col_names.add(m.group(1));
					}
				}
				count++;

			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File inputFile = new File("cars.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("row");
			System.out.println("----------------------------");
			Object data[][] = new Object[nList.getLength()][col_names.size()];

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					for (int i = 0; i < col_names.size(); i++) {
						data[temp][i] = eElement.getElementsByTagName(col_names.get(i)).item(0).getTextContent();

					}
				}
			}
			for (int i = 0; i < nList.getLength(); i++) {
				for (int j = 0; j < col_names.size(); j++) {
					System.out.println(data[i][j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
