
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
    public HelpException(String preMessage, String prgmDescript, String[] argDescripts){
        String argD = "";        
        for(int i = 0; i < argDescripts.length;i++){
            argD +=   argDescripts[i] + "\n";
        }
        String argDSub = argD.substring(0, argD.length() - 1);        
		exceptionOutput = preMessage+"\n"+prgmDescript+"\n"+"positional arguments:"+"\n"+ argDSub;
        //System.out.println(getExceptionOutput());
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}

}