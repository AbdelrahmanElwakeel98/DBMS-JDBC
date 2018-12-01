package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainDB {

	public static void main(String[] args) {
		System.out.println("DataBase Managment System : ");
		System.out.println("---------------------------");
		System.out.println("Enter a query :");
		Scanner sca = new Scanner(System.in);
		String query = sca.nextLine();
		List<String> univ = new ArrayList<String>();
		while(!(query.equals("exit"))) {
			System.out.println("Enter another query :");
			    univ.add(query);
	            sca = new Scanner(System.in);
	            query = sca.nextLine();
	          }

	}

}
