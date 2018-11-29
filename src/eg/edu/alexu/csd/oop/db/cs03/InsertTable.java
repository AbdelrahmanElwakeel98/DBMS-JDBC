package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eg.edu.alexu.csd.oop.db.Command;


public class InsertTable implements Command {

	private String databaseName;
	private String tableName;
	private ArrayList<IHolder> arrayOfinsert = new ArrayList<>();

	public InsertTable(String databaseName, String tableName, ArrayList<IHolder> arrayOfinsert) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.arrayOfinsert = arrayOfinsert;
	}

	@Override
	public Object execute() {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("table");
			doc.appendChild(rootElement);
			// where j is number of columns

			Element row = doc.createElement("row");
			rootElement.appendChild(row);
			for (int j = 0; j < arrayOfinsert.size(); j++) {
				row.appendChild(Add_column(arrayOfinsert.get(j).getFieldNames(),arrayOfinsert.get(j).getDataTypes(), doc));
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("DataBases/" + this.databaseName + "/" + this.tableName + ".xml"));
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;

	}

	public static Element Add_column(String col_name, String col_data, Document doc) {
		Element col = doc.createElement(col_name);
		col.appendChild(doc.createTextNode(col_data));

		return col;
	}

	

}
