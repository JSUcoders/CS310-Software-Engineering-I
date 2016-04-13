package edu.jsu.mcis;
import java.util.*;

public class RequiredArgumentsNeededException extends RuntimeException{
    private String exceptionOutput;
    public RequiredArgumentsNeededException(Map<String, NamedArgument> optArgs){
        exceptionOutput = "You have not used the required named arguments.\nThe required named arguments are: " ;
        for(NamedArgument o: optArgs.values()){
            if(o.required){
                exceptionOutput += o.getName() + " ";
            }
        }
    }
    
    public String getExceptionOutput(){
        return exceptionOutput;
    }

}