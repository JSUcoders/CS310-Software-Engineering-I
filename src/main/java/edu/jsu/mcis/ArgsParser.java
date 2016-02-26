package edu.jsu.mcis;
import java.util.*;

public class ArgsParser{
    
	protected enum DataType{INT, FLOAT, BOOL, STRING, SHORT, LONG, BYTE, DOUBLE, CHAR};
	
    private List<String> argValues;
    private List<String> argNames;
	private List<DataType> argDataType;
    private String programName;
    private String programDescription;
    private String[] argDescriptions;
    public ArgsParser(){
        argValues = new ArrayList<String>();
        argNames = new ArrayList<String>();
		argDataType = new ArrayList<DataType>();
        programName = "";
        programDescription = "";
        
    }
    
    private String makePreMessage(){
       
        String names = "";
        for(int i =0; i < argNames.size();i++){
            names += argNames.get(i) + " ";
        }
        String namesSub = names.substring(0, names.length() - 1); 
        String preMessage = "usage: java "+ programName+" "+ namesSub; 
        return preMessage;
            
        
    }
    public void addArgDescriptions(String[] positionalArgs){
        argDescriptions = new String[argNames.size()];
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
        return argValues.size();
    }
    
    public int getNumOfNameArgs(){
        return argNames.size();
    }
    
    public void addArg(String name){
        argNames.add(name);
		argDataType.add(DataType.STRING);
    }
	
	public void addArg(String name, Class<?> t){
		argNames.add(name);
		if(t == int.class){
			argDataType.add(DataType.INT);
		}
		else if(t == float.class){
			argDataType.add(DataType.FLOAT);
		}
		else if(t == boolean.class){
			argDataType.add(DataType.BOOL);
		}
		else if(t == String.class){
			argDataType.add(DataType.STRING);
		}
        else if(t == double.class){
            argDataType.add(DataType.DOUBLE);
        }
        else if(t == short.class){
            argDataType.add(DataType.SHORT);
        }
        else if(t == byte.class){
            argDataType.add(DataType.BYTE);
        }
        else if(t == long.class){
            argDataType.add(DataType.LONG);
        }
        else if(t == char.class){
            argDataType.add(DataType.CHAR);
        }
        
		
	}
    
    private void checkForTooFewArgs(String[] cla, List<String> argNms)  {
    
        if(cla.length < argNames.size()){
            
            throw new TooFewArgsException(makePreMessage(), cla, argNms, programName);
            
        }
        
    }
    

	private void checkForTooManyArgs(String[] cla, List<String> argNms) {
        
		if(cla.length > argNames.size()){
		
			throw new TooManyArgsException(makePreMessage(),cla, argNms, programName);

		
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
            for(; i < argValues.size();i++){
                if(argDataType.get(i) == DataType.INT){
                    int a = Integer.parseInt(argValues.get(i));
                }
                else if(argDataType.get(i) == DataType.FLOAT){
                    float f = Float.parseFloat(argValues.get(i));
                }
                
            }
            
        }
        catch(NumberFormatException n){
            throw new InvalidArgumentException(makePreMessage(),programName, argValues.get(i), argNames.get(i), getDataType(argNames.get(i)));
        } 
        for(int j =0; j < argValues.size(); j++){
            if(argDataType.get(j)==DataType.LONG){
                throw new InvalidArgumentException(makePreMessage(), programName, argValues.get(j), argNames.get(j), getDataType(argNames.get(j)));
            }
            else if(argDataType.get(j)==DataType.BYTE){
                throw new InvalidArgumentException(makePreMessage(), programName, argValues.get(j), argNames.get(j), getDataType(argNames.get(j)));
            }
            else if(argDataType.get(j)==DataType.SHORT){
                throw new InvalidArgumentException(makePreMessage(), programName, argValues.get(j), argNames.get(j), getDataType(argNames.get(j)));
            }
            else if(argDataType.get(j)==DataType.DOUBLE){
                throw new InvalidArgumentException(makePreMessage(), programName, argValues.get(j), argNames.get(j), getDataType(argNames.get(j)));
            }
            else if(argDataType.get(j)==DataType.CHAR){
                throw new InvalidArgumentException(makePreMessage(), programName, argValues.get(j), argNames.get(j), getDataType(argNames.get(j)));
            }
            
            
            
        }
		
	}
    
    public void parse(String[] cla) {
        
        if(cla.length == 0){
            throw new TooFewArgsException(makePreMessage(), cla, argNames, programName);
        }   
        for(int i =0; i < cla.length;i++){
             argValues.add(cla[i]);
        } 
        
        checkForHelp(cla, programDescription, argDescriptions);
        checkForTooFewArgs(cla, argNames);
		checkForTooManyArgs(cla, argNames);
        checkForInvalidArgument( );
        
    }
	
	
    public Object getArg(String name){
        for(int i =0;i < getNumOfNameArgs();i++){
            if(name.equals(argNames.get(i))){
                if(argDataType.get(i) == DataType.INT){
					return Integer.parseInt(argValues.get(i));
				}
				
				else if(argDataType.get(i) == DataType.FLOAT){
					return Float.parseFloat(argValues.get(i));
				}
				
				else if(argDataType.get(i) == DataType.BOOL){
					return Boolean.parseBoolean(argValues.get(i));
				}
				
				else{
					return (String)argValues.get(i);
				}
            }
        }
        return "";
       
        
    }
	
	public DataType getDataType(String name){
		for(int i = 0; i < getNumOfNameArgs();i++){
			if(name.equals(argNames.get(i))){
				return argDataType.get(i);
			}
		}
		return DataType.STRING;
	}
    
    
}