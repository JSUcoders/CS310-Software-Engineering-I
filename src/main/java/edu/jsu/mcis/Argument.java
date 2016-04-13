package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the user to create an object that will hold all data pertaining to an argument.
 *</p>
 *<p>
 * this is an example of how to use this class 
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
 *	p.setProgramName("VolumeCalculator");
 *	p.setProgramDescription("Calculate the volume of a box.");
 *	String[] s = {"7", "--help","3","2"};
 *	p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *	p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
 *	p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
 *	p.parse(s);
 *</pre>
 
 
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class Argument {
	public enum DataType{INT, FLOAT, BOOL, STRING};

    protected String name;
    protected String value;
    protected DataType type;
	protected String description;
	protected List<String> restrictedValues;
    protected String XMLData;
    protected int position;
   
    /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param t Argument data Type identifier 
	 */
    
    public Argument(String n, String d, DataType t){
        name = n;
        type = t;
		description = d;
		value = "";
		restrictedValues = new ArrayList<String>();
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + t + "</type>\n" + "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" +  "</positionalArgument>\n"; 
    }
    
        /**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param t Argument data Type identifier
     *@param restricted list of restricted values   
	 */
    
    public Argument(String n, String d, DataType t, List<String> restricted){
        name = n;
        type = t;
		description = d;
		value = "";
		restrictedValues = new ArrayList<String>(restricted);
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + t + "</type>\n" + "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" +  "</positionalArgument>\n"; 
    }
     
    
     /**
	 *This method adds a specified value to an argument object.
	 *
	 *@param v Value for this argument
	 */
	
    public void addValue(String v){
        value = v;
    }
    
	 /**
	 *This method returns the name of an argument object.
	 *
	 *@return name Argument name 
	 */
	
    public String getName(){
        return name;
    }
    
	 /**
	 *This method returns the value of an argument object.
	 *
	 *@return value argument value
	 */
	
    public String getValue(){
        return value;
    }
    
	 /**
	 *This method adds index value to an argument object and updates the XML data to reflect this value
	 *
	 *@param index Position of the argument   
	 */
	
    public void setPosition(int index){
        position = index;
        updateXMLDataForPArg();
    }
    

    private void updateXMLDataForPArg(){

        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + type + "</type>\n" + "<position>" + position + "</position>\n" +
						  "<restrictedValues>" + restrictedValues + "</restrictedValues>\n" + "</positionalArgument>\n"; 
    }
    
	 /**
	 *This method returns the data type of an argument object.
	 *
	 *@return type Data type identifier for the argument
	 */
	
    public DataType getType(){
        return type;
    }
	
	 /**
	 *This method returns the description of an argument object.
	 *
	 *@return description Description of the argument
	 */
	
    public String getDescription(){
		return description;
	}
    
	 /**
	 *This method returns the XML data for an argument object.
	 *
	 *@return XMLData returns a string containing the XML value for this argument 
	 */
	
    public String getXMLDataForArg(){
        return XMLData; 
    }
    
}