package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves an argument that does not exist to be called from the command line. 
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *<p>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	ArgsParser p = new ArgsParser();
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramName("VolumeCalculator");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("length", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("width", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("height", Argument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.parse(s);
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	try{
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;		Argument.DataType d = p.getDataType("--z-axis");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	}catch(ThatArgumentDoesNotExistException e){
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

public class ThatArgumentDoesNotExistException extends RuntimeException{ 
    private String exceptionOutput;
	
	 /**
	 *Class constructor. 
	 *
	 *@param name Argument name  
	 *@param optionalArguments Optional arguments list
	 *@param arguments Program arguments list
	 */
	
    public ThatArgumentDoesNotExistException(String name, List<OptionalArgument> optionalArguments, List<Argument> arguments){
        exceptionOutput = "The argument " + name+ " does not exist." + " Here are the optional argument names given: ";
        for(int i =0 ; i < optionalArguments.size();i++){
            exceptionOutput += optionalArguments.get(i).getName() + " ";
        }
        exceptionOutput += "\n" + "Here are the regular arguments given: ";
        for(int i =0; i < arguments.size();i++){
            exceptionOutput += arguments.get(i).getName() + " ";
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