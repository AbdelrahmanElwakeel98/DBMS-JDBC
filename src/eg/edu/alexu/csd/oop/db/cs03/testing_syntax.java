package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

public class testing_syntax {

	public static void main(String[] args) {
		String line = "   SELeCT    column1   ,   column2   ,    column3   ,   column4     FroM     table_name1     " ;		  
		   String pattern = "^\\s*(?i)(SELECT)\\s*+((\\w*+)\\s*+(,)+\\s*)*+(\\w*+)\\s*+(?i)(FROM)\\s*+(\\w*+)\\s*+((WHERE)+\\s*+"
		   		           + "(\\w*+)\\s*+((?:[<|>|=]))\\s*+(\\d)+\\s*)*$";
		      // Create a Pattern object
		      Pattern r = Pattern.compile(pattern);
		      // Now create matcher object.
		      Matcher m = r.matcher(line);
		      if (m.find()) {
		    	  ArrayList<String> value = new ArrayList<>();
		    	  String[] attr = line.trim().split("(?i)SELECT");
		    	  String[] attr3 = attr[1].trim().split("(?i)FROM");
		    	  String[] attr1 = attr3[0].trim().split(",");
		    	  String withoutS1 = null ;
		    	  for (int i = 0; i < attr1.length; i++) { 
		    		  withoutS1=attr1[i].replaceAll(" ", "");
					  value.add(withoutS1);
		    	  }
               if(m.group(8)!=null) {
  	  		    String conditionColumn = m.group(9);
  	  		 String conditionSign = m.group(10);
  	  		String conditionValue = m.group(11);
  	  	System.out.println(conditionColumn);
  	    	  }
               System.out.println(value.get(3));
               
		      }	 
		
	      
} 
	
   void create_database() {
	      String line = "Create dataBASE jk_f8j" ;		  
	      String pattern = "^(?i)(CREATE)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }	
   }

   void drop_database() {
	      String line = "drop dataBASE jk_f8j" ;		  
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }	
   }
   void drop_table() {
	   String line = "drop TABLE jk_f8j" ;		  
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(table)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }	
   }
   
   void create_table() {
	   String linem = "Create TABLE table_name1(column_name1 varchar, "
	   		      + "column_name2 int, column_name3 varchar)" ;		  
	      String patternm = "^(?i)(CREATE)+(\\s)+(?i)(table)+(\\s)+(\\w*+)(\\()+"
	      		+ "((\\w*+)\\s+(int|varchar)+(,)\\s)*+"
	      		+ "((\\w*+)\\s+(int|varchar))+(\\))$";
	      // Create a Pattern object
	      Pattern rm = Pattern.compile(patternm);
	      // Now create matcher object.
	      Matcher mm = rm.matcher(linem);
	      if (mm.find()) {
	      System.out.println(mm.group(1));
	      }	  
	      String line = "Create TABLE table_name1(column_name1 varchar)" ;		  
	      String pattern = "^(?i)\\s*(CREATE)\\s(TABLE)\\s(\\w+)\\s*+"
	      		+ "(\\()\\s*(\\w+\\s+(varchar|int)\\s*(,)\\s*)*(\\w+\\s+(varchar|int)\\s*)"
	      		+ "(\\))\\s*(;)?\\s*$";  
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	  ArrayList<String> fieldNames = new ArrayList< String>(),dataTypes = new ArrayList< String>();
	    	  String[] spliter;
	    	  String helper ;
			  String[] columns = line.split(",");
	          helper = null;
	          spliter = new String[columns.length];
	          for (int i = 0; i < columns.length; i++) {
	        	  String[] attr = columns[i].trim().split("\\s+");
	              if(i==0&&i!=columns.length-1) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  fieldNames.add(spliter[1].trim()) ;
		              dataTypes.add(attr[3].trim());  
	              }
	              else if(i==columns.length-1&&i!=0) {
	            	  helper = attr[1].trim();
	            	  spliter=helper.trim().split("\\)");
	            	  dataTypes.add(spliter[0].trim());
	            	  fieldNames.add(attr[0].trim());   
	              }
	              else if(i==0 && i==columns.length-1 ) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  fieldNames.add(spliter[1].trim());
	            	  helper = attr[3].trim();
	            	  spliter=helper.trim().split("\\)");
	            	  dataTypes.add(spliter[0].trim());
	            	  
	              }
	              else {
	                  fieldNames.add(attr[0].trim());
	                  dataTypes.add(attr[1].trim());
	              }
	          }
	      System.out.println(dataTypes.get(0));
	      }	
   }
   
   void select() {
	   String line = "SELECT * FROM table_name1 WHERE coluMN_NAME2 > 4" ;		  
	   String pattern = "^(?i)(SELECT)\\s+(\\*)\\s+(FROM)\\s+(\\w*+)\\s+(WHERE)+\\s+"
	   		           + "(\\w*+)\\s+(?:[<|>|=])\\s+(\\d)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(0));

	      }	 
   }
   
   void delete() {
	   String line = "DELETE From table_name1  WHERE coLUmn_NAME2=4" ;		  
		  String pattern = "^(?i)(DELETE FROM)+(\\s)+(\\w*+)\\s*+(WHERE)+\\s+(\\w*+)(?:[<|>|=])(\\d)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
   }
   
   }
  
   void insert() {
	      String line = "INSERT INTO table_name1(column_NAME1, COLUMN_name3, "
	   		        + "column_name2) VALUES ('value1', 'value3', 4) " ;		  
		  String pattern = "^(?i)(INSERT INTO)+(\\s)+(\\w*+)(\\()+((\\w*+)(,)\\s)*+(\\w*+)(\\))\\s+"
		  		+ "(?i)(VALUES)\\s+(\\()+((\\d|(')+"
		  		+ "(\\w*+)('))+(,)\\s)*+(\\d|((')+(\\w*+)(')))+(\\))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
   }
   
}
   
   
   void update() {
	   String line = "UPDATE table_name1 SET column_name1='11111111' WHERE coLUmn_NAME2=8" ;		  
	   String pattern = "^(?i)(UPDATE)\\s+(\\w*+)\\s+(SET)+\\s+((\\w*+)(=)+(\\d|(')+(\\w*+)('))+(,)\\s)*+"
		  	     	+ "(\\w*+)(=)+(\\d|(')+(\\w*+)('))\\s*+(WHERE)\\s+(\\w*+)((?:[<|>|=]))(\\d|(')(\\w*+)('))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	  ArrayList<String> fieldNames = new ArrayList<>();
	    	  ArrayList<String> valuesArgs = new ArrayList<>();
	    	  String[] spliter;
	    	  String helper ;
	          String[] attr = line.split(",");
	          helper = null;
	          spliter = new String[attr.length];
	          for (int i = 0; i < attr.length; i ++) {
	              String[] attributes = attr[i].split("=");
	              if(i==0 && i!=attr.length-1) {
	            	  helper = attributes[0].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\s");
	            	  fieldNames.add(spliter[3].trim()) ; 
		              valuesArgs.add(attributes[1].trim());
	              }
	              else if(i==attr.length-1 && i!=0) {
	            	  fieldNames.add(attributes[0].trim().toLowerCase());
	            	  helper = attributes[1].trim();
	            	  spliter=helper.trim().split("\\s"); 
		              valuesArgs.add(spliter[0].trim());
	              }
	              else if(i==attr.length-1 && i==0) {
	            	  helper = attributes[0].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\s");
	            	  fieldNames.add(spliter[3].trim()) ;
	            	  helper = attributes[1].trim();
	            	  spliter=helper.trim().split("\\s"); 
		              valuesArgs.add(spliter[0].trim());
	              }
	              else {
	            	  fieldNames.add(attributes[0].trim().toLowerCase());
		              valuesArgs.add(attributes[1].trim());
	              }
	          }
	      
	          System.out.println(fieldNames.get(0));
	      }	
	
   }
}