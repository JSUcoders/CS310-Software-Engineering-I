
package edu.jsu.mcis;
import java.util.*;
public class HelpException extends RuntimeException{
    public HelpException(List<String> argList, List<String> argNames){
        System.out.println("Positional arguments: ");
        for(int i = 0; i < argNames.size();i++){
            System.out.println(argNames.get(i));
        }
    }
}