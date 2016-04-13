package edu.jsu.mcis;
import java.util.*;

public class ThatArgumentDoesNotExistException extends RuntimeException{ 
    private String exceptionOutput;
    public ThatArgumentDoesNotExistException(String name, Map<String, NamedArgument> optionalArguments, Map<String,Argument> arguments){
        exceptionOutput = "The argument " + name+ " does not exist." + " Here are the optional argument names given: ";
        for(String key : optionalArguments.keySet()){
            exceptionOutput += optionalArguments.get(key).getName() + " ";
        }
        exceptionOutput += "\n" + "Here are the regular arguments given: ";
        for(String key : arguments.keySet()){
            exceptionOutput += arguments.get(key).getName() + " ";
        }
    } 
    



    public String getExceptionOutput(){
        return exceptionOutput;
    }



}