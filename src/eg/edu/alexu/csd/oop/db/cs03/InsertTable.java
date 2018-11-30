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
	private Object[][] data;
	private Dtdreader dtdreader;
	private ArrayList<String> col_names;

	public InsertTable(String databaseName, String tableName, ArrayList<IHolder> arrayOfinsert, Object[][] data) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.arrayOfinsert = arrayOfinsert;
		this.data = data;
	}

	@Override
	public Object execute() {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		dtdreader = new Dtdreader(databaseName, tableName);
		col_names = dtdreader.read();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement(tableName);
			doc.appendChild(rootElement);
			// where j is number of columns

			Element row = doc.createElement("row");
			rootElement.appendChild(row);
			if (data == null) {
				for (int j = 0; j < arrayOfinsert.size(); j++) {
					row.appendChild(Add_column(col_names.get(j), arrayOfinsert.get(j).getDataTypes(), doc));
				}
			} else {
				for (int i = 0; i < data.length + 1; i++) {
					for (int j = 0; j < arrayOfinsert.size(); j++) {
						if (i == data.length) {
							row.appendChild(Add_column(col_names.get(j), arrayOfinsert.get(j).getDataTypes(), doc));
						} else {
							row.appendChild(Add_column(col_names.get(j), (String) data[i][j], doc));
						}
					}
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(
					new File(databaseName + System.getProperty("file.separator") + tableName + ".xml"));
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
