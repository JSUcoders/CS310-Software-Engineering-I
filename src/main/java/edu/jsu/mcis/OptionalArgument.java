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
    public OptionalArgument(String n, String d, String v, DataType t, String shortName){
        super(n,d,t);
		addValue(v);
        this.shortName = shortName;
         XMLData = "<optionalArgument>\n" + "<name>" + name + "</name>\n" + "<description>" + description + "</description>\n" + 
                         "<defaultValue>" + value + "</defaultValue>\n" +"<type>" + type + "</type>\n" +"<shortName>" + this.shortName + 
                         "</shortName>\n" + "</optionalArgument>\n"; 
    }
    
	 /**
	 *Class constructor. 
	 */
	
    public void setValue(String v){
        addValue(v);
    }
    
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */
	
    public String getShortName(){
        return this.shortName;
    }
    
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */
	
    public String getXMLDataForOptArg(){
       return XMLData; 
        
    }
}