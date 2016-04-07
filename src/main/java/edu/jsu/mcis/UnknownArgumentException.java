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

public class UnknownArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */
	
	public UnknownArgumentException(String preMessage, String prgrmName, 
    List<String> unknownArgumentNames){
		if(unknownArgumentNames.size() == 1){
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
            + " error: argument "+ unknownArgumentNames.get(0) 
            + " is an unknown argument";
		}
		
		else{
			String temp = "";
			for(int i = 0; i < unknownArgumentNames.size(); i++){
				temp += unknownArgumentNames.get(i);
				if(i != unknownArgumentNames.size() - 1){
					temp += ", ";
				}
			}
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
            + " error: arguments " + 
            temp + " are unknown arguments";
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