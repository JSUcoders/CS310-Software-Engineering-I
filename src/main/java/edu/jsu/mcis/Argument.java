package edu.jsu.mcis;
import java.util.*;

public class Argument {
	public enum DataType{INT, FLOAT, BOOL, STRING};

    protected String name;
    protected String value;
    protected DataType type;
	protected String description;
	protected List<String> restrictedValues;
    protected String XMLData;
    protected int position;
   
    
    public Argument(String n, String d, DataType t){
        name = n;
        type = t;
		description = d;
		value = "";
		restrictedValues = new ArrayList<String>();
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + t + "</type>\n" + "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" +  "</positionalArgument>\n"; 
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
    
    public void setPosition(int index){
        position = index;
        updateXMLDataForPArg();
    }
    
    private void updateXMLDataForPArg(){
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + type + "</type>\n" + "<position>" + position + "</position>\n" +
						  "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</positionalArgument>\n"; 
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