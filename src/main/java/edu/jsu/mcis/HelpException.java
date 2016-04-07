
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

public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
	
	 /**
	 *Class constructor. 
	 */
	
	public HelpException(String preMessage, String prgmDescript, List<Argument> arguments){	
        String argD = "";        
        for(int i = 0; i < arguments.size();i++){
            argD += "\n" + arguments.get(i).getDescription();
        }
        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+argD;//argDSub        
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