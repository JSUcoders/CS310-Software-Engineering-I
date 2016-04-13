package edu.jsu.mcis;
import java.util.*;

public class NamedArgument extends Argument{
    protected String shortName;
    protected boolean required;
    public NamedArgument(String n, String d, String v, DataType t, String shortName){
        super(n,d,t);
		addValue(v);
        required = false;
        this.shortName = shortName;
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
                            
    }
    
    public NamedArgument(String n, String d, String v, DataType t, String shortName, boolean required){
        super(n,d,t);
		addValue(v);
        this.required = required;
        this.shortName = shortName;
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
                            
    }

    public NamedArgument(String n, String d, String v, DataType t, String shortName, List<String> restricted){
        super(n,d,t);
		addValue(v);
        required = false;
        this.shortName = shortName;
        restrictedValues = new ArrayList<String>(restricted);
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
                            
    }    
    
    public NamedArgument(String n, String d, String v, DataType t, String shortName, List<String> restricted, boolean required){
        super(n,d,t);
		addValue(v);
        this.required = required;
        this.shortName = shortName;
        restrictedValues = new ArrayList<String>(restricted);
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "<required>" + Boolean.toString(required) + "</required>\n" + 
						 "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</optionalArgument>\n";
                            
    }    
    
    public void setValue(String v){
        addValue(v);
    }
    

    
    public String getShortName(){
        return this.shortName;
    }
    
    public String getXMLDataForOptArg(){
       return XMLData; 
        
    }
}