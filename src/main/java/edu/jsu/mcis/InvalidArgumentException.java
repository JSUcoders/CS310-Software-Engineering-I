package edu.jsu.mcis;
import java.util.*;

public class InvalidArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	
	
	
	public InvalidArgumentException(List<String>argNames,String programName ){
		
		String args= "";
		
		
		for(int i=0;i<argNames.size();i++){
			
			args+=(argNames.get(i)+ " ");
			
			
			
		}
		
		
		String argSub = args.substring(0, args.length()-1);
		exceptionOutput= "usage: java"+programName+"  "+argSub+"\n"+programName+".java:"+" error: argument ";
		
		
		
		
		
		/*public String getExceptionOutput(){
			
			
			return exceptionOutput;
			
			
		}*/
		
		
			
			
			
			
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
