package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of named argument objects.
 *</p>
 *<p>
 * this is an example of how to use this class 
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
 *	p.setProgramName("VolumeCalculator");
 *	p.addArg("length", Argument.DataType.FLOAT);
 *	p.addArg("width", Argument.DataType.FLOAT);
 *	p.addArg("height", Argument.DataType.FLOAT);
 *	p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
 *	p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
 *	p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
 *	p.parse(s); 
 *</pre>
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class NamedArgument extends Argument{
    protected String shortName;
    protected boolean required;
    
     /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param v Argument value
	 *@param t Argument data Type identifier
	 *@param shortName shortname of this argument	 
	 */
     
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
    
     /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param v Argument value
	 *@param t Argument data Type identifier
	 *@param shortName shortname of this argument
     *@param required boolean for if named argument is required
	 */
    
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
    
     /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param v Argument value
	 *@param t Argument data Type identifier
	 *@param shortName shortname of this argument
     *@param restricted list of restricted values for the named argument
	 */

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

     /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param v Argument value
	 *@param t Argument data Type identifier
	 *@param shortName shortname of this argument
     *@param restricted list of restricted values for the named argument
     *@param required boolean for if named argument is required
	 */    
    
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

	 /**
	 *This method sets a specified value to an argument object
	 *
	 *@param v Value of this argument
	 */     
    
    public void setValue(String v){
        addValue(v);
    }
    
	 /**
	 *This method returns the shortname value for this argument object
	 *
	 *@return this.shortName Shortname of this argument
	 */
    
    public String getShortName(){
        return this.shortName;
    }
    
	 /**
	 *This method returns the XML value for this argument object.
	 *
	 *@return XMLData String of XML data representing this argument
	 */    
    
    public String getXMLDataForOptArg(){
       return XMLData; 
        
    }
}