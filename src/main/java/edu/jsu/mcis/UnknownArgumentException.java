package edu.jsu.mcis;
import java.util.*;

public class UnknownArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	public UnknownArgumentException(String preMessage, String prgrmName, List<String> unknownArgumentNames){
		if(unknownArgumentNames.size() == 1){
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" + " error: argument " + unknownArgumentNames.get(0) + " is an unknown argument";
		}
		
		else{
			String temp = "";
			for(int i = 0; i < unknownArgumentNames.size(); i++){
				temp += unknownArgumentNames.get(i);
				if(i != unknownArgumentNames.size() - 1){
					temp += ", ";
				}
			}
			exceptionOutput = preMessage + "\n" + prgrmName + ".java:" + " error: arguments " + temp + " are unknown arguments";
		}
	}
	
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
	
}