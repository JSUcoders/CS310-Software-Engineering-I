package edu.jsu.mcis;
import java.util.*;

public class RestrictedArgumentException extends RuntimeException{
	private String exceptionOutput="";

	public RestrictedArgumentException(String preMessage, String prgmName, String restrictedValue, Argument arg){
		String allowedValues = new String();
		for(int i = 0; i < arg.restrictedValues.size(); i++){
			allowedValues += arg.restrictedValues.get(i);
			if(i + 1 != arg.restrictedValues.size()){
				allowedValues += ", ";
			}
		}
		exceptionOutput = preMessage + "\n" + prgmName + ".java: error: " + restrictedValue + " is not an allowed value for " + arg.getName() + "\nAllowed Values: " + allowedValues;
	}
	
	public String getExceptionOutput(){
        return exceptionOutput;		
    }
}