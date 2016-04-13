package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves a required named arg not being used. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	p.setProgramName("VolumeCalculator");
 *	String[] s = {"7", "3", "2"};
 *	p.addArg("length");
 *	p.addArg("width");
 *	p.addArg("height");
 *  p.addOptionalArgument(new OptionalArgument("--type", "the type of the object", "box", OptionalArgument.DataType.STRING, "-t", true));
 *	p.parse(s);
 *</pre>

 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class RequiredArgumentsNeededException extends RuntimeException{
    private String exceptionOutput;
    
     /**
	 *Class constructor. 
	 *
	 *@param optArgs the named arguments map  
	 */
    
    public RequiredArgumentsNeededException(Map<String, NamedArgument> optArgs){
        exceptionOutput = "You have not used the required named arguments.\nThe required named arguments are: " ;
        for(NamedArgument o: optArgs.values()){
            if(o.required){
                exceptionOutput += o.getName() + " ";
            }
        }
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