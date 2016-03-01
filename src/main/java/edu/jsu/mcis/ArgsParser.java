package edu.jsu.mcis;
import java.util.*;

public class ArgsParser{
	
    //private List<String> argValues;
    //private List<String> argNames;
	//private List<DataType> argDataType;
    private List<Argument> arguments;
    private String programName;
    private String programDescription;
    private String[] argDescriptions;
    public ArgsParser(){
        //argValues = new ArrayList<String>();
        //argNames = new ArrayList<String>();
		//argDataType = new ArrayList<DataType>();
        arguments = new ArrayList<Argument>();
        programName = "";
        programDescription = "";
        
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
    
    /*public int getNumOfArguments(){
        return argValues.size();
    }*/
    
    /*public int getNumOfNameArgs(){
        return argNames.size();
    }*/
    
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
    

	/*private void checkForTooManyArgs(String[] cla) {
        
		if(cla.length > arguments.size()){
		
			throw new TooManyArgsException(makePreMessage(),cla, arguments, programName);

		
		}
	
	}*/
    
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
        
        if(cla.length == 0){
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);
        } 
        try{
            for(int i =0; i < cla.length;i++){
             arguments.get(i).addValue(cla[i]);
            } 
            
        }catch(IndexOutOfBoundsException e){
            throw new TooManyArgsException(makePreMessage(),cla, arguments, programName);
        }    
        
        
        checkForHelp(cla, programDescription, argDescriptions);
        checkForTooFewArgs(cla);
		//checkForTooManyArgs(cla);
        checkForInvalidArgument( );
        
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