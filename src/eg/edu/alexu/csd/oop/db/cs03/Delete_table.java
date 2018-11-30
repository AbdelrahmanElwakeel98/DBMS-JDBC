package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import eg.edu.alexu.csd.oop.db.Command;

public class Delete_table  implements Command{
	
	private String databaseName;
	private String tableName;
	private String col_name;
	private String sign;
	private String value;
	private Object[][] data;
	private Dtdreader dtdreader;
	private ArrayList<String> col_names;
	private int col_num;
	private int count_delete;
	
	public Delete_table(String databaseName, String tableName,Object[][] data,String col_name,String sign ,String value) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.data = data;
		this.col_name = col_name;
		this.sign = sign;
		this.value = value;
	}
	@Override
	public Object execute() {
		dtdreader = new Dtdreader(databaseName, tableName);
		col_names = dtdreader.read();
		Boolean[][] flag = new Boolean [data.length][data[0].length];
		for(int i = 0; i <col_names.size();i++) {
			if(col_names.get(i).equals(col_name)) {
				col_num = i;
			}
		}
		if(value.charAt(0) == '\'' && sign.equals("=")) {
			
			for(int i = 0;i < data.length;i++) {
				for(int j = 0; j <data[0].length;j++) {
					if(j == col_num) {
						if(value.equals((String)data[i][j])){
							count_delete++;
							for(int k =0 ; k < data[0].length;k++) {
								flag[i][k] = true;
							}
						}
					}
				}
			}
			
		}
		else if(value.charAt(0) != '\'') {
			for(int i = 0;i < data.length;i++) {
				for(int j = 0; j <data[0].length;j++) {
					if(j == col_num) {
						switch(sign) {
						case "<":
							if((int)data[i][j] < Integer.parseInt(value)) {
								count_delete++;
								for(int k =0 ; k < data[0].length;k++) {
									flag[i][k] = true;
								}
							}break;
						case ">":
							if((int)data[i][j] > Integer.parseInt(value)) {
								count_delete++;
								for(int k =0 ; k < data[0].length;k++) {
									flag[i][k] = true;
								}
							}break;
						case "=":
							if((int)data[i][j] == Integer.parseInt(value)) {
								count_delete++;
								for(int k =0 ; k < data[0].length;k++) {
									flag[i][k] = true;
								}
							}break;
						
						}
					}
				}
			}
		}
		return null;
	}

}
