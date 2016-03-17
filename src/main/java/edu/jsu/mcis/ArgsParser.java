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
    private HashMap longShortArgNames = new HashMap();
	
	
	
	
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
    
    public void addArg(String name, String def){
        if(name.contains("--")){
            optionalArgNames.add(name);
            optionalArgValues.add(def);
        }
    }
	public void addArg(String name, String def, String shortName){
		addArg(name,def);
		longShortArgNames.put(shortName,name);
	}
	
    
    private void checkForTooManyArgs(String [] cla){
       if(cla.length > (arguments.size() + optionalArgNames.size() + optionalArgValues.size())){
           throw new TooManyArgsException(makePreMessage(), cla, arguments, programName, optionalArgNames, optionalArgValues);
       }
       else{
           List<String> args = new ArrayList<String>();
           if(cla.length > arguments.size()){
               for(int i =0; i < cla.length; i++){
                   int shortnamesUsed=0;
					if(cla[i].contains("-")){
						if (cla[i].charAt(1)!=('-')){ 
							for(int j=1;j<cla[i].length();j++){
								shortnamesUsed++;
								args.add(("-"+ cla[i].substring(j,j+1)).toString());
								
								if(optionalArgNames.contains(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
									args.remove(("-"+ cla[i].substring(j,j+1)).toString());
									
								}	 
							}
						}
						else {
							args.add(cla[i]);
							
						}
					}
					else { 
						args.add(cla[i]); 
						
					}
					i= i + shortnamesUsed;
               }
			   //check for shortname longname association then remove from list
			   for(int i =0; i < args.size();i++){
                   if(longShortArgNames.containsKey(args.get(i))){
                       args.remove(args.get(i));
                   }
               }
		   
               for(int i =0; i < args.size();i++){
                   if(optionalArgNames.contains(args.get(i)) ){
                       args.remove(args.get(i));
                   }
                   if(optionalArgValues.contains(args.get(i))){
                       args.remove(args.get(i));
                   }
               }
               
               for(int i =0; i < arguments.size();i++){
                   if(args.contains(arguments.get(i).getValue())){
                       args.remove(arguments.get(i).getValue());
                   }
               }
               
               if(args.size() > 0){
                   
                   throw new TooManyArgsException(makePreMessage(), cla, arguments, programName, optionalArgNames, optionalArgValues);
               }
           }
       }
    }
    
    private void checkForTooFewArgs(String[] cla)  {
    
        if(cla.length < arguments.size()){
            
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);
            
        }
        
    }
    

	
    
    private void checkForHelp(String[] cla, String prgmDescript, String[] argDescript){
        
        
            for(int i =0; i < optionalArgNames.size();i++){
                if(optionalArgNames.get(i).equals("--help") && optionalArgValues.get(i).equals("true")){
                    throw new HelpException(makePreMessage(), prgmDescript, argDescript); 
                }
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
        for(int i = 0; i < cla.length;i++){

            if(optionalArgNames.contains(cla[i])){
                if(optionalArgValues.get(optionalArgNames.indexOf(cla[i])) == "false"){
                    optionalArgValues.set(optionalArgNames.indexOf(cla[i]), "true");
                }
                else{
                    optionalArgValues.set(optionalArgNames.indexOf(cla[i]), cla[i + 1]);
                    i++;
                }
			}	  

            else if(cla[i].contains("--")){
                optionalArgNames.add(cla[i]);
                optionalArgValues.add(cla[i+1]);
            }
			else if(cla[i].contains("-") && (cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){
				int shortnamesUsed=0;
				for(int j=1;j<cla[i].length();j++){					
					if (cla[i].equals("-h")){
						throw new HelpException(makePreMessage(), programDescription, argDescriptions); 
					}
					
					else if(cla[i].substring(j,j+1) != "-"){
						shortnamesUsed++;	 
						if(optionalArgNames.contains(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
							optionalArgValues.set(optionalArgNames.indexOf(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString()), cla[i + shortnamesUsed]);
						}
					} 
				}
				i= i + shortnamesUsed;
			}
		}
        int j = 0;
        for(int i = 0; i < cla.length; i ++){
            if(optionalArgNames.contains(cla[i]) || optionalArgValues.contains(cla[i]) || (cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){ 
            
            }

            else{
                if(j < arguments.size()){
                    arguments.get(j).addValue(cla[i]);
                     j++;
                }
                
              
            }
        }
        checkForTooManyArgs(cla);
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