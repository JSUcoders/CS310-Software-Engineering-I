
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
    public HelpException(List<String> argList, List<String> argNames, String prgmName, String prgmDescript){
        String args = "";
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String argSub = args.substring(0, args.length() - 1);
        System.out.println("usage: java "+ prgmName+" "+argSub+"\n"+prgmDescript);
    }
}