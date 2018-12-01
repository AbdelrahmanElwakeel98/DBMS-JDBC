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
		Command c1 = new DropDatabase( "PoliceStation" );
		Command c2 = new CreateTable("NewTable", "PoliceStation", arraylist);
		Command c3 = new DropTable("NewTable", "PoliceStation");
		
		
		
		Object[][] o = new Object[3][3];
		
		o[0][0] = "'a'";
		o[0][1] = "'b'";
		o[0][2] = "'c'";
		
		o[1][0] = "'1'";
		o[1][1] = "'2'";
		o[1][2] = "'3'";
		
		o[2][0] = "'11'";
		o[2][1] = "'22'";
		o[2][2] = "'c'";
		
		Object[] namesOfCols = new Object[3];
		
		namesOfCols[0] = "col1";
		namesOfCols[1] = "col2";
		namesOfCols[2] = "col3";
		
		ArrayList<String> namesOfCol = new ArrayList<>();

		namesOfCol.add("col1");
		namesOfCol.add("col2");
		namesOfCol.add("col3");
		
		ArrayList<String> selectedCols = new ArrayList<>();
		
		selectedCols.add("col3");
		selectedCols.add("col2");
		
		Command c5 = new SelectTableConditional(o, namesOfCols, selectedCols, "col3", "=", "'c'", namesOfCol);
		
		Object[][] o1 = (Object[][]) c5.execute();
		
	//	System.out.println(o1[0][0]);
		
		
		for (int i = 0; i < o1.length; i ++) {
			for (int j = 0; j <o1[0].length; j++) {
				System.out.println(o1[i][j]);
			}
		}
		
		//CommandUpdate c4 = new InsertTable("PoliceStation", "NewTable", arraylist1);

		//c1.execute();
		
		//Database db = new IDatabase();
		
		//db.executeStructureQuery("CREATE DATABASE      TestDB ");
		
		
		
	//	db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");

	}
}
