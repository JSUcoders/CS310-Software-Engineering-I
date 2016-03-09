package edu.jsu.mcis;
import java.util.*;
import java.util.Arrays;

public class ArgsParser{

    private List<Argument> arguments;
    private String programName;
    private String programDescription;
    private String[] argDescriptions;
    private List<String> optionalArgValues;
    private List<String> optionalArgNames;
    public ArgsParser(){
        arguments = new ArrayList<Argument>();
        programName = "";
        programDescription = "";
        optionalArgValues = new ArrayList<String>();
        optionalArgNames = new ArrayList<String>();
    }
    
    public void print(){
        List<String> s = new ArrayList<String>();
        for(int i = 0; i < arguments.size();i++){
            s.add(arguments.get(i).getValue());
        }
        System.out.println(s.toString());
        System.out.println(optionalArgNames.toString());
        System.out.println(optionalArgValues.toString());
    }
    
    public void addDefaultOptionalArg(String name, String def){
        optionalArgNames.add(name);
        optionalArgValues.add(def);
    }
   
    
    public String getOptionalArg(String name){
       for(int i =0; i< optionalArgNames.size();i++){
           if(optionalArgNames.get(i).equals(name)){
               return optionalArgValues.get(i);
           }
       } 
       return "";
    }
    
    private String makePreMessage(){
       
        String names = "";
        for(int i =0; i < arguments.size();i++){
            names += arguments.get(i).getName() + " ";
        }
        String namesSub = names.substring(0, names.length() - 1); 
        String preMessage = "usage: java "+ programName+" "+ namesSub; 
        return preMessage;
            
        
    }
    public void addArgDescriptions(String[] positionalArgs){
        argDescriptions = new String[arguments.size()];
        for(int i =0; i < positionalArgs.length;i++){
            argDescriptions[i] = positionalArgs[i];
        }
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
       return arguments.size(); 
    }
    
    
    
    public Argument.DataType getDataType(String name){
        for(int i = 0; i < getNumOfArguments();i++){
			if(name.equals(arguments.get(i).getName())){
				return arguments.get(i).getType();
			}
		}
		return Argument.DataType.STRING;
	}
    
    public void addArg(String name){
        addArg(name, Argument.DataType.STRING);
    }
	
	public void addArg(String name, Argument.DataType t){
        Argument a = new Argument(name, t);
        arguments.add(a);
	}
    
    private void checkForTooFewArgs(String[] cla)  {
    
        if(cla.length < arguments.size()){
            
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);
            
        }
        
    }
    

	
    
    private void checkForHelp(String[] cla, String prgmDescript, String[] argDescript){
       
        if(cla[0].equals("-h") || cla[0].equals("--help")){
            throw new HelpException(makePreMessage(), prgmDescript, argDescript); 
        }
    }
	
	private void checkForInvalidArgument( ){
        int i =0; 
        try{
            for(; i < arguments.size();i++){
                if(arguments.get(i).getType() == Argument.DataType.INT){
                    int a = Integer.parseInt(arguments.get(i).getValue());
                }
                else if(arguments.get(i).getType() == Argument.DataType.FLOAT){
                    float f = Float.parseFloat(arguments.get(i).getValue());
                }
                
            }
            
        }
        catch(NumberFormatException n){
            throw new InvalidArgumentException(makePreMessage(),programName, arguments.get(i));
        } 
        
		
	}
    
    public void parse(String[] cla) {
        List<String> tempList = new ArrayList<String>(optionalArgNames);

        if(cla.length == 0){
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);
        } 
        int timesLooped = 0;
        for(int i =0; i < optionalArgNames.size();i++){
            for(int j =0; j < cla.length;j++){
                try{
                    if(cla[j].contains("--") ){
                        if(cla[j].equals(optionalArgNames.get(i))){
                            optionalArgValues.set(i, cla[j + 1]);
                            //grady: ++i?
                            //cody: j and i need to be iterated correctly?
                      
                            i++;
                        }
                    
                        else{
                            tempList.add(cla[j]);
                            optionalArgValues.add(cla[j+1]);
                        }
                    }
                    timesLooped += 1;
                }catch(IndexOutOfBoundsException e){
                    System.out.println(timesLooped);
                }
            }    
        }
        optionalArgNames = new ArrayList<String>(tempList);
        try{
           //get it to look for the long name "--" values and ignore the very next cla just like the the "I++" that resolved the double issues.
            for(int i =0; i < cla.length;i++){
                if(!optionalArgNames.contains(cla[i]) || !optionalArgValues.contains(cla[i])){
                     arguments.get(i).addValue(cla[i]);
                }
                
               
            } 
            
        }catch(IndexOutOfBoundsException e){
            
           
           if(!cla[arguments.size()].contains("--")){
                throw new TooManyArgsException(makePreMessage(), cla, arguments, programName);
           }
                
        }
            
        
        
        
        checkForHelp(cla, programDescription, argDescriptions);
        checkForTooFewArgs(cla);
        checkForInvalidArgument();
        
    }
	
	
    public Object getArg(String name){
        int j =0;
        for(int i =0;i < getNumOfArguments();i++){
            if(name.equals(arguments.get(i).getName())){
                if(arguments.get(i).getType() == Argument.DataType.INT){
					return Integer.parseInt(arguments.get(i).getValue());
				}
				
				else if(arguments.get(i).getType() == Argument.DataType.FLOAT){
					return Float.parseFloat(arguments.get(i).getValue());
				}
				
				else if(arguments.get(i).getType() == Argument.DataType.BOOL){
					return Boolean.parseBoolean(arguments.get(i).getValue());
				}
				
				else{
					return (String)arguments.get(i).getValue();
				}
            }
            j = i;
        }
        throw new InvalidArgumentException(makePreMessage(),programName, arguments.get(j));
       
        
    }
	

    
    
}