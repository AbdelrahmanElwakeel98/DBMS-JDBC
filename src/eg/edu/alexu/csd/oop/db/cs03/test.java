package eg.edu.alexu.csd.oop.db.cs03;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.Database;


public class test {

	public static void main(String[] args) throws SQLException {

		ArrayList<IHolder> arraylist = new ArrayList<>();
		ArrayList<IHolder> arraylist1 = new ArrayList<>();

		arraylist.add(new IHolder("name", "VARCHAR"));
		arraylist.add(new IHolder("phone", "VARCHAR"));
		arraylist.add(new IHolder("id", "INT"));

		arraylist1.add(new IHolder("name", "'ahmed'"));
		arraylist1.add(new IHolder("phone", "'0111'"));
		arraylist1.add(new IHolder("id", "4"));

		Command c = new CreateDatabase( "PoliceStation");
		Command c1 = new DropDatabase( "sample" +  System.getProperty("file.separator") + "testdb" );
		Command c2 = new CreateTable("NewTable", "PoliceStation", arraylist);
		Command c3 = new DropTable("NewTable", "PoliceStation");
		
		
		
		Object[][] o = new Object[3][3];
		
		o[0][0] = "'1'";
		o[0][1] = "'2'";
		o[0][2] = "'a'";
		
		o[1][0] = "'3'";
		o[1][1] = "'4'";
		o[1][2] = "'b'";
		
		o[2][0] = "'5'";
		o[2][1] = "'6'";
		o[2][2] = "'c'";
		
		
		
		ArrayList<String> namesOfCol = new ArrayList<>();

		namesOfCol.add("col1");
		namesOfCol.add("col2");
		namesOfCol.add("col3");
		
		ArrayList<String> selectedCols = new ArrayList<>();
		
		selectedCols.add("col3");
		selectedCols.add("col2");
		
		Command c5 = new SelectTableFrom(o, namesOfCol, "col3", ">", "'a'", namesOfCol);
		
		Object[][] o1 = (Object[][]) c5.execute();
		
	//	System.out.println(o1[0][0]);
		
		
		for (int i = 0; i < o1.length; i ++) {
			for (int j = 0; j <o1[0].length; j++) {
				System.out.println(o1[i][j]);
			}
		}
		
		//CommandUpdate c4 = new InsertTable("PoliceStation", "NewTable", arraylist1);

		c1.execute();
		
		Database db = new IDatabase();
		
		db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");
		
			
		
	//	db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");

	}
}
