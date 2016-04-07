package edu.jsu.mcis;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.io.*;

/**<p>
 * This class allows the user to ...
 *<p>
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class SaveToXML extends DefaultHandler{
	private boolean name;
    private boolean value;
    private boolean type;
	private boolean description;
	private boolean shorthand;
	private boolean defaultValue;
	
	private StringBuilder nameStringbuild;
	private StringBuilder valueStringbuild;
	private StringBuilder typeStringbuild;
	private StringBuilder descriptionStringbuild;
	private StringBuilder shorthandStringbuild;
	private StringBuilder defaultValueStringbuild;
	
	private ArgsParser p; 
	
	private String stringName;
	private String stringDescription;
	private String stringShorthand;
	private String stringDefault;
	private Argument.DataType stringType;
	
	 /**
	 *Class constructor. 
	 */
	
	public SaveToXML(){
		
		nameStringbuild = new StringBuilder();
		valueStringbuild =  new StringBuilder();
 		typeStringbuild =  new StringBuilder();
		descriptionStringbuild =  new StringBuilder();
		shorthandStringbuild =  new StringBuilder();
 		defaultValueStringbuild =  new StringBuilder();
		
		
		name = false ; 
		value = false ; 
		type = false ; 
		description = false ; 
		
		p = new ArgsParser();
		
		stringName = "" ; 
		stringDescription = "" ; 
		stringShorthand = "" ; 
		stringDefault = "" ; 
		stringType = Argument.DataType.STRING; 
	
	}
	
	DefaultHandler handler = new DefaultHandler(){
	
		public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
			if(qName.equals("arguments")){
				p.setProgramName(stringName);
				p.setProgramDescription(stringDescription);
			}
			else if (qName.equalsIgnoreCase("name")){
				name = true;
			}
			else if (qName.equalsIgnoreCase("type")){
				type = true;
			}
			else if (qName.equalsIgnoreCase("description")){
				description = true;
			}
			else if (qName.equalsIgnoreCase("defaultValue")){
				defaultValue = true;
			}
			else if (qName.equalsIgnoreCase("shorthand")){
				shorthand = true;
			}
		
		}
				
				
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(qName.equals("positionalArgument")){
				p.addArg(stringName,stringDescription,stringType);
			}
			else if(qName.equals("optionalArgument")){
				p.addArg(stringName,stringDescription,stringDefault,stringType);
			}
		}
			
		
		public void characters(char ch[], int start, int length) throws SAXException {

			if (name) {
				stringName=new String(ch, start, length);
				name = false;
				
			}

			if (type) {
				
				if (new String(ch, start, length).equals("STRING")){
					stringType=Argument.DataType.STRING;
				}
				else if(new String(ch, start, length).equals("FLOAT")){
					stringType=Argument.DataType.FLOAT;
				}
				else if (new String(ch, start, length).equals("INT")){
					stringType=Argument.DataType.INT;
				}
				else{
					stringType=Argument.DataType.BOOL;
				}
				type = false;
			}

			if (description) {
				stringDescription=new String(ch, start, length);
				description = false;
			}
			
			if (defaultValue) {
				stringDefault=new String(ch, start, length);
				defaultValue = false;
			}
			
			if (shorthand) {
				stringShorthand= new String(ch, start, length);
				shorthand= false;
			}
		}	
	};
	
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */
	
	public ArgsParser getArgsParser(){
		return p;
	}
			
	 /**
	 *This method adds a named argument to the program.
	 *
	 *@param <name> <description>  
	 *@throws <exception name>
	 *@returns <return value name>
	 
	 or @ <no value here> for void functions
	 */
			
	public ArgsParser parseXML(String filepath){
		try {
			InputStream xmlInput = new FileInputStream(filepath);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlInput,handler);
		}   
		catch (Exception e) {
			e.printStackTrace();
		}
		return getArgsParser();
	}

}
