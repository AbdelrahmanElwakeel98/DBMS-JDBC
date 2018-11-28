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
		 String pattern = "^(?i)(UPDATE)\\s+(\\w*+)\\s+(SET)+\\s+((\\w*+)(=)+(\\d|(')+(\\w*+)('))+(,)\\s)*+"
		  	     	+ "(\\w*+)(=)+(\\d|(')+(\\w*+)('))\\s*+(WHERE)\\s+(\\w*+)((?:[<|>|=]))(\\d|(')(\\w*+)('))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "update";
	    	  tableName = m.group(2);
	  		  conditionColumn = m.group(19);
	  		  conditionSign = m.group(20);
	  		  conditionValue = m.group(21);
	    	  String[] spliter,spliterr;
	    	  String helper,helperr ;
	          String[] attr = this.query.split(",");
	          helper = null;
	          spliter = new String[attr.length];
	          helperr = null;
	          spliterr = new String[attr.length];
	          for (int i = 0; i < attr.length; i ++) {
	              String[] attributes = attr[i].split("=");
	              if(i==0 && i!=attr.length-1) {
	            	  helper = attributes[0].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\s");
	            	  tableDetails.add(new IHolder(spliter[3].trim(),attributes[1].trim()));
	            	  //fieldNames.add(spliter[3].trim()) ; 
		              //valuesArgs.add(attributes[1].trim());
	              }
	              else if(i==attr.length-1 && i!=0) {
	            	  //fieldNames.add(attributes[0].trim().toLowerCase());
	            	  helper = attributes[1].trim();
	            	  spliter=helper.trim().split("\\s"); 
		              //valuesArgs.add(spliter[0].trim());
		              tableDetails.add(new IHolder(attributes[0].trim().toLowerCase(),spliter[0].trim()));
	              }
	              else if(i==attr.length-1 && i==0) {
	            	  helper = attributes[0].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\s");
	            	  //fieldNames.add(spliter[3].trim()) ;
	            	  helperr = attributes[1].trim();
	            	  spliterr=helperr.trim().split("\\s"); 
		              //valuesArgs.add(spliterr[0].trim());
		              tableDetails.add(new IHolder(spliter[3].trim(),spliterr[0].trim()));
	              }
	              else {
	            	  tableDetails.add(new IHolder(attributes[0].trim().toLowerCase(),attributes[1].trim()));
	            	  //fieldNames.add(attributes[0].trim().toLowerCase());
		              //valuesArgs.add(attributes[1].trim());
	              }
	          }
	       
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	public boolean check3()	{
	      String pattern = "^(?i)\\s*(INSERT)\\s+(INTO)\\s+"
	      		+ "(\\w*+)\\s*(\\()\\s*((\\w*+)\\s*(,)\\s*)*\\w+\\s*(\\))\\s+"
	      		+ "(VALUES)\\s*(\\()+((\\d|(')+(\\w*+)('))+(,)\\s)*+(\\d|((')+(\\w*+)(')))+(\\))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "insert";
	    	  tableName = m.group(3);
	    	  String[] spliter;
	    	  String helper;
	    	  helper = null;
	          String[] fields = this.query.trim().split(",");
	          spliter = new String[fields.length];
	          for (int i = 0; i < fields.length; i++) {
	        	  if(i==0) {
	        		  helper = fields[0].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\(");
	        		  value.add(spliter[1].trim().toLowerCase()); 
	        	  }
	        	  else if(i==(fields.length-1)/2) {
	        		  helper = fields[2].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\)");
	        		  value.add(spliter[0].trim().toLowerCase());
	        		  helper = fields[2].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\(");
	        		  value1.add(spliter[1].trim().toLowerCase()); 
	        	  }
	        	  else if(i==fields.length-1) {
	        		  helper = fields[fields.length-1].trim().toLowerCase();
	            	  spliter=helper.trim().split("\\)");
	        		  value1.add(spliter[0].trim().toLowerCase());
	        	  }
	        	  else if(i>0&&i<(fields.length-1)/2) {
	        		  value.add(fields[i].trim().toLowerCase());
	        	  }
	        	  else if(i>(fields.length-1)/2&&i<fields.length-1){
	        		  value1.add(fields[i].trim().toLowerCase());
	        	  }
	        	 
	          }
	          for(int i=0 ; i<(fields.length-1)/2 ; i++) {
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
