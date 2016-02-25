package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	

	public TooManyArgsException(String preMessage,String[] cla, List<String> argNames){

		String extraArgs = "";
		
		for(int i = argNames.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}


       
         
		exceptionOutput = preMessage + ".java: error: unrecognized arguments:"+extraArgs;
        //System.out.println(getExceptionOutput());

    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}