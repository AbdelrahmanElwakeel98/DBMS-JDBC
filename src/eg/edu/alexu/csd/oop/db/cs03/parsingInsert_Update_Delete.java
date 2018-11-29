package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsingInsert_Update_Delete {

	private String query ;
	private String order ;
	private String tableName;
	private String conditionColumn;
	private String conditionSign;
	private String conditionValue;
	private ArrayList<IHolder> tableDetails;
	ArrayList<String> value ;
	ArrayList<String> value1 ;
	//private ArrayList<String> fieldNames;
	//private ArrayList<String> valuesArgs;
	public parsingInsert_Update_Delete(String query) {
		this.query = query;
		tableName = null;
		conditionColumn =null;
		conditionSign =null;
		conditionValue =null;
		tableDetails = new ArrayList<>();
		value = new ArrayList<>();
		value1 = new ArrayList<>();
		//fieldNames = new ArrayList<>();
		//valuesArgs = new ArrayList<>();
	}
	
	public boolean validity (String query) {
		if(check1()||check2()||check3()) {
			return true;
		}else {
		  return false;
		} 
	}
	
	public boolean check1()	{
	      String pattern = "^(?i)(DELETE FROM)+(\\s)+(\\w*+)\\s*+(WHERE)+\\s+(\\w*+)((?:[<|>|=]))(\\d|(')(\\w*+)('))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "delete";
	    	  tableName = m.group(3);            
	  		  conditionColumn = m.group(5);
	  		  conditionSign = m.group(6);
	  		  conditionValue = m.group(7);
	    	  
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	public boolean check2()	{
		 String pattern = "^\\s*+(?i)(UPDATE)+\\s*+(\\w*+)\\s*+(SET)+\\s*+((\\w*+)\\s*+(=)+\\s*+(\\d|(')+(\\w*+)('))+\\s*+(,)\\s*)*+"
		  	     	+ "(\\w*+)\\s*+(=)+\\s*+(\\d|(')+(\\w*+)('))+\\s*+(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))+\\s*+(\\d|(')(\\w*+)('))\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  String[] attr = this.query.trim().split("SET");
	    	  String[] attr3 = attr[1].trim().split("WHERE");
	    	  String[] attr1 = attr3[0].trim().split(","); 
	    	  String withoutS1 = null ;
	    	  String withoutS2 = null ;
	    	  for (int i = 0; i < attr1.length; i++) {
	    		  String[] attr2 = attr1[i].trim().split("=");
	    		  withoutS1=attr2[0].replaceAll(" ", "");
	    		  withoutS2=attr2[1].replaceAll(" ", "");
				tableDetails.add(new IHolder(withoutS1, withoutS2));
				  
	      }
	      
	       
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	public boolean check3()	{
		 String pattern = "^\\s*+(?i)(INSERT)\\s*+(INTO)+(\\s*)+(\\w*+)\\s*(\\()+\\s*+((\\w*+)\\s*(,)\\s*)*+(\\w*+)\\s*(\\))\\s*+"
			  		+ "(?i)(VALUES)\\s*+(\\()\\s*+((\\d|(')+"
			  		+ "(\\w*+)('))+\\s*(,)\\s*)*+(\\d|((')+(\\w*+)(')))+\\s*+(\\))\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "insert";
	    	  tableName = m.group(3);
	    	  String[] attr = this.query.trim().split("\\(");
	    	  String[] attr3 = attr[1].trim().split("\\)");
	    	  String[] attr1 = attr3[0].trim().split(",");
	    	  String[] attr4 = attr3[0].trim().split(",");
	    	  String withoutS1 = null ;
	    	  for (int i = 0; i < attr1.length; i++) { 
	    		  withoutS1=attr1[i].replaceAll(" ", "");
				  value.add(withoutS1);
	    	  }
	    	  attr = this.query.trim().split("VALUES");
	    	  attr4 = attr[1].trim().split("\\(");
	    	  attr3 = attr4[1].trim().split("\\)");
	    	  attr1 = attr3[0].trim().split(",");
	    	  for (int i = 0; i < attr1.length; i++) { 
	    		  withoutS1=attr1[i].replaceAll(" ", "");
	    		  value1.add(withoutS1);
	    	  }

	          for(int i=0 ; i<attr1.length; i++) {
	        	  tableDetails.add(new IHolder(value.get(i), value1.get(i)));
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
	
	
	public String getTableName() {
		return tableName;
	}
	
	public String getConditionColumn() {
		return conditionColumn;
	}
	
	public String getConditionSign() {
		return conditionSign;
	}
	
	public String getConditionValue() {
		return conditionValue;
	}
	
	public ArrayList<IHolder> getTableDetails(){
		return tableDetails;
	}
	
}
