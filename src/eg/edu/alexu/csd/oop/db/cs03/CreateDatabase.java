package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import eg.edu.alexu.csd.oop.db.Command;

public class CreateDatabase implements Command {
	
	private String databaseName;
	private String path = "DataBases/";
	private File dir;
	
	public CreateDatabase (String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public void execute() {
		dir = new File (path);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		dir = new File (path + this.databaseName);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
}
