package edu.jsu.mcis;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

public class ArgsParser{
    private List<String> argNames;
    private List<String> optionalArgNames;
    private Map<String, Argument> arguments;
	private Map<String, OptionalArgument> optionalArguments;
    private String programName;
    private String programDescription;
    private Map<String, String> unknownArguments;
    private Map<String, String> longShortArgNames;
    private String XMLData;
	public ArgsParser(){
        argNames = new ArrayList<String>();
        optionalArgNames = new ArrayList<String>();
        unknownArguments = new HashMap<String,String>();
        arguments = new HashMap<String, Argument>();
		optionalArguments = new HashMap<String, OptionalArgument>();
        programName = "";
        programDescription = "";
		longShortArgNames = new HashMap<String, String>(); 
        XMLData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ "<program>\n";
            

    }
   
    private String makePreMessage(){
        String names = "";
        for(String key : arguments.keySet()){
            names += key + " ";
        }
        String namesSub = names.substring(0, names.length() - 1); 
        String preMessage = "usage: java "+ programName+" "+ namesSub; 
        return preMessage;   
    }	
    public void setProgramDescription(String d){
      XMLData += "<description>" + d + "</description>\n" + "<arguments>\n"; 
      programDescription = d; 
	  
    }
    public String getProgramDescription(){
        return programDescription;
		
    }
    public void setProgramName(String s){
        programName = s;
		XMLData += "<name>" + s + "</name>\n" ;
    }
    public String getProgramName(){
        return programName;
    }
    public int getNumOfArguments(){
       return arguments.size(); 
    }
    public Argument.DataType getDataType(String name){
        try{
            if(name.contains("--")){
                return optionalArguments.get(name).getType(); 
            
            }
            else{
                return arguments.get(name).getType();
            }
        }catch(NullPointerException e){throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);}
        
        
	}
	public void addArg(String name,String descriptionOrDefaultValue, Argument.DataType t){
        if(name.contains("--")){
           OptionalArgument o = new OptionalArgument(name, "", descriptionOrDefaultValue, t,"");
           optionalArgNames.add(name);
           optionalArguments.put(name, o); 
        }
        else{
            Argument a = new Argument(name, descriptionOrDefaultValue, t);
            argNames.add(name);
            arguments.put(name, a);
        }
        

	}
	public void addArg(String name){
        addArg(name, "", Argument.DataType.STRING);
    }
	public void addArg(String name, String descriptionOrDefaultValue){
        if(name.contains("--")){
            addArg(name, descriptionOrDefaultValue, Argument.DataType.STRING);
		}
        else{
             addArg(name, descriptionOrDefaultValue, Argument.DataType.STRING);
        }
       
    }
	public void addArg(String name,Argument.DataType t ){
		addArg(name, "", t);
	}
    public void addArg(String name,String description,String value, Argument.DataType t){
		OptionalArgument o = new OptionalArgument(name,description,value, t,"");
        optionalArgNames.add(name);
		optionalArguments.put(name, o);
	}

	public void addArg(String name, String defaultValue, String shortNameOrDescription){
        if(shortNameOrDescription.length() == 2){
            OptionalArgument o = new OptionalArgument(name, "", defaultValue,Argument.DataType.STRING ,shortNameOrDescription);
            optionalArgNames.add(name);
            optionalArguments.put(name, o);
            longShortArgNames.put(shortNameOrDescription,name);
        }
        else{
            OptionalArgument o = new OptionalArgument(name, shortNameOrDescription, defaultValue,Argument.DataType.STRING, "");
            optionalArgNames.add(name);
            optionalArguments.put(name,o);
        }
	}
	
    public void addArg(String name, String description, String defaultValue, String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,Argument.DataType.STRING ,shortName);
        optionalArgNames.add(name);
        optionalArguments.put(name, o);
        longShortArgNames.put(shortName,name);
    }
    
    public void addArg(String name, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, "", defaultValue,t ,shortName);
        optionalArgNames.add(name);
        optionalArguments.put(name,o);
        longShortArgNames.put(shortName,name);
    }
    
    public void addArg(String name,String description, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,t ,shortName);
        optionalArguments.put(name, o);
        longShortArgNames.put(shortName,name);
    }
	
	public void setRestricted(String name, List<String> restrictedValue){
		if(name.contains("--")){
			optionalArguments.get(name).restrictedValues = restrictedValue;
		}
		else{
			arguments.get(name).restrictedValues = restrictedValue;
		}
	}
    
    private void checkForTooManyArgs(String [] cla){
       if(cla.length > (arguments.size() + optionalArguments.size()*2 + unknownArguments.size()*2)){
           throw new TooManyArgsException(makePreMessage(), cla, arguments, programName, optionalArguments, unknownArguments);

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
								if(optionalArguments.containsKey(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
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
			   		   
               for(int i =0; i < args.size();i++){
                   if(optionalArguments.containsKey(args.get(i)) ){
                       args.remove(args.get(i));
                   }
                   for(OptionalArgument a : optionalArguments.values()){
                       if(args.contains(a.getValue())){
                           args.remove(a.getValue());
                       }
                   }
                   

               }
			   
			   for(int i = 0; i < args.size(); i++){
					if(unknownArguments.containsKey(args.get(i))){
						args.remove(args.get(i));
					}
                    if(unknownArguments.containsValue(args.get(i))){
                        args.remove(args.get(i));
                    }
			   }

               for(Argument a : arguments.values()){
                       if(args.contains(a.getValue())){
                           args.remove(a.getValue());
                       }
                   }
               
               
               if(args.size() > 0){
                   
                   throw new TooManyArgsException(makePreMessage(), cla, arguments, programName,optionalArguments, unknownArguments);

               }
           }
       }
    }   
    private void checkForTooFewArgs(String[] cla)  {   
        if(cla.length < arguments.size()){          
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);            
        }       
    }   
    private void checkForHelp(String[] cla){      
            for(int i =0; i < cla.length;i++){
                if(cla[i].equals("-h") || cla[i].toLowerCase().equals("--help")){
                    throw new HelpException(makePreMessage(), programDescription, arguments);
                }
            }    
    }	
	private void checkForInvalidArgument( ){
        int i =0; 
        String argKey = "";
        try{
            for(String key : arguments.keySet()){
                argKey = key;
                if( arguments.get(key).getType() == Argument.DataType.INT){
                    int a = Integer.parseInt(arguments.get(key).getValue());
                }
                else if(arguments.get(key).getType() == Argument.DataType.FLOAT){
                    float f = Float.parseFloat(arguments.get(key).getValue());
                }               
            }            
        }
        catch(NumberFormatException n){
            throw new InvalidArgumentException(makePreMessage(),programName, arguments.get(argKey));
        } 
        
		
	}
	
	private void checkForUnknownArg(){
		if(unknownArguments.size() > 0){
			throw new UnknownArgumentException(makePreMessage(), programName, unknownArguments);
		}
	}
    
    private void setTheValuesOfOptionalArguments(String [] cla, int i){
        if(optionalArguments.get(cla[i]).getValue() == "false"){
                optionalArguments.get(cla[i]).setValue("true");
        }
        else{
			if(!optionalArguments.get(cla[i]).restrictedValues.contains(cla[i + 1]) && optionalArguments.get(cla[i]).restrictedValues.size() != 0){
				String invalidValue = cla[i + 1];
				throw new RestrictedArgumentException(makePreMessage(), programName, invalidValue, optionalArguments.get(cla[i]));
			}
                optionalArguments.get(cla[i]).setValue(cla[i + 1]);
                i++;
            }
    }
    
    private void setTheValuesOfOptionalArgumentsThatUseShortNamesOnTheCommandLine(String [] cla, int i){
        int shortnamesUsed=0;
            for(int j=1;j<cla[i].length();j++){					
										
                if(cla[i].substring(j,j+1) != "-"){
                    shortnamesUsed++;	 
                    if(optionalArguments.containsKey(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
                        optionalArguments.get(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString()).setValue(cla[i + shortnamesUsed]);
                    }
                } 
            }
            i= i + shortnamesUsed;
    }
	
    public void parse(String[] cla) {
        checkForHelp(cla);
        checkForTooFewArgs(cla);
        for(int i = 0; i < cla.length;i++){
            if(optionalArguments.containsKey(cla[i])){
                setTheValuesOfOptionalArguments(cla, i);
			}
            else if(cla[i].contains("--") && !optionalArguments.containsKey(cla[i])){
				unknownArguments.put(cla[i], cla[i+1]);
			    i++;	
            }
			
			else if(cla[i].contains("-") && (cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){ 
				setTheValuesOfOptionalArgumentsThatUseShortNamesOnTheCommandLine( cla,  i);
			}
		}
        List<String> tempOptValues = new ArrayList<String>();
        for(OptionalArgument o : optionalArguments.values()){
            tempOptValues.add(o.getValue());
        }    
        
        int j = 0;
        for(int i = 0; i < cla.length; i ++){
            if(optionalArguments.containsKey(cla[i]) || tempOptValues.contains(cla[i]) ||
			unknownArguments.containsKey(cla[i])|| unknownArguments.containsValue(cla[i]) ||(cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){}
            else{
                 if(j < arguments.size()){
					if(!arguments.get(argNames.get(j)).restrictedValues.contains(cla[i]) && arguments.get(argNames.get(j)).restrictedValues.size() != 0){
						String invalidValue = cla[i];
						throw new RestrictedArgumentException(makePreMessage(), programName, invalidValue, arguments.get(argNames.get(j)));
					}
					else{
						arguments.get(argNames.get(j)).addValue(cla[i]); 
						j++;  
					}
				 }
                 
            }
        }
        checkForExceptions(cla);
    }

    private void checkForExceptions(String [] cla){
        checkForTooManyArgs(cla);
		checkForUnknownArg();  
        checkForTooFewArgs(cla);
        checkForInvalidArgument();
    }    
    
    public Object getArg(String name){
        if(name.contains("--")){
            
            if(optionalArguments.containsKey(name)){
                if(optionalArguments.get(name).getType() == Argument.DataType.INT){
                    return Integer.parseInt(optionalArguments.get(name).getValue());
                }
                else if(optionalArguments.get(name).getType() == Argument.DataType.FLOAT){
                    return Float.parseFloat(optionalArguments.get(name).getValue());
                }
                else if(optionalArguments.get(name).getType() == Argument.DataType.BOOL){
                    return Boolean.parseBoolean(optionalArguments.get(name).getValue());
                }
                else{
                    return (String)optionalArguments.get(name).getValue();
                }
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
        else{
        
            if(arguments.containsKey(name)){
                if(arguments.get(name).getType() == Argument.DataType.INT){
                    return Integer.parseInt(arguments.get(name).getValue());
                }
                else if(arguments.get(name).getType() == Argument.DataType.FLOAT){
                    return Float.parseFloat(arguments.get(name).getValue());
                }
                else if(arguments.get(name).getType() == Argument.DataType.BOOL){
                    return Boolean.parseBoolean(arguments.get(name).getValue());
                }
                else{
                    return (String)arguments.get(name).getValue();
                }
            
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
          
    }
	public void saveXML(String filepath){
            int i = 1;
            for(String key : arguments.keySet()){
                arguments.get(key).setPosition(i);
                XMLData += arguments.get(key).getXMLDataForArg();
                i++;
            }
            for(String key : optionalArguments.keySet()){
                XMLData += optionalArguments.get(key).getXMLDataForOptArg();
                
            }
		XMLData+= "</arguments>\n</program>";
		File outfile = new File(filepath);	
		try{
            Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(outfile), "utf-8")); 
            
            writer.write(XMLData);
            writer.close();
		}
		catch(IOException e){
			throw new RuntimeException("Issue in saveXML()");
		}
		
		
	}
    
}