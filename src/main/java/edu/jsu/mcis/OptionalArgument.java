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

public class OptionalArgument extends Argument{
    protected String shortName;
	
	/**
	 *Class constructor. 
	 *
	 *@param n Argument name  
	 *@param d Argument description
	 *@param v Argument value
	 *@param t Argument data Type identifier
	 *@param shortName shortname of this argument	 
	 */
	
	
	
    public OptionalArgument(String n, String d, String v, DataType t, String shortName){
        super(n,d,t);
		addValue(v);
        this.shortName = shortName;
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "</optionalArgument>\n"; 
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@param v Value of this argument
	 */ 
	
    public void setValue(String v){
        addValue(v);
    }
    
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@return this.shortName Shortname of this argument
	 */
	
    public String getShortName(){
        return this.shortName;
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return XMLData String of XML data representing this argument
	 */
	
    public String getXMLDataForOptArg(){
       return XMLData; 
        
    }
}