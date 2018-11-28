package eg.edu.alexu.csd.oop.db.cs03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsingSelect {
	
	private String query ;
	private String order ;
	private String tableName;
	private String conditionColumn;
	private String conditionSign;
	private String conditionValue;
	private int conditonExist;
	
	public parsingSelect(String query) {
		this.query = query;
		order = "select";
		tableName = null;
		conditionColumn =null;
		conditionSign =null;
		conditionValue =null;
		conditonExist=0;
	}
	
	public boolean validity (String query) {
		if(check()) {
			return true;
		}else {
		  return false;
		} 
	}
	
    public boolean check()	{
    	String pattern = "^(?i)(SELECT)\\s+(\\*)\\s+(FROM)\\s+(\\w*+)\\s+((WHERE)+\\s+"
		           + "(\\w*+)\\s+((?:[<|>|=]))\\s+(\\d|(')(\\w*+)(')))*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  tableName = m.group(4);
	    	  if(m.group(5)!=null) {
	    		 conditonExist=1;
	  		     conditionColumn = m.group(7);
	  		     conditionSign = m.group(8);
	  		     conditionValue = m.group(9);
	    	  }
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
  
   
    public String getWhichOrder() {
		return order;                     //select »œÌÂÌ« 
	}
	
	
	public String getTableName() {
		return tableName;
	}
	
	public boolean conditonExist() {
		if(conditonExist==0) {
			return false;
		}
		else {
		    return true;
		}
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
	
	
}
