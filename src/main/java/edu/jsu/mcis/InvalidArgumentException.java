package edu.jsu.mcis;
import java.util.*;

public class InvalidArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	public InvalidArgumentException(String preMsg, String programName, Argument arg){
        String datatype = "";
		if(arg.getType() == Argument.DataType.INT){
            datatype = "integer";
        }
        else if(arg.getType() == Argument.DataType.FLOAT ){
            datatype = "float";
        }
        
        else if(arg.getType() == Argument.DataType.BOOL ){
            datatype = "boolean";
        }
        
		exceptionOutput= preMsg +"\n"+programName+".java:"+" error: argument " + arg.getName()+": invalid " +datatype+ " value: " + arg.getValue();
		
    }
    
    public String getExceptionOutput(){
			
			
        return exceptionOutput;
			
			
    }
			
    
		
}
	
	
	
	
	
