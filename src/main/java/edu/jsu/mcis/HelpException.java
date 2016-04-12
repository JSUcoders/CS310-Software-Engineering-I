
package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves a help argument to be called from the command line. 
 *<p>
 * this is an example of how this exception would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *<p>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	ArgsParser p = new ArgsParser();
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	String[] s = {"-h"}; or  String[] s = {"--help"};
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramName("VolumeCalculator");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramDescription("Calculate the volume of a box.");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.parse(s);
 *<br>

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
	 *@param arguments Program list of arguments	 
	 */
	
	public HelpException(String preMessage, String prgmDescript, List<Argument> arguments){	
        String argD = "";        
        for(int i = 0; i < arguments.size();i++){
            argD += "\n" + arguments.get(i).getDescription();
        }
        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+argD;//argDSub        
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