package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves an invalid arguments called from the command line. 
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *<p>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	ArgsParser p = new ArgsParser();
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramName("VolumeCalculator");v
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	String[] s = {"7", "something", "2"};
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("length", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("width", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("height", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	try {
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;		p.parse(s);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	}
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	catch(InvalidArgumentException e) {
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;		System.out.println(e.getExceptionOutput());
 *<br> 
 *      &nbsp;&nbsp;&nbsp;&nbsp;		throw e;
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	}
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
	
	
	
	
	
