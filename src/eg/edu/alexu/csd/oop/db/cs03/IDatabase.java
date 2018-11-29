package eg.edu.alexu.csd.oop.db.cs03;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;

public class IDatabase implements Database {

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		parsingCreate_Drop parse = new parsingCreate_Drop(query);
		
		if (parse.validity()) {
			
			
			
		} else {
			
			throw new SQLException ("Failed to parse query with extra spaces");
			
		}
		
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
