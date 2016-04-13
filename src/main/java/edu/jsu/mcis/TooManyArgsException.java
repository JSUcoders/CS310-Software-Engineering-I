package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the use of runtime exceptions that are specific to a senario that involves too many arguments to be called from the command line. 
 *</p>
 *<p>
 * this is an example of how this excetpion would be thrown
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *</p>
 *<pre>
 *	ArgsParser p = new ArgsParser();
 *	p.setProgramName("VolumeCalculator");
 *	String[] s = {"--type", "ellipsoid","7","3","--digits","1","2", "--hello","6", "43","8"};
 *	p.addArg("length");
 *	p.addArg("width");
 *	p.addArg("height");
 *	p.addArg("--type", "box");
 *	p.addArg("--digits", "4");
 *	p.addArg("--hello", "3");
 *	try{
 *		p.parse(s);
 *	}catch(TooManyArgsException e){          
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

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	
	/**
	 *Class constructor. 
	 *
	 *@param preMessage Pre-message used to contruct the exceptionOutput
	 *@param cla Command Line Arguments array
	 *@param arguments Program arguments map
	 *@param prgmName Program name
 	 *@param optArgs Named arguments map
	 *@param unknownArgs unknown arguments map
	 */

	public TooManyArgsException(String preMessage,String[] cla, Map<String, Argument> arguments, String prgmName, Map<String, NamedArgument> optArgs, Map<String,String> unknownArgs){

		String extraArgs = "";
		if(optArgs.size() == 0){
            for(int i = arguments.size(); i < cla.length; i++){
                extraArgs = extraArgs + " " + cla[i];
	        }
        }
		
        else{
            List<String> temp = new ArrayList<String>();
            for (Map.Entry<String, Argument> entry : arguments.entrySet()) {
                String key = entry.getKey();
                Argument value = entry.getValue();
                temp.add(key);
                temp.add(value.getValue());
            }
            for (Map.Entry<String, NamedArgument> entry : optArgs.entrySet()) {
                String key = entry.getKey();
                Argument value = entry.getValue();
                temp.add(key);
                temp.add(value.getValue());
               
            }
            for (Map.Entry<String, String> entry : unknownArgs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                temp.add(key);
                temp.add(value);
            }

            for(int i = 0; i < cla.length;i++){
                if(!temp.contains(cla[i])){
                    extraArgs = extraArgs + " " + cla[i];
                    
                }
               
            }
        }
		exceptionOutput = preMessage + "\n" + prgmName+".java: error: unrecognized arguments:"+extraArgs;
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