package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.CommandUpdate;

public class InsertTable implements CommandUpdate {
	
	
	private String databaseName;
	private String tableName;
	private ArrayList<IHolder> arrayOfinsert = new ArrayList<>();
	
	public InsertTable (String databaseName, String tableName, ArrayList<IHolder> arrayOfinsert){
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.arrayOfinsert = arrayOfinsert;
	}

	@Override
	public void execute() {
		
	}

	@Override
	public int getNumRow() {
		// TODO Auto-generated method stub
		return 0;
	}

}
