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

public class ThatArgumentDoesNotExistException extends RuntimeException{ 
    private String exceptionOutput;
	
	 /**
	 *Class constructor. 
	 */
	
    public ThatArgumentDoesNotExistException(String name, List<OptionalArgument> optionalArguments, List<Argument> arguments){
        exceptionOutput = "The argument " + name+ " does not exist." + " Here are the optional argument names given: ";
        for(int i =0 ; i < optionalArguments.size();i++){
            exceptionOutput += optionalArguments.get(i).getName() + " ";
        }
        exceptionOutput += "\n" + "Here are the regular arguments given: ";
        for(int i =0; i < arguments.size();i++){
            exceptionOutput += arguments.get(i).getName() + " ";
        }
    } 
    
	/**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */

    public String getExceptionOutput(){
        return exceptionOutput;
    }



}