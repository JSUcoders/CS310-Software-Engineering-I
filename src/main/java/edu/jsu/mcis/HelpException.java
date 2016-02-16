
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
	private String exceptionOutput= ""; 
    public HelpException(List<String> argList, List<String> argNames, String prgmName, String prgmDescript){
        String args = "";
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String argSub = args.substring(0, args.length() - 1);
		exceptionOutput ="usage: java "+ prgmName+" "+argSub+"\n"+prgmDescript+"\npositional arguments:"+"\nlength the length of the box (float)"+"\nwidth the width of the box(float)"+"\nheight the height of the box(float)";
        System.out.println(getExceptionOutput());
    }
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}