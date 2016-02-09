package edu.jsu.mcis;

public class TooFewArgsException extends Exception{
    int numArgs;
    public TooFewArgsException(String[] clArgs){
        numArgs = clArgs.length;
    }
    
    public void showMessage(){
        if(numArgs == 0){
            System.out.println("Arguments given: 0"+ "\n" +"Arguments needed: 3" );
        }
        else if(numArgs==1){
            System.out.println("Arguments given: 1"+ "\n"+"Arguments needed: 3" );
        }
        else if(numArgs == 2){
            System.out.println("Arguments given: 2"+ "\n" +"Arguments needed: 3" );
        }
        
    }
}