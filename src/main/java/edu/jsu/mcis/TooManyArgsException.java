package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	

	public TooManyArgsException(String preMessage,String[] cla, List<Argument> arguments, String prgmName, List<String> oAN, List<String> oAV){

		String extraArgs = "";
		if(oAN.size() == 0){
            for(int i = arguments.size(); i < cla.length; i++){
                extraArgs = extraArgs + " " + cla[i];
	        }
        }
		
        else{
            List<String> temp = new ArrayList<String>();
            for(int i =0; i < arguments.size();i++){
                temp.add(arguments.get(i).getValue());
            } 
            for(int i = 0; i < cla.length;i++){
                if(!oAN.contains(cla[i]) && !oAV.contains(cla[i]) && !temp.contains(cla[i])){
                    extraArgs = extraArgs + " " + cla[i];
                    
                }
               
            }
        }
        
        


       
         
		exceptionOutput = preMessage + "\n" + prgmName+".java: error: unrecognized arguments:"+extraArgs;
        System.out.println(getExceptionOutput());

    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}