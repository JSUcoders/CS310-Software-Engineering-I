package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	
	public TooManyArgsException(String preMsg, String[] cla, List<String> argNames){
		String extraArgs = "";
		String args = "";
		for(int i = argNames.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String argSub = args.substring(0, args.length() - 1); 
		exceptionOutput = preMsg + ".java: error: unrecognized arguments:"+extraArgs;
        //System.out.println(getExceptionOutput());
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}