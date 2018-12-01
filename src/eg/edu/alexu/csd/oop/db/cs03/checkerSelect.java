package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.util.ArrayList;

public class checkerSelect { 
	private String databaseName;
	private String tableName;
	private ArrayList<String> selectedCols;
	private ArrayList<String> namesOfCols; 
	private ArrayList<Boolean> status;
	private String colCondition;
	
	public checkerSelect(String databaseName, String tableName,  ArrayList<String> selectedCols,
			ArrayList<String> namesOfCols) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.selectedCols = selectedCols;
		this.namesOfCols = namesOfCols;
	}
	public checkerSelect(String databaseName, String tableName,  ArrayList<String> selectedCols,
			ArrayList<String> namesOfCols,String colCondition) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.selectedCols = selectedCols;
		this.namesOfCols = namesOfCols;
		this.colCondition = colCondition;
	}

	public boolean tableIfExist() {
		File checkerXML = new File (this.databaseName + System.getProperty("file.separator") + this.tableName + ".xml");
		File checkerDTD = new File (this.databaseName + System.getProperty("file.separator") + this.tableName + ".DTD");
		
		if (checkerXML.exists() && checkerDTD.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean colOfConditionIfExist() {
			for (int j = 0; j < this.namesOfCols.size(); j++) {
				if (this.colCondition.equals(this.namesOfCols.get(j))) {
					return true;
				}
			}
		  return false;
		}
	
	
	public boolean colsIfExist() {
		for (int i = 0; i < this.selectedCols.size(); i++) {
			for (int j = 0; j < this.namesOfCols.size(); j++) {
				if (this.selectedCols.get(i).equals(this.namesOfCols.get(j))) {
					status.add(true);
				}
			}
		}
		if (status.size() == this.selectedCols.size()) {
			return true;
		} else {
			return false;
		}
		
		
		}
}
