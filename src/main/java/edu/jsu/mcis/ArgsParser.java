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
    
    public void parse(String[] cla){
        for(int i =0; i < cla.length;i++){
            argValues.add(cla[i]);
        }
    }
    
    public String getArg(String name){
        if(name.equals(argNames.get(0))){
            return argValues.get(0);
        }
        else if(name.equals(argNames.get(1))){
            return argValues.get(1);
        }
        else if(name.equals(argNames.get(2))){
            return argValues.get(2); 
        }
        else return "";
        
    }
}