package edu.jsu.mcis;
import java.util.*;
public class TooFewArgsException extends RuntimeException {
    
    
    public TooFewArgsException(String[] cla, List<String> argNames){
        System.out.println("Not enough arguments. Need: "+argNames.size() + " Have: "+cla.length);
        System.out.println("Arguments required: ");
        for(int i =0; i < argNames.size();i++){
            System.out.println(argNames.get(i));
        }
        
    }
    
    
}