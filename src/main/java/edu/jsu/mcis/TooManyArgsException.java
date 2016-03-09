package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	

	public TooManyArgsException(String preMessage,String[] cla, List<Argument> arguments, String prgmName){

		String extraArgs = "";
		
		for(int i = arguments.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}


       
         
		exceptionOutput = preMessage + "\n" + prgmName+".java: error: unrecognized arguments:"+extraArgs;
        

    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}