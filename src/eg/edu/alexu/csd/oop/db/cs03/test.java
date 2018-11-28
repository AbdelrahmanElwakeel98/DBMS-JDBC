package eg.edu.alexu.csd.oop.db.cs03;

import eg.edu.alexu.csd.oop.db.Command;

public class test {
	
	public static void main(String[] args) {
		
		Command c = new CreateDatabase( "PoliceStation" );
		Command c1 = new DropDatabase( "PoliceStation" );
		Command c2 = new CreateTable("NewTable", "PoliceStation");
		Command c3 = new DropTable("NewTable", "PoliceStation");
		
		c3.execute();
		
	}
}
