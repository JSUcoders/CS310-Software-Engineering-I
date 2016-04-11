
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
	 *
	 *@param preMessage Pre-message used to contruct the exceptionOutput   
	 *@param prgmDescript Program description
	 *@param arguments Program list of arguments	 
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
	 *This method adds a ... to the program.
	 *
	 *@return exceptionOutput The output message from this exception.
	 */
	
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}