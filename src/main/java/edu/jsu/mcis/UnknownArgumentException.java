package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involve unknown arguments to be called from the command line. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *      		ArgsParser p = new ArgsParser();
 *      		String[] s = {"7", "--myArg", "myval", "3", "2"};
 *      		p.setProgramName("VolumeCalculator");
 *      		p.addArg("length", Argument.DataType.FLOAT);
 *      		p.addArg("width", Argument.DataType.FLOAT);
 *      		p.addArg("height", Argument.DataType.FLOAT);
 *      		try{
 *      			p.parse(s);
 *      		}catch(UnknownArgumentException e){
 *      			System.out.println(e.getExceptionOutput());
 *      			throw e;
 *      		}
 *</pre>
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
	 *Class constructor. 
	 *
	 *@param preMessage pre message that is passed in to help identify where the issue is ocurring 
	 *@param prgrmName Program name
	 *@param unknownArguments Program map of unknown arguments
	 */
	
	public UnknownArgumentException(String preMessage, String prgrmName, 
                                        Map<String,String> unknownArguments){
        int count = 0;
		if(unknownArguments.size() == 1){
            for(String key : unknownArguments.keySet()){
                if(count < 1){
                   exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
                    + " error: argument "+ unknownArguments.get(key) 
                    + " is an unknown argument";
                    count++;
                }
                
            }
			
		}
		
		else{
            int i = 0;
			String temp = "";
			for(String key : unknownArguments.keySet()){
				temp += unknownArguments.get(key);
				if(i != unknownArguments.size() - 1){
					temp += ", ";
				}
                i++;
			}
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
            + " error: arguments " + 
            temp + " are unknown arguments";
		}
	}
	
	 /**
	 *This method returns the output of this exception.
	 *
	 *@return exceptionOutput Exception output message
	 *
	 */
	
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
	
}