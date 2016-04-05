package edu.jsu.mcis;
import java.util.*;

public class UnknownArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	public UnknownArgumentException(String preMessage, String prgrmName, 
                                        Map<String,String> unknownArguments){
        int count = 0;
		if(unknownArguments.size() == 1){
            for(String key : unknownArguments.keySet()){
                if(count < 1){
                   exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
                    + " error: argument "+ unknownArguments.get(key) 
                    + " is an unknown argument";
                    count++;
                }
                
            }
			
		}
		
		else{
            int i = 0;
			String temp = "";
			for(String key : unknownArguments.keySet()){
				temp += unknownArguments.get(key);
				if(i != unknownArguments.size() - 1){
					temp += ", ";
				}
                i++;
			}
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" 
            + " error: arguments " + 
            temp + " are unknown arguments";
		}
	}
	
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
	
}