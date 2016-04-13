package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	

	public TooManyArgsException(String preMessage,String[] cla, Map<String, Argument> arguments, String prgmName, Map<String, NamedArgument> optArgs, Map<String,String> unknownArgs){

		String extraArgs = "";
		if(optArgs.size() == 0){
            for(int i = arguments.size(); i < cla.length; i++){
                extraArgs = extraArgs + " " + cla[i];
	        }
        }
		
        else{
            List<String> temp = new ArrayList<String>();
            for (Map.Entry<String, Argument> entry : arguments.entrySet()) {
                String key = entry.getKey();
                Argument value = entry.getValue();
                temp.add(key);
                temp.add(value.getValue());
            }
            for (Map.Entry<String, NamedArgument> entry : optArgs.entrySet()) {
                String key = entry.getKey();
                Argument value = entry.getValue();
                temp.add(key);
                temp.add(value.getValue());
               
            }
            for (Map.Entry<String, String> entry : unknownArgs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                temp.add(key);
                temp.add(value);
            }

            for(int i = 0; i < cla.length;i++){
                if(!temp.contains(cla[i])){
                    extraArgs = extraArgs + " " + cla[i];
                    
                }
               
            }
        }
		exceptionOutput = preMessage + "\n" + prgmName+".java: error: unrecognized arguments:"+extraArgs;
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}