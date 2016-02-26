package edu.jsu.mcis;
import java.util.*;

public class InvalidArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	public InvalidArgumentException(String preMsg, String programName,String badArg, String badArgName, ArgsParser.DataType d){
        String datatype = "";
		if(d == ArgsParser.DataType.INT){
            datatype = "integer";
        }
        else if(d == ArgsParser.DataType.FLOAT ){
            datatype = "float";
        }
        else if(d == ArgsParser.DataType.DOUBLE){
            datatype = "double";
        }
        else if(d == ArgsParser.DataType.CHAR ){
            datatype = "char";
        }
        else if(d == ArgsParser.DataType.BOOL ){
            datatype = "boolean";
        }
        else if(d == ArgsParser.DataType.LONG ){
            datatype = "long";
        }
        else if(d == ArgsParser.DataType.SHORT){
            datatype = "short";
        }
        else if(d == ArgsParser.DataType.BYTE){
            datatype = "byte";
        }
		exceptionOutput= preMsg +"\n"+programName+".java:"+" error: argument " + badArgName+": invalid " +datatype+ " value: " + badArg;
		System.out.println(getExceptionOutput());
    }
    
    public String getExceptionOutput(){
			
			
        return exceptionOutput;
			
			
    }
			
    
		
}
	
	
	
	
	
