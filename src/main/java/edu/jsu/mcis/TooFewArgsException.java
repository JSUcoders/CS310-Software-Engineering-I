package edu.jsu.mcis;
import java.util.*;
public class TooFewArgsException extends RuntimeException {
    private String exceptionOutput= "";
    
    public TooFewArgsException(String preMsg,  String[] cla, Map<String, Argument> arguments, String prgmName){
        String args = "";
        String missingArgNames = "";
        
        if(cla.length == 0){
            
            for (String key : arguments.keySet()) {
                args += key + " ";
            }    

            String argsSub = args.substring(0, args.length()- 1);
            missingArgNames = argsSub;
        }
        else{
            args = "";
            int i = cla.length;
            int count =0;
            for (String key : arguments.keySet()) {
                if(count < cla.length){
                    count++;
                }
                else{
                   args += key + " "; 
                }
                
            }
            String argsSub = args.substring(0, args.length() - 1);
            missingArgNames = argsSub;    
            
            
        }
        exceptionOutput = preMsg+"\n"+ prgmName+".java: error: the following arguments are required: "+missingArgNames;
        
    }
    public String getExceptionOutput(){
		return exceptionOutput;
	}
    
}