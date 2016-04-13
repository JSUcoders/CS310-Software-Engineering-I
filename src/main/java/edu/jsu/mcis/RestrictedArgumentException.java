package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves an argument not being parsed with its specified values. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *ArgsParser p = new ArgsParser();
 *p.setProgramName("VolumeCalculator");
 *List&lt;String&gt; restrictedValues = Arrays.asList("6", "4");
 *String[] s = {"7", "3", "2"};
 *p.addArg(new Argument("length", "length the length", Argument.DataType.FLOAT, restrictedValues));
 *p.addArg("width");
 *p.addArg("height");
 *p.parse(s);
 *</pre>

 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class RestrictedArgumentException extends RuntimeException{
	private String exceptionOutput="";
    
     /**
	 *Class constructor. 
	 *
	 *@param preMessage Pre-message used to construct the exceptionOutput  
	 *@param prgmName the name of the program
	 *@param invalidValue the value that is not contained in the restricted set
	 *@param arg the argument that contains the restricted set
	 */

	public RestrictedArgumentException(String preMessage, String prgmName, String invalidValue, Argument arg){
		String allowedValues = new String();
		for(int i = 0; i < arg.restrictedValues.size(); i++){
			allowedValues += arg.restrictedValues.get(i);
			if(i + 1 != arg.restrictedValues.size()){
				allowedValues += ", ";
			}
		}
		exceptionOutput = preMessage + "\n" + prgmName + ".java: error: " + invalidValue + " is not an allowed value for " + arg.getName() + "\nAllowed Values: " + allowedValues;
	}

	 /**
	 *This method returns the output of this exception.
	 *
	 *@return exceptionOutput The output message from this exception.
	 */	
    
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
}