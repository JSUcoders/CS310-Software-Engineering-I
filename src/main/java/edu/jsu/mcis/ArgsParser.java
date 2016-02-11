package edu.jsu.mcis;
import java.util.*;

public class ArgsParser{
    
    private List<String> argValues;
    private List<String> argNames;
    
   
    public ArgsParser(){
        argValues = new ArrayList<String>();
        argNames = new ArrayList<String>();
       
    }


    public int getNumOfArguments(){
        return argValues.size();
    }
    
    public int getNumOfNameArgs(){
        return argNames.size();
    }
    
    public void addArg(String name){
        argNames.add(name);
    }
    
    private void checkForTooFewArgs(String[] cla, List<String> numNamedArgs)  {
        if(cla.length < argNames.size()){
            
            throw new TooFewArgsException(cla, numNamedArgs);
            
        }
        
    }
    

    
    public void parse(String[] cla) {
        
           
        for(int i =0; i < cla.length;i++){
             argValues.add(cla[i]);
        } 
        
        checkForTooFewArgs(cla, argNames);
        
    }
    
    public String getArg(String name){
        for(int i =0;i < getNumOfNameArgs();i++){
            if(name.equals(argNames.get(i))){
                return argValues.get(i);
            }
        }
        return "";
       
        
    }
    
    
}