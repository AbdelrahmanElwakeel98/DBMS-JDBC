package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class CreateTable implements Command {
	
	private String tableName;
	private String databaseName;
	private ArrayList<String> cols;
	private String path = "DataBases/";
	private File dir;
	
	public CreateTable (String tableName, String databaseName) {
		this.tableName = tableName;
		this.databaseName = databaseName;
	}

	@Override
	public void execute() {
		dir = new File(path + databaseName);
		if (dir.exists()) {
			File file1 = new File (path + databaseName + "/" + tableName + ".xml");
			File file2 = new File (path + databaseName + "/" + tableName + ".DTD"); 
			if (!file1.exists() && !file2.exists()) {
				try {
					file1.createNewFile();
					file2.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
