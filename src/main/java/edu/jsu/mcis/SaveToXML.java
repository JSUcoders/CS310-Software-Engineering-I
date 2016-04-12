package edu.jsu.mcis;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.io.*;

/**<p>
 * This class allows program data to be saved or loaded from XML files.
 *<p>
 * this is an example of how to use this class to load from XML files
 * in the example the string array args would be replaced with the comandline arguments taken from the user
 *<p>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;	ArgsParser p = new ArgsParser();
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	SaveToXML rw = new SaveToXML();
 *<br> 
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p=rw.parseXML("newXML.xml");
 *<p>
 *This is an example of how to use this class to save XML files.
 *<p>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	ArgsParser p = new ArgsParser();
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramName("VolumeCalculator");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.setProgramDescription("Calculate the volume of a box.");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
 *<br>
 *     	&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
 *<br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
 *<br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--help","false");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--type", "the type","box",OptionalArgument.DataType.STRING,"-t");
 *<br>
 *      &nbsp;&nbsp;&nbsp;&nbsp;	p.addArg("--digits","4");
 *<br>
 *		&nbsp;&nbsp;&nbsp;&nbsp;	p.saveXML("newXML.xml");
 *<br>
 
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
	 *This method returns this instance of ArgsParser.
	 *
	 *@return p This instance of ArgsParser
	 */
	
	public ArgsParser getArgsParser(){
		return p;
	}
			
	 /**
	 *This method reads XML Data into and instance of ArgsParser.
	 *
	 *@param filepath Path to specified XML file 
	 *@return getArgsParser
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
