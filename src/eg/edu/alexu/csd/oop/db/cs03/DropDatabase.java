package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import eg.edu.alexu.csd.oop.db.Command;

public class DropDatabase implements Command {
	
	private String databaseName;
	private String path = "DataBases/";
	private File dir;
	
	public DropDatabase (String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public void execute() {
		dir = new File(path + databaseName);
		String[] entries = dir.list();
		
		if (dir.exists()) {
			for (String s : entries) {
				File currentFile = new File(dir.getPath(), s);
				currentFile.delete();
			}
			dir.delete();
		}
		
	}

}
