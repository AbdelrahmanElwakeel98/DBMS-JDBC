package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class parsingCreate_Drop {

	private String query ;
	private String order ;
	private String executeOn ;
	private String name;
	private ArrayList<IHolder> tableDetails;
	//private ArrayList<String> dataTypes;
	
	public parsingCreate_Drop(String query) {
		this.query = query;
		order = null;
		executeOn = null;
		name = null;
		tableDetails = new ArrayList<>();
	}
	
	public boolean validity () {
		if(check1()||check2()||check3()||check4()) {
			return true;
		}else {
		  return false;
		} 
	}
	
    public boolean check1()	{
	      String pattern = "^(?i)(CREATE)+(\\s*)+(?i)(DATABASE)+(\\s*)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "create"; 
	    	  executeOn = "database";
	    	  name = m.group(5);
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
   public boolean check2()	{		  
	      String pattern = "^(?i)(DROP)+(\\s*)+(?i)(DATABASE)+(\\s*)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "drop";
	    	  executeOn = "database";
	    	  name = m.group(5);
	    	  
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
   public boolean check3()	{
	      String pattern = "^(?i)(DROP)+(\\s*)+(?i)(table)+(\\s*)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "drop";
	    	  executeOn = "table";
	    	  name = m.group(5);
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
   
   public boolean check4()	{
	      String pattern ="^(?i)\\s*(CREATE)\\s*(TABLE)\\s*(\\w*+)\\s*+"
		      		+ "(\\()\\s*(\\w+\\s*+(varchar|int)\\s*(,)\\s*)*(\\w+\\s*+(varchar|int)\\s*)"
		      		+ "(\\))\\s*(;)?\\s*$"; 
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "create";
	    	  executeOn = "table";
	    	  name = m.group(3);	    	  
	    	  String[] attr = this.query.trim().split("\\(");
	    	  String[] attr3 = attr[1].trim().split("\\)");
	    	  String[] attr1 = attr3[0].trim().split(","); 
	    	  
	    	  for (int i = 0; i < attr1.length; i++) {
	    		  String[] attr2 = attr1[i].trim().split("\\s+");
	    		  
				tableDetails.add(new IHolder(attr2[0], attr2[1]));
				  System.out.println(attr2[1]);
	    	  }
	      
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	      
	}
   
    public String getWhichOrder() {
		return order;
	}
	
	public String getDatabaseOrTable() {
		return executeOn;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<IHolder> getTableDetails(){
		return tableDetails;
	}
	
	
   
}
