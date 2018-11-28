package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class test {
	
	public static void main(String[] args) {
		
		ArrayList<IHolder> arraylist = new ArrayList<>();
		
		arraylist.add(new IHolder("name", "VARCHAR"));
		arraylist.add(new IHolder("phone", "VARCHAR"));
		arraylist.add(new IHolder("id", "INT"));
		
		Command c = new CreateDatabase( "PoliceStation" );
		Command c1 = new DropDatabase( "PoliceStation" );
		Command c2 = new CreateTable("NewTable", "PoliceStation", arraylist);
		Command c3 = new DropTable("NewTable", "PoliceStation");
		
		c3.execute();
		
	}
}
