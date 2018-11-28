package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import eg.edu.alexu.csd.oop.db.Command;

public class DropTable implements Command {
	
	private String tableName;
	private String databaseName;
	private File dir;
	private String path = "DataBases/";

	public DropTable(String tableName, String databaseName) {
		this.tableName = tableName;
		this.databaseName = databaseName;
	}
	
	@Override
	public void execute() {
		dir = new File (path + databaseName);
		
		if (dir.exists()) {
			File file1 = new File (path + databaseName + "/" + tableName + ".xml");
			File file2 = new File (path + databaseName + "/" + tableName + ".DTD"); 
			
			if (file1.exists() && file2.exists()) {
				file1.delete();
				file2.delete();
			}
		}
	}

}
