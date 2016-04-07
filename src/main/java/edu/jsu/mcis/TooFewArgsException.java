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

public class TooFewArgsException extends RuntimeException {
    private String exceptionOutput= "";
    
	 /**
	 *This method adds a named argument to the program.
	 */
	
    public TooFewArgsException(String preMsg,  String[] cla, List<Argument> arguments, String prgmName){
        String args = "";
        String missingArgNames = "";
        
        if(cla.length == 0){
            
            for(int i = 0; i < arguments.size(); i++){
                args += arguments.get(i).getName() + " ";
            }
            String argsSub = args.substring(0, args.length()- 1);
            missingArgNames = argsSub;
        }
        else{
            args = "";
            for(int i = cla.length; i < arguments.size() ;i++){
               args += (arguments.get(i).getName() + " "); 
            }
            String argsSub = args.substring(0, args.length() - 1);
            missingArgNames = argsSub;    
            
            
        }
        exceptionOutput = preMsg+"\n"+ prgmName+".java: error: the following arguments are required: "+missingArgNames;
        
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