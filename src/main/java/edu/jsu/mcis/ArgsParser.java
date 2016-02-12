package edu.jsu.mcis;
import java.util.*;

public class ArgsParser{
    
    private List<String> argValues;
    private List<String> argNames;
    private String programName;
    private String programDescription;
    public ArgsParser(){
        argValues = new ArrayList<String>();
        argNames = new ArrayList<String>();
        programName = "";
        programDescription = "";
    }
    
  
    
    public void setProgramDescription(String d){
      programDescription = d; 
    }
    
    public String getProgramDescription(){
        return programDescription;
    }
    
    public void setProgramName(String s){
        programName = s;
    }
    
    public String getProgramName(){
        return programName;
    }


    public int getNumOfArguments(){
        return argValues.size();
    }
    
    public int getNumOfNameArgs(){
        return argNames.size();
    }
    
    public void addArg(String name){
        argNames.add(name);
    }
    
    private void checkForTooFewArgs(String[] cla, List<String> numNamedArgs, String prgmName)  {
    
        if(cla.length < argNames.size()){
            
            throw new TooFewArgsException(cla, numNamedArgs, prgmName);
            
        }
        
    }
    
	private void checkForTooManyArgs(String[] cla, List<String> numNamedArgs, String prgmName) {
        
		if(cla.length > argNames.size()){
		
			throw new TooManyArgsException(cla, numNamedArgs, prgmName);
		
		}
	
	}
    
    private void checkForHelp(String[] cla, List<String> numArgs, List<String> numNamedArgs, String prgmName, String prgmDescript){
       
        if(cla[0].equals("-h") || cla[0].equals("-help")){
            throw new HelpException(numArgs, numNamedArgs, prgmName, prgmDescript); 
        }
    }
    
    public void parse(String[] cla) {
        
           
        for(int i =0; i < cla.length;i++){
             argValues.add(cla[i]);
        } 
        checkForHelp(cla, argValues, argNames, programName, programDescription);
        checkForTooFewArgs(cla, argNames, programName);
		checkForTooManyArgs(cla, argNames, programName);
        
        
    }
    
    public String getArg(String name){
        for(int i =0;i < getNumOfNameArgs();i++){
            if(name.equals(argNames.get(i))){
                return argValues.get(i);
            }
        }
        return "";
       
        
    }
    
    
}