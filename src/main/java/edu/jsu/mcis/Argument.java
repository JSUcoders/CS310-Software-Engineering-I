package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the user to ...
 *<p>
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
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + t + "</type>\n" +  "</positionalArgument>\n"; 
    }
    
     /**
	 *This method adds a ... to the program.
	 *
	 *@param v Value for this argument
	 */
	
    public void addValue(String v){
        value = v;
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return name Argument name 
	 */
	
    public String getName(){
        return name;
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return value argument value
	 */
	
    public String getValue(){
        return value;
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@param index Position of the argument   
	 */
	
    public void setPosition(int index){
        position = index;
        updateXMLData();
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 */
	
    public void updateXMLData(){
        XMLData = "<positionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                          "<type>" + type + "</type>\n" + "<position>" + position + "</position>\n" + "</positionalArgument>\n"; 
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return type Data type identifier for the argument
	 */
	
    public DataType getType(){
        return type;
    }
	
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return description Description of the argument
	 */
	
    public String getDescription(){
		return description;
	}
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return XMLData returns a string containing the XML value for this argument 
	 */
	
    public String getXMLDataForArg(){
        return XMLData; 
    }
    
}