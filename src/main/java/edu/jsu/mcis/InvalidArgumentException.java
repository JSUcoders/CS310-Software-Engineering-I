package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves an invalid arguments called from the command line. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	p.setProgramName("VolumeCalculator");v
 *	String[] s = {"7", "something", "2"};
 *	p.addArg("length", Argument.DataType.FLOAT);
 *	p.addArg("width", Argument.DataType.FLOAT);
 *	p.addArg("height", Argument.DataType.FLOAT);
 *	try {
 *		p.parse(s);
 *	}
 *	catch(InvalidArgumentException e) {
 *		System.out.println(e.getExceptionOutput());
 *		throw e;
 *	}
 *</pre>
 
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
	 *This method returns the output of this exception.
	 *
	 *@return exceptionOutput Exception output message
	 */
	
    public String getExceptionOutput(){
			
			
        return exceptionOutput;
			
			
    }
			
    
		
}
	
	
	
	
	
