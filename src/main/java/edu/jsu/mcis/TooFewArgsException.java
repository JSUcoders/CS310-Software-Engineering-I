package edu.jsu.mcis;
import java.util.*;
public class TooFewArgsException extends RuntimeException {
    
    
    public TooFewArgsException(String[] cla, List<String> argNames, String prgmName){
        String args = "";
        for(int i =0;i<argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String missingArgNames = "";
        String argSub = args.substring(0, args.length() - 1);
        if(cla.length == 0){
            missingArgNames = argSub;
        }
        else{
            args = "";
            for(int i = cla.length; i < argNames.size() ;i++){
               args += (argNames.get(i) + " "); 
            }
            argSub = args.substring(0, args.length() - 1);
            missingArgNames = argSub;    
            
            
        }
        
        System.out.println("usage: java "+ prgmName+" "+argSub + "\n" + prgmName + ".java: error: the following arguments are required: "+missingArgNames);
        
        
    }
    
    
}