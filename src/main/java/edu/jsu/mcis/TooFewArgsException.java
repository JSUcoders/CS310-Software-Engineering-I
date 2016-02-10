package edu.jsu.mcis;

public class TooFewArgsException extends RuntimeException {
    private int numArgs;
    
    public TooFewArgsException(String[] cla){
        numArgs = cla.length;
        
    }
    
    public String showMessage(){
      
        
        if(numArgs == 0 ){
            return "Arguments given: "+ "\n" +"Arguments needed: ";
        }
        else if(numArgs==1 ){
            return "Arguments given: "+ "\n"+"Arguments needed: ";
        }
        else if(numArgs==2 ){
            return "Arguments given: "+ "\n" +"Arguments needed: ";
        }
        return "";
    }
}