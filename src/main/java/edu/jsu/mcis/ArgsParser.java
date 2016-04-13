package edu.jsu.mcis;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

/**<p>
 * This program allows the user to add arguments and enter data values into them from the command line.
 * this is an example of how to use this class 
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *<p>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;	private ArgsParser p = new ArgsParser();
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	String[] args = new String[] {"7","--digits","10"};
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramName("VolumeCalculator");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramDescription("Calculate the volume of a box");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--digits","4");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.parseValues(args);
 *<p>
 * after the block of code excutes the value stored in the argument length would be  "7" and the value of digits would be 10.
 * to obtain the values from this class use the getValue function like so 
 *<p>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;	p.getArg("length")
 *<p>
 * this would return a 7 if using the above example
 * the user can also get the data type of any given argument by using getArgumentType
 * for length it would return string use like so
 *<p>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.getDataType("length")
 *
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class ArgsParser{
    List<String> argNames;
    private Map<String, Argument> arguments;
	private Map<String, NamedArgument> optionalArguments;
    private String programName;
    private String programDescription;
    private Map<String, String> unknownArguments;
    private Map<String, String> longShortArgNames;
    private String XMLData;
	
	 /**
	 *Class constructor. 
	 */
	
	public ArgsParser(){
        argNames = new ArrayList<String>();
        unknownArguments = new HashMap<String,String>();
        arguments = new HashMap<String, Argument>();
		optionalArguments = new HashMap<String, NamedArgument>();
        programName = "";
        programDescription = "";
		longShortArgNames = new HashMap<String, String>(); 
        XMLData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ "<program>\n";      

    }
    
   
   
	 /**
	 *This method creates a pre-message that is used with certain exceptions.
	 *
	 *@return preMessage text
	 */
	 
    private String makePreMessage(){
        String names = "";
        for(String key : arguments.keySet()){
            names += key + " ";
        }
        String namesSub = names.substring(0, names.length() - 1); 
        String preMessage = "usage: java "+ programName+" "+ namesSub; 
        return preMessage;   
    }	
	
	 /**
	 *This method adds a description for this program that will be used.
	 *
	 *@param d Text that will state the description of the program used.   
	 */
	
    public void setProgramDescription(String d){
      XMLData += "<description>" + d + "</description>\n" + "<arguments>\n"; 
      programDescription = d; 
	  
    }
	
	 /**
	 *This method returns the description of this program.
	 *
	 *@return programDescription
	 */
	
    public String getProgramDescription(){
        return programDescription;
		
    }
	
	 /**
	 *This method adds a name to identify this program.
	 *
	 *@param s Name of the program.  
	 */
	
    public void setProgramName(String s){
        programName = s;
		XMLData += "<name>" + s + "</name>\n" ;
    }
	
	 /**
	 *This method returns the name of this program.
	 *
	 *@return programName
	 */
	
    public String getProgramName(){
        return programName;
    }
	
	 /**
	 *This method returns the number of arguments involved with this program.
	 *
	 *@return arguments.size() The number of arguments in this list.
	 */
	
    public int getNumOfArguments(){
       return arguments.size(); 
    }
	
	 /**
	 *This method returns the data type for an argument value.
	 *
	 *@param name The name of this argument.
	 *@return optionalArguments.get(tempOptNames.indexOf(name)).getType() the data type associated with this argument's value if it is an optional argument.
	 *@return arguments.get(tempNames.indexOf(name)).getType() the data type associated with this argument's value if it is a primary argument.
	 *@throws ThatArgumentDoesNotExistException Exception that is thrown when the specified arguement does not exsist
	 
	 */
	
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
	
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param name The name of this argument  
	 *@param descriptionOrDefaultValue The description or default value of this argument
	 *@param t Data Type identifier 
	 */
	
	public void addArg(String name,String descriptionOrDefaultValue, Argument.DataType t){
        if(name.contains("--")){
           NamedArgument o = new NamedArgument(name, "", descriptionOrDefaultValue, t,"");
           optionalArguments.put(name, o); 
        }
        else{
            Argument a = new Argument(name, descriptionOrDefaultValue, t);
            argNames.add(name);
            arguments.put(name, a);
        }
        

	}
	
	 /**
	 *This method adds an argument to this program whoes data type will be automatically set to a string value.
	 *
	 *@param name name of this argument  
	 */
	
	public void addArg(String name){
        addArg(name, "", Argument.DataType.STRING);
    }
	
	 /**
	 *This method adds an argument to this program whoes data type will be automatically set to a string value.
	 *
	 *@param name Name of this argument		
	 *@param descriptionOrDefaultValue The description or default value of this argument 
	 */
	
	public void addArg(String name, String descriptionOrDefaultValue){
        if(name.contains("--")){
            addArg(name, descriptionOrDefaultValue, Argument.DataType.STRING);
		}
        else{
             addArg(name, descriptionOrDefaultValue, Argument.DataType.STRING);
        }
       
    }
	
	 /**
	 *This method adds an argument to this program with a specified data type.
	 *
	 *@param name The name of this argument
	 *@param t Data type identifier
	 */
	
	public void addArg(String name,Argument.DataType t ){
		addArg(name, "", t);
	}
	
	 /**
	 *This method adds an argument to this program with a specified description, value, and data type.
	 *
	 *@param name The name of this arguement 	
	 *@param description The description of this argument
	 *@param value The value of this argument
	 *@param t Data type identifier
	 */
	
    public void addArg(String name,String description,String value, Argument.DataType t){
		NamedArgument o = new NamedArgument(name,description,value, t,"");
		optionalArguments.put(name, o);
	}

	 /**
	 *This method adds an argument to this program with a specified default value, and shortname or description.
	 *@param name The name of this argument
	 *@param defaultValue The default value of this argument
	 *@param shortNameOrDescription the shortName or description of argument
	 */
	
	public void addArg(String name, String defaultValue, String shortNameOrDescription){
        if(shortNameOrDescription.length() == 2){
            NamedArgument o = new NamedArgument(name, "", defaultValue,Argument.DataType.STRING ,shortNameOrDescription);
            optionalArguments.put(name, o);
            longShortArgNames.put(shortNameOrDescription,name);
        }
        else{
            NamedArgument o = new NamedArgument(name, shortNameOrDescription, defaultValue,Argument.DataType.STRING, "");
            optionalArguments.put(name,o);
        }
				
	} 
	
	 /**
	 *This method adds an argument to this program with a specified description, defaultValue, and shortName that will have a data type value of string.
	 *
	 *@param name The name of this argument
	 *@param description The description of this argument
	 *@param defaultValue The default value of this argument
	 *@param shortName The shortname of this argument 
	 */

	
    public void addArg(String name, String description, String defaultValue, String shortName){
        NamedArgument o = new NamedArgument(name, description, defaultValue,Argument.DataType.STRING ,shortName);
        optionalArguments.put(name, o);
        longShortArgNames.put(shortName,name);
    }
    
	 /**
	 *This method adds an argument to the program.
	 *
	 *@param name The name of this argument
	 *@param defaultValue The default value this argument
	 *@param t Data Type identifier 
	 *@param shortName The shortName of this argument
	 */
	
    public void addArg(String name, String defaultValue,Argument.DataType t,String shortName){
        NamedArgument o = new NamedArgument(name, "", defaultValue,t ,shortName);
        optionalArguments.put(name,o);
        longShortArgNames.put(shortName,name);
    }
    
	 /**
	 *This method adds an argument to the program with a specified description, default value, data type, and shortname.
	 *
	 *@param name Name of this arguement 
	 *@param description Description of this argument
	 *@param defaultValue Default value of this arguement 
	 *@param t Data type identifier
	 *@param shortName the shortname of this argument
	 */
	
    public void addArg(String name,String description, String defaultValue,Argument.DataType t,String shortName){
        NamedArgument o = new NamedArgument(name, description, defaultValue,t ,shortName);
        optionalArguments.put(name, o);
        longShortArgNames.put(shortName,name);
    }
	
    public void addArg(Argument arg){
        
            arguments.put(arg.getName(), arg);
            argNames.add(arg.getName());
        
    }
    
    public void addNamedArgument(NamedArgument optArg){
        optionalArguments.put(optArg.getName(), optArg);
    }
    
/* 	public void setRestricted(String name, List<String> restrictedValue){
		if(name.contains("--")){
			optionalArguments.get(name).restrictedValues = restrictedValue;
		}
		else{
			arguments.get(name).restrictedValues = restrictedValue;
		}
	} */
    
/*     public void setOptArgToRequired(String name){
        optionalArguments.get(name).setRequired();
    } */
  
    
	 /**
	 *This method checks the arguments list to ensure the command line arguments supplied do not exceed the proper amount.
	 *
	 *@param cla Command line argument  
	 *@throws TooManyArgsException Exception thrown when there are too many arguments in the command line
	 */
	
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

                   
                }
               
                for(NamedArgument a : optionalArguments.values()){
                       if(args.contains(a.getValue())){
                           args.remove(a.getValue());
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

	/**
	 *This method checks the arguments list to ensure the command line arguments supplied are not less than the proper amount.
	 *
	 *@param cla Command line argument  
	 *@throws TooFewArgsException Exception that is thrown when there are too few arguments in the command line
	 */
	
    private void checkForTooFewArgs(String[] cla)  {   
        if(cla.length < arguments.size()){          
            throw new TooFewArgsException(makePreMessage(), cla, arguments, programName);            
        }       
    }

	 /**
	 *This method throws a help exception if a help flag is invoked.
	 *
	 *@param cla Command line argument  
	 *@throws HelpException Exception that is thrown when the help option is invoked in the command line 
	 */
	
    private void checkForHelp(String[] cla){      
            for(int i =0; i < cla.length;i++){
                if(cla[i].equals("-h") || cla[i].toLowerCase().equals("--help")){
                    throw new HelpException(makePreMessage(), programDescription, arguments);
                }
            }    
    }	
	
	/**
	 *This method throws an invalid argument exception a value does not match a data type.
	 *  
	 *@throws InvalidArgumentException Exception that is thrown when the data types do not match up from an arguement object
	 */
	
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
	
	 /**
	 *This method checks for arguments that this program is not equiped to deal with.
	 *
	 *@throws UnknownArgumentException Exception that is thrown when an argument in the command line is unexpected
	 */
	
	private void checkForUnknownArg(){
		if(unknownArguments.size() > 0){
			throw new UnknownArgumentException(makePreMessage(), programName, unknownArguments);
		}
	}
    
    private void checkForRequiredArguments(String [] cla){
        List<String> tempCLA = new ArrayList<String>();
        for(int i =0; i < cla.length;i++){
            tempCLA.add(cla[i]);
        }
        for(NamedArgument o: optionalArguments.values()){
            if(o.required && !tempCLA.contains(o.getName())){
                throw new RequiredArgumentsNeededException(optionalArguments);
            }
        }
        
    }
    
    private void setTheValuesOfNamedArguments(String [] cla, int i){
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
    
    private void setTheValuesOfNamedArgumentsThatUseShortNamesOnTheCommandLine(String [] cla, int i){
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
	
	 /**
	 *This method parses the command line arguments and inputs them into this program for logic handling.
	 *
	 *@param cla Command line arguments  
	 *@throws TooFewArgsException Exception thrown when there are too few arguments in command line
	 *@throws HelpException Exception that is thrown when the help option is invoked in the command line 
	 *@see #checkForTooManyArgs(String[] cla)
	 *@see #checkForUnknownArg()
	 *@see #checkForHelp(String[] cla)
	 *@see #checkForTooFewArgs(String[] cla)
	 *@see #checkForInvalidArgument()
	 */
	
    public void parse(String[] cla) {
        checkForHelp(cla);
        checkForTooFewArgs(cla);
        for(int i = 0; i < cla.length;i++){
            if(optionalArguments.containsKey(cla[i])){
                setTheValuesOfNamedArguments(cla, i);
			}
            else if(cla[i].contains("--") && !optionalArguments.containsKey(cla[i])){
				unknownArguments.put(cla[i], cla[i+1]);
			    i++;	
            }
			
			else if(cla[i].contains("-") && (cla[i].charAt(0) == '-' && cla[i].charAt(1) != '-')){ 
				setTheValuesOfNamedArgumentsThatUseShortNamesOnTheCommandLine( cla,  i);
			}
		}
        List<String> tempOptValues = new ArrayList<String>();
        for(NamedArgument o : optionalArguments.values()){
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
        checkForRequiredArguments(cla);
        checkForTooManyArgs(cla);
		checkForUnknownArg();  
        checkForTooFewArgs(cla);
        checkForInvalidArgument();

    }	

	 /**
	 *This method returns this object's value   
	 *
	 *@param name Name of and argument  
	 *@return Integer.parseInt(optionalArguments.get(tempOptNames.indexOf(name)).getValue()) value parsed as an Integer
	 *@return Float.parseFloat(optionalArguments.get(tempOptNames.indexOf(name)).getValue()) value parsed as an Float
	 *@return Boolean.parseBoolean(optionalArguments.get(tempOptNames.indexOf(name)).getValue()) value parsed as an Boolean
	 *@return (String)optionalArguments.get(tempOptNames.indexOf(name)).getValue() value parsed as an String
	 *@throws ThatArgumentDoesNotExistException Exception thrown when...	 
	 */
	

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
	
	 /**
	 *This method creats XML data for current arguments and saves them to the specified file.
	 *
	 *@param filepath the filepath of the file containing xml data
	 *@throws RuntimeException Exception thrown when there is an issue opening the specified file.	 
	 */
	
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