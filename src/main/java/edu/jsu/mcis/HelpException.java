
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
    //public HelpException(String preMessage, String prgmDescript, String[] argDescripts){
	public HelpException(String preMessage, String prgmDescript, List<Argument> arguments){	
        String argD = "";        
        for(int i = 0; i < arguments.size();i++){
            argD += "\n" + arguments.get(i).getDescription();
        }
        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+argD;//argDSub
        
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}

}