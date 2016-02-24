package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	
<<<<<<< HEAD
	public TooManyArgsException(String preMsg, String[] cla, List<String> argNames){
=======
	public TooManyArgsException(String preMsg, String prgmName, String[] cla, List<String> argNames){
>>>>>>> origin/Scrum-01
		String extraArgs = "";
		
		for(int i = argNames.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}
<<<<<<< HEAD
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String argSub = args.substring(0, args.length() - 1); 
		exceptionOutput = preMsg + ".java: error: unrecognized arguments:"+extraArgs;
        //System.out.println(getExceptionOutput());
=======
       
         
		exceptionOutput = preMsg + "\n" + prgmName + ".java: error: unrecognized arguments:"+extraArgs;
        System.out.println(getExceptionOutput());
>>>>>>> origin/Scrum-01
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}