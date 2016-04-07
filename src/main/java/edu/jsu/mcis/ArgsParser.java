package edu.jsu.mcis;
import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

/**<p>
 * This program allows the user to add arguments and enter data values into them from the command line.
 * this is an example of how to use this class 
 * in the example the string array args would be replaced with the comandline arguments taken form the user
 *<p>
 * 		&nbsp;&nbsp;&nbsp;&nbsp; private ArgsParser p = new ArgsParser();
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; String[] args = new String[] {"7","--digits","10"};
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; p.setProgramName("VolumeCalculator");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; p.setProgramDescription("Calculate the volume of a box");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; p.addArg("--digits","4");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp; p.parseValues(args);
 *<p>
 * after the block of code excutes the value stored in the argument length would be  "7" and the value of digits would be 10.
 * to obtain the values from this class use the getValue function like so 
 *<p>
 * 		&nbsp;&nbsp;&nbsp;&nbsp; p.getArg("length")
 *<p>
 * this would return a 7 if using the above example
 * the user can also get the data type of any given argument by using getArgumentType
 * for length it would return string use like so
 *<p>
 *		&nbsp;&nbsp;&nbsp;&nbsp; ap.getDataType("length")
 *
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class ArgsParser{

    private List<Argument> arguments;
	private List<OptionalArgument> optionalArguments;
    private String programName;
    private String programDescription;
	private List<String> unknownArgNames;
	private List<String> unknownArgVals;
    private Map<String, String> longShortArgNames;
    private String XMLData;
	
	 /**
	 *Class constructor. 
	 */
	
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
   
   
	 /**
	 *This method creates a pre-message that is used with certain exceptions.
	 *
	 *@returns preMessage text
	 */
	 
    private String makePreMessage(){
        String names = "";
        for(int i =0; i < arguments.size();i++){
            names += arguments.get(i).getName() + " ";
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
	 *@returns programDescription
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
	 *@returns programName
	 */
	
    public String getProgramName(){
        return programName;
    }
	
	 /**
	 *This method returns the number of arguments involved with this program.
	 *
	 *@returns The number of arguments in this list.
	 */
	
    public int getNumOfArguments(){
       return arguments.size(); 
    }
	
	 /**
	 *This method returns the data type for an argument value.
	 *
	 *@param name The name of this argument.
	 *@returns the data type associated with this argument's value if it is an optional argument.
	 *@returns the data type associated with this argument's value if it is a primary argument.
	 *@throws ThatArgumentDoesNotExistException
	 
	 */
	
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
	
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param name   
	 *@param descriptionOrDefaultValue 
	 *@param t <Data Type identifier 
	 */
	
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
	
	 /**
	 *This method adds an argument to this program whoes data type will be automatically set to a string value.
	 *
	 *@param name   
	 */
	
	public void addArg(String name){
        addArg(name, "", Argument.DataType.STRING);
    }
	
	 /**
	 *This method adds an argument to this program whoes data type will be automatically set to a string value.
	 *
	 *@param name 
	 *@param descriptionOrDefaultValue
	 */
	
	public void addArg(String name, String descriptionOrDefaultValue){
        if(name.contains("--")){
            OptionalArgument o = new OptionalArgument(name, "", descriptionOrDefaultValue, Argument.DataType.STRING,"");
            optionalArguments.add(o);
			
        
			}
        else{
             addArg(name, descriptionOrDefaultValue, Argument.DataType.STRING);
        }
       
    }
	
	 /**
	 *This method adds an argument to this program with a specified data type.
	 *
	 *@param name 
	 *@param t Data type identifier
	 */
	
	public void addArg(String name,Argument.DataType t ){
		addArg(name, "", t);
	}
	
	 /**
	 *This method adds an argument to this program with a specified description, value, and data type.
	 *
	 *@param name 
	 *@param description
	 *@param value
	 *@param t Data type identifier
	 */
	
    public void addArg(String name,String description,String value, Argument.DataType t){
		OptionalArgument o = new OptionalArgument(name,description,value, t,"");
		optionalArguments.add(o);
		
        
	}

	 /**
	 *This method adds an argument to this program with a specified default value, and shortname or description.
	 *@param name
	 *@param defaultValue
	 *@param shortNameOrDescription
	 */
	
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
	
	 /**
	 *This method adds an argument to this program with a specified description, defaultValue, and shortName that will have a data type value of string.
	 *
	 *@param name  
	 *@param description
	 *@param defaultValue
	 *@param shortName
	 */
	
    public void addArg(String name, String description, String defaultValue, String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,Argument.DataType.STRING ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
	 /**
	 *This method adds an argument to the program.
	 *
	 *@param name
	 *@param defaultValue
	 *@param t Data Type identifier
	 *@param shortName
	 */
	
    public void addArg(String name, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, "", defaultValue,t ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
	 /**
	 *This method adds an argument to the program with a specified description, default value, data type, and shortname.
	 *
	 *@param name
	 *@param description
	 *@param defaultValue
	 *@param t Data type identifier
	 *@param shortName
	 */
	
    public void addArg(String name,String description, String defaultValue,Argument.DataType t,String shortName){
        OptionalArgument o = new OptionalArgument(name, description, defaultValue,t ,shortName);
        optionalArguments.add(o);
        longShortArgNames.put(shortName,name);
    }
    
	 /**
	 *This method checks the arguments list to ensure the command line arguments supplied do not exceed the proper amount.
	 *
	 *@param cla Command line argument  
	 *@throws TooManyArgsException
	 */
	
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

	/**
	 *This method checks the arguments list to ensure the command line arguments supplied are not less than the proper amount.
	 *
	 *@param cla Command line argument  
	 *@throws TooFewArgsException
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
	 *@throws HelpException
	 */
	
    private void checkForHelp(String[] cla){      
            for(int i =0; i < optionalArguments.size();i++){
                if(optionalArguments.get(i).getName().equals("--help") && optionalArguments.get(i).getValue().equals("true")){
                    throw new HelpException(makePreMessage(), programDescription, arguments); 
                }
            }    
    }	
	
	/**
	 *This method throws an invalid argument exception a value does not match a data type.
	 *  
	 *@throws InvalidArgumentException
	 */
	
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
	
	 /**
	 *This method checks for arguments that this program is not equiped to deal with.
	 *
	 *@throws UnknownArgumentException
	 */
	
	private void checkForUnknownArg(){
		if(unknownArgNames.size() > 0){
			throw new UnknownArgumentException(makePreMessage(), programName, unknownArgNames);
		}
	}
	
	 /**
	 *This method parses the command line arguments and inputs them into this program for logic handling.
	 *
	 *@param cla Command line arguments  
	 *@throws TooFewArgsException
	 *@throws HelpException
	 *@see #checkForTooManyArgs(String[] cla)
	 *@see #checkForUnknownArg()
	 *@see #checkForHelp(String[] cla)
	 *@see #checkForTooFewArgs(String[] cla)
	 *@see #checkForInvalidArgument()
	 */
	
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

	 /**
	 *This method returns this object's value   
	 *
	 *@param name  
	 *@throws ThatArgumentDoesNotExistException
	 *@returns This object's value
	 */
	
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
	
	 /**
	 *This method creats XML data for current arguments and saves them to the specified file.
	 *
	 *@param filepath  
	 *@throws RuntimeException	 
	 */
	
	public void saveXML(String filepath){
            for(int i =0; i < arguments.size();i++){
                arguments.get(i).setPosition(i + 1);
                XMLData += arguments.get(i).getXMLDataForArg();
                
            }
            for(int i =0; i < optionalArguments.size();i++){
                XMLData += optionalArguments.get(i).getXMLDataForOptArg();
                
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