package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	/*public TooManyArgsException(String message){
		super(message);
	}*/
	
	public TooManyArgsException(String[] cla, List<String> argNames){
		String extraArgs = "";
		
		for(int i = argNames.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}
	
        System.out.println("Unrecognized arguments: " + extraArgs);
        System.out.println("Arguments required: ");
        for(int i =0; i < argNames.size();i++){
            System.out.println(argNames.get(i));
        }
        
    }
}