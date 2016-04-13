package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves too few arguments called from the command line. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	p.setProgramName("VolumeCalculator");
 *	String[] s = {"7", "3"};
 *	p.addArg("length");
 *	p.addArg("width");
 *	p.addArg("height");
 *	p.parse(s);
 *</pre>

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
	 *Class constructor. 
	 *
	 *@param preMsg Pre-message used to contruct the exceptionOutput 
	 *@param cla Command Line Argument
	 *@param arguments List of Program arguments 	 
	 *@param prgmName Program name
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
	 *This method returns the output of this exception.
	 *
	 *@return exceptionOutput Exception output message
	 */
	
    public String getExceptionOutput(){
		return exceptionOutput;
	}
    
}