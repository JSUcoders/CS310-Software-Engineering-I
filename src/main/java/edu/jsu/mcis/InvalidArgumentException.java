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

public class InvalidArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	 /**
	 *Class constructor. 
	 *
	 *@param preMsg Pre-message used to contruct the exceptionOutput
	 *@param programName Program name
	 *@param arg Argument name
	 */
	
	public InvalidArgumentException(String preMsg, String programName, Argument arg){
        String datatype = "";
		if(arg.getType() == Argument.DataType.INT){
            datatype = "integer";
        }
        else if(arg.getType() == Argument.DataType.FLOAT ){
            datatype = "float";
        }
        
        else if(arg.getType() == Argument.DataType.BOOL ){
            datatype = "boolean";
        }
        
		exceptionOutput= preMsg +"\n"+programName+".java:"+" error: argument " + arg.getName()
        +": invalid " +datatype+ " value: " + arg.getValue();
		
    }
    
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return exceptionOutput Exception output message
	 */
	
    public String getExceptionOutput(){
			
			
        return exceptionOutput;
			
			
    }
			
    
		
}
	
	
	
	
	
