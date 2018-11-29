package eg.edu.alexu.csd.oop.db.cs03;

import java.sql.SQLException;
import java.util.ArrayList;


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

		Command c = new CreateDatabase( "sample" + System.getProperty("file.separator") + "ppp" );
		Command c1 = new DropDatabase( "PoliceStation" );
		Command c2 = new CreateTable("NewTable", "PoliceStation", arraylist);
		Command c3 = new DropTable("NewTable", "PoliceStation");
		//CommandUpdate c4 = new InsertTable("PoliceStation", "NewTable", arraylist1);

		c.execute();
		
		Database db = new IDatabase();
		
		db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");

	}
}
