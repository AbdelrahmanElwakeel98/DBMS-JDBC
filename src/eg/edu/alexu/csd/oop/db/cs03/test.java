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
		//CommandUpdate c4 = new InsertTable("PoliceStation", "NewTable", arraylist1);

		//c1.execute();
		
		//Database db = new IDatabase();
		
		//db.executeStructureQuery("CREATE DATABASE      TestDB ");
		
		String line = "CREATE DATABASE" + " " + "sample\\TestDB";
		
		System.out.println(System.getProperty("file.separator"));
		
		String f = System.getProperty("file.separator");
		
		String pattern = "^(?i)(CREATE)+(\\s*)+(?i)(DATABASE)+(\\s*)+((\\w+)|(\\w+([\\" + f + "]\\w+)+))\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	  System.out.println(m.group(5)); 
	      }	
		
	//	db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");

	}
}
