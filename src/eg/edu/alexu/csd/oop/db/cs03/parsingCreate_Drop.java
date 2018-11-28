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
	
	public boolean validity (String query) {
		if(check1()||check2()||check3()||check4()) {
			return true;
		}else {
		  return false;
		} 
	}
	
    public boolean check1()	{
	      String pattern = "^(?i)(CREATE)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
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
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
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
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(table)+(\\s)+(\\w*+)$";
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
	      String pattern = "^(?i)\\s*(CREATE)\\s(TABLE)\\s(\\w+)\\s*+"
	      		+ "(\\()\\s*(\\w+\\s+(varchar|int)\\s*(,)\\s*)*(\\w+\\s+(varchar|int)\\s*)"
	      		+ "(\\))\\s*(;)?\\s*$";  
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "create";
	    	  executeOn = "table";
	    	  name = m.group(3);	    	  
	    	  String[] spliter,spliterr;
	    	  String helper ,helperr ;
			  String[] columns = this.query.split(",");
	          helper = null;
	          helperr = null;
	          spliter = new String[columns.length];
	          spliterr = new String[columns.length];
	          
	          for (int i = 0; i < columns.length; i++) {
	        	  String[] attr = columns[i].trim().split("\\s+");
	              if(i==0&&i!=columns.length-1) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  tableDetails.add(new IHolder(spliter[1].trim(), attr[3].trim()));
	            	//  fieldNames.add(spliter[1].trim()) ;
		            //  dataTypes.add(attr[3].trim());  
	              }
	              else if(i==columns.length-1&&i!=0) {
	            	  helper = attr[1].trim();
	            	  spliter=helper.trim().split("\\)");
	            	  tableDetails.add(new IHolder(attr[0].trim(), spliter[0].trim()));
	            	  //dataTypes.add(spliter[0].trim());
	            	  //fieldNames.add(attr[0].trim());   
	              }
	              else if(i==0 && i==columns.length-1 ) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  //fieldNames.add(spliter[1].trim());
	            	  helperr = attr[3].trim();
	            	  spliterr=helperr.trim().split("\\)");
	            	  //dataTypes.add(spliterr[0].trim());
	            	  tableDetails.add(new IHolder(spliter[1].trim(), spliterr[0].trim()));
	            	  
	              }
	              else {
	            	  tableDetails.add(new IHolder(attr[0].trim(), attr[1].trim()));
	                  //fieldNames.add(attr[0].trim());
	                  //dataTypes.add(attr[1].trim());
	              }
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
