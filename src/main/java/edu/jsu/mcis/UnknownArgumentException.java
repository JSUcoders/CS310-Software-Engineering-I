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
	 *@param unknownArgumentNames Program list of unknown argument names
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
	 *This method returns the output of this exception.
	 *
	 *@return exceptionOutput Exception output message
	 *
	 */
	
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
	
}