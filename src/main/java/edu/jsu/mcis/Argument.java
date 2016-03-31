package edu.jsu.mcis;
import java.util.*;

public class Argument {
	public enum DataType{INT, FLOAT, BOOL, STRING};

    protected String name;
    protected String value;
    protected DataType type;
	protected String description;
    protected String XMLData;
   
    
    public Argument(String n, String d, DataType t){
        name = n;
        type = t;
		description = d;
		value = "";
         XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + t + "</type>\n" + "<positionalArgument>\n"; */
    }
    
    
    public void addValue(String v){
        value = v;
    }
    
    public String getName(){
        return name;
    }
    
    public String getValue(){
        return value;
    }
    
    
    public DataType getType(){
        return type;
    }
    public String getDescription(){
		return description;
	}
    
    public String getXMLDataForArg(){
        return XMLData; 
    }
    
}