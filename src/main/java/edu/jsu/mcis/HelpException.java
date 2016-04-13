
package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves a help argument to be called from the command line. 
 *</p>
 *<p>
 * this is an example of how this exception would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *      	ArgsParser p = new ArgsParser();
 *      	String[] s = {"-h"}; or  String[] s = {"--help"};
 *      	p.setProgramName("VolumeCalculator");
 *      	p.setProgramDescription("Calculate the volume of a box.");
 *      	p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *      	p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
 *      	p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
 *      	p.parse(s);
 *</pre>

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
	 *@param arguments Program map of arguments	 
	 */

	public HelpException(String preMessage, String prgmDescript, Map<String, Argument> arguments){	
        String argD = ""; 
        for (Argument a : arguments.values()) {
            argD += "\n" + a.getDescription(); 
        }       

        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+argD;      
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