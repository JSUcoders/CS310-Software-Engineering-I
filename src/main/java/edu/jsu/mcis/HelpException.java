
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
	public HelpException(String preMessage, String prgmDescript, Map<String, Argument> arguments){	
        String argD = ""; 
        for (Argument a : arguments.values()) {
            argD += "\n" + a.getDescription(); 
        }       
        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+argD;      
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}