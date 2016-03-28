package edu.jsu.mcis;
import java.util.*;

public class ThatArgumentDoesNotExistException extends RuntimeException{ 
    private String exceptionOutput;
    public ThatArgumentDoesNotExistException(String name, List<String> optArgNames, List<Argument> arguments){
        exceptionOutput = "The argument " + name+ " does not exist." + " Here are the optional argument names given: ";
        for(int i =0 ; i < optArgNames.size();i++){
            exceptionOutput += optArgNames.get(i) + " ";
        }
        exceptionOutput += "\n" + "Here are the regular arguments given: ";
        for(int i =0; i < arguments.size();i++){
            exceptionOutput += arguments.get(i).getName() + " ";
        }
    } 
    



    public String getExceptionOutput(){
        return exceptionOutput;
    }



}