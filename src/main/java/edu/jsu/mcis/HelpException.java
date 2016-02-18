
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
    public HelpException(List<String> argNames, String prgmName, String prgmDescript, String[] argDescripts){
        String args = "";
        String argD = "";
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        for(int i = 0; i < argDescripts.length;i++){
            argD +=   argDescripts[i] + "\n";
        }
        String argDSub = argD.substring(0, argD.length() - 1);
        String argSub = args.substring(0, args.length() - 1);
		exceptionOutput ="usage: java "+ prgmName+" "+argSub+"\n"+prgmDescript+"\n"+"positional arguments:"+"\n"+ argDSub;
        System.out.println(getExceptionOutput());
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}

}