package edu.jsu.mcis;
import java.util.*;

public class InvalidArgumentException extends RuntimeException{
	
	private String exceptionOutput="";
	
	public InvalidArgumentException(String preMsg, String programName,List<String> argNms, List<String> argVls, List<ArgsParser.DataType> argDataType){
		for(int i =0; i < argNms.size();i++){
            //if(argVls.get(i) )
            //(/*argValue at certain index can not be parsed into datatype at same index, then argName at same index should be known*/)
        }
		exceptionOutput= preMsg +"\n"+programName+".java:"+" error: argument "/*wrongArg invalid datatypeofarg + argthatswrong*/;
		
		/*public String getExceptionOutput(){
			
			
			return exceptionOutput;
			
			
		}*/
			
		}
		
	}
	
	
	
	
	
