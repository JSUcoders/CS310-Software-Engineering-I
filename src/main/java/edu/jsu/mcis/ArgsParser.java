package edu.jsu.mcis;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

public class ArgsParser{

    private List<Argument> arguments;
	private List<OptionalArgument> optionalArguments;
    private String programName;
    private String programDescription;
	private List<String> unknownArgNames;
	private List<String> unknownArgVals;
    private Map<String, String> longShortArgNames;
    private String XMLData;
	public ArgsParser(){
        arguments = new ArrayList<Argument>();
		optionalArguments = new ArrayList<OptionalArgument>();
        programName = "";
        programDescription = "";
		unknownArgNames = new ArrayList<String>();
		unknownArgVals = new ArrayList<String>();

		longShortArgNames = new HashMap<String, String>(); 
        XMLData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ "<program>\n";
            

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
    public void setProgramDescription(String d){
      XMLData += "<description>" + d + "</description>\n" + "<arguments>\n"; 
      programDescription = d; 
	  XMLData += "<description>" + d + "</description>\n" + "<arguments>\n";
    }
    public String getProgramDescription(){
        return programDescription;
		
    }
    public void setProgramName(String s){
        XMLData += "<name>" + s + "</name>\n";
        programName = s;
		XMLData += "<name>" + s + "</name>\n" + "<arguments>\n";
    }
    public String getProgramName(){
        return programName;
    }
    public int getNumOfArguments(){
       return arguments.size(); 
    }
    public Argument.DataType getDataType(String name){
        if(name.contains("--")){
            List<String> tempOptNames = new ArrayList<String>();
            for(int i = 0; i < optionalArguments.size(); i++){
                tempOptNames.add(optionalArguments.get(i).getName());
            }
            if(tempOptNames.contains(name)){
                return optionalArguments.get(tempOptNames.indexOf(name)).getType();
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
        else{
            List<String> tempNames = new ArrayList<String>();
            for(int i = 0; i < getNumOfArguments(); i++){
                tempNames.add(arguments.get(i).getName());
            }
            if(tempNames.contains(name)){
                return arguments.get(tempNames.indexOf(name)).getType();
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
        
	}
	public void addArg(String name,String descriptionOrDefaultValue, Argument.DataType t){
        if(name.contains("--")){
           OptionalArgument o = new OptionalArgument(name, "", descriptionOrDefaultValue, t,"");
           optionalArguments.add(o); 
        }
        else{
            Argument a = new Argument(name, descriptionOrDefaultValue, t);
            arguments.add(a);
        }
        

	}
	public void addArg(String name){
        addArg(name, "", Argument.DataType.STRING);
    }
	public void addArg(String name, String descriptionOrDefaultValue){
        if(name.contains("--")){
            OptionalArgument o = new OptionalArgument(name, "", descriptionOrDefaultValue, Argument.DataType.STRING,"");
            optionalArguments.add(o);
			XMLData += "<namedArgument>\n" + 
						"<name>" + name + "</name>\n" + 
						"<type>" + Argument.DataType.STRING + "</type>\n" + 
						"<description>" + description + "</description>\n</namedArgument>\n";
        
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
		optionalArguments.add(o);
		XMLData += "<namedArgument>\n" + 
					"<name>" + name + "</name>\n" +
					"<type>" + t + "</type>\n" + 
					"<description>" + description + "</description>\n" +
					"<default>" + value + "</default>\n</namedArgument>\n";
        
	}

	public void addArg(String name, String defaultValue, String shortNameOrDescription){
        if(shortNameOrDescription.length() == 2){
            OptionalArgument o = new OptionalArgument(name, "", defaultValue,Argument.DataType.STRING ,shortNameOrDescription);
            optionalArguments.add(o);
            longShortArgNames.put(shortNameOrDescription,name);
        }
        else{
            OptionalArgument o = new OptionalArgument(name, shortNameOrDescription, defaultValue,Argument.DataType.STRING, "");
            optionalArguments.add(o);
        }
		
		

	} 
    public void addArg(String name, String description, String defaultValue, String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,Argument.DataType.STRING ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
    public void addArg(String name, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, "", defaultValue,t ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
    public void addArg(String name,String description, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,t ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
    private void checkForTooManyArgs(String [] cla){
       if(cla.length > (arguments.size() + optionalArguments.size()*2 + unknownArgNames.size() + unknownArgVals.size())){
           throw new TooManyArgsException(makePreMessage(), cla, arguments, programName, optionalArguments, unknownArgNames, unknownArgVals);

       }
       else{
           List<String> tempOptNames = new ArrayList<String>();
            for(int i = 0; i < optionalArguments.size(); i++){
                tempOptNames.add(optionalArguments.get(i).getName());
            }
           List<String> tempOptValues = new ArrayList<String>();
            for(int i = 0; i < optionalArguments.size();i++ ){
                tempOptValues.add(optionalArguments.get(i).getValue());
            }      
           List<String> args = new ArrayList<String>();
           if(cla.length > arguments.size()){
               for(int i =0; i < cla.length; i++){
                   int shortnamesUsed=0;
					if(cla[i].contains("-")){
						if (cla[i].charAt(1)!=('-')){ 
							for(int j=1;j<cla[i].length();j++){
								shortnamesUsed++;
								args.add(("-"+ cla[i].substring(j,j+1)).toString());
								if(tempOptNames.contains(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
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
                   if(tempOptNames.contains(args.get(i)) ){
                       args.remove(args.get(i));
                   }
                   if(tempOptValues.contains(args.get(i))){
                       args.remove(args.get(i));
                   }

               }
			   
			   for(int i = 0; i < args.size(); i++){
					if(unknownArgNames.contains(args.get(i))){
						args.remove(args.get(i));
					}
					if(unknownArgVals.contains(args.get(i))){
						args.remove(args.get(i));
					}
			   }

               
               for(int i =0; i < arguments.size();i++){
                   if(args.contains(arguments.get(i).getValue())){
                       args.remove(arguments.get(i).getValue());
                   }

               }
               
               if(args.size() > 0){
                   
                   throw new TooManyArgsException(makePreMessage(), cla, arguments, programName,optionalArguments, unknownArgNames, unknownArgVals);

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
            for(int i =0; i < optionalArguments.size();i++){
                if(optionalArguments.get(i).getName().equals("--help") && optionalArguments.get(i).getValue().equals("true")){
                    throw new HelpException(makePreMessage(), programDescription, arguments); 
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
	
	private void checkForUnknownArg(){
		if(unknownArgNames.size() > 0){
			throw new UnknownArgumentException(makePreMessage(), programName, unknownArgNames);
		}
	}
	
    public void parse(String[] cla) {
        if(cla.length == 0){
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);
        } 
        List<String> tempNames = new ArrayList<String>();
        for(int i =0; i < optionalArguments.size();i++){
            tempNames.add(optionalArguments.get(i).getName());
        }
        for(int i = 0; i < cla.length;i++){
            if(tempNames.contains(cla[i])){
                if(optionalArguments.get(tempNames.indexOf(cla[i])).getValue() == "false"){
                    optionalArguments.get(tempNames.indexOf(cla[i])).setValue("true");
                }
                else{
                    optionalArguments.get(tempNames.indexOf(cla[i])).setValue(cla[i + 1]);
                    i++;
                }
			}
            else if(cla[i].contains("--") && !tempNames.contains(cla[i])){
				if(cla[i] == "--help" || cla[i] == "--HELP" || cla[i] == "--Help"){
					throw new HelpException(makePreMessage(), programDescription, arguments); 
				}
				else{
					unknownArgNames.add(cla[i]);
					unknownArgVals.add(cla[i + 1]);
					i++;
				}
            }
			
			else if(cla[i].contains("-") && (cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){ 
				int shortnamesUsed=0;
				for(int j=1;j<cla[i].length();j++){					
					if (cla[i].equals("-h")){
						throw new HelpException(makePreMessage(), programDescription, arguments); 
					}					
					else if(cla[i].substring(j,j+1) != "-"){
						shortnamesUsed++;	 
						if(tempNames.contains(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())){
							optionalArguments.get(tempNames.indexOf(longShortArgNames.get("-"+ cla[i].substring(j,j+1)).toString())).setValue(cla[i + shortnamesUsed]);
						}
					} 
				}
				i= i + shortnamesUsed;
			}
		}
        List<String> tempValues = new ArrayList<String>();
        for(int i = 0; i < optionalArguments.size();i++ ){
            tempValues.add(optionalArguments.get(i).getValue());
        }    
        int j = 0;
        for(int i = 0; i < cla.length; i ++){
            if(tempNames.contains(cla[i]) || tempValues.contains(cla[i]) ||
			unknownArgNames.contains(cla[i])|| unknownArgVals.contains(cla[i]) ||(cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){ 
            
            }
            else{
                if(j < arguments.size()){ 
                    arguments.get(j).addValue(cla[i]);
                     j++;
                }                            
            }
        }
        checkForTooManyArgs(cla);
		checkForUnknownArg();
        checkForHelp(cla);    
        checkForTooFewArgs(cla);
        checkForInvalidArgument();
    }		
    public Object getArg(String name){
        if(name.contains("--")){
            List<String> tempOptNames = new ArrayList<String>();
            for(int i =0; i < optionalArguments.size();i++){
                tempOptNames.add(optionalArguments.get(i).getName());
            }
            if(tempOptNames.contains(name)){
                if(optionalArguments.get(tempOptNames.indexOf(name)).getType() == Argument.DataType.INT){
                    return Integer.parseInt(optionalArguments.get(tempOptNames.indexOf(name)).getValue());
                }
                else if(optionalArguments.get(tempOptNames.indexOf(name)).getType() == Argument.DataType.FLOAT){
                    return Float.parseFloat(optionalArguments.get(tempOptNames.indexOf(name)).getValue());
                }
                else if(optionalArguments.get(tempOptNames.indexOf(name)).getType() == Argument.DataType.BOOL){
                    return Boolean.parseBoolean(optionalArguments.get(tempOptNames.indexOf(name)).getValue());
                }
                else{
                    return (String)optionalArguments.get(tempOptNames.indexOf(name)).getValue();
                }
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
        else{
            List<String> tempNames = new ArrayList<String>();
            for(int i=0; i < getNumOfArguments(); i++){
                tempNames.add(arguments.get(i).getName());
            }
        
            if(tempNames.contains(name)){
                if(arguments.get(tempNames.indexOf(name)).getType() == Argument.DataType.INT){
                    return Integer.parseInt(arguments.get(tempNames.indexOf(name)).getValue());
                }
                else if(arguments.get(tempNames.indexOf(name)).getType() == Argument.DataType.FLOAT){
                    return Float.parseFloat(arguments.get(tempNames.indexOf(name)).getValue());
                }
                else if(arguments.get(tempNames.indexOf(name)).getType() == Argument.DataType.BOOL){
                    return Boolean.parseBoolean(arguments.get(tempNames.indexOf(name)).getValue());
                }
                else{
                    return (String)arguments.get(tempNames.indexOf(name)).getValue();
                }
            
            }
            else{
                throw new ThatArgumentDoesNotExistException(name, optionalArguments, arguments);
            }
        }
          
    }
	public void saveXML(String filepath){
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