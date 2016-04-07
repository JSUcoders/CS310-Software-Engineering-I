package edu.jsu.mcis;
import java.util.*;

public class OptionalArgument extends Argument{
    protected String shortName;
    protected boolean required;
    public OptionalArgument(String n, String d, String v, DataType t, String shortName){
        super(n,d,t);
		addValue(v);
        required = false;
        this.shortName = shortName;
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
                            
    }
    
    public void setValue(String v){
        addValue(v);
    }
    
    public void setRequired(){
        required = true;
        updateXMLDataForOArg();
    }
    
    private void updateXMLDataForOArg(){
        XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
        
    }
    
    public String getShortName(){
        return this.shortName;
    }
    
    public String getXMLDataForOptArg(){
       return XMLData; 
        
    }
}