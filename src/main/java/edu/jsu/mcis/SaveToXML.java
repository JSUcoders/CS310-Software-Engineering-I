package edu.jsu.mcis.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.io.*;



public class SaveToXML extends DefaultHandler{
	private boolean name;
    private boolean value;
    private boolean type;
	private boolean description;
	private boolean shorthand;
	private boolean defaultValue;
	
	private StringBuilder nameString;
	private StringBuilder valueString;
	private StringBuilder typeString;
	private StringBuilder descriptionString;
	private StringBuilder shorthandString;
	private StringBuilder defaultValueString;
	
	private ArgsParser p; 
	
	private String stringName;
	private String stringDescription;
	private String stringShorthand;
	private String stringDefault;
	private Argument.Type stringType;
	
	
	
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
		
		stringNames = "" ; 
		stringDescription = "" ; 
		stringShorthand = "" ; 
		stringDefault = "" ; 
		argType = Argument.Type.STRING; 
	
	}
	
	DefaultHandler handler = new DefaultHandler(){
	
		public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
			System.out.println("Start Element :" + qName);
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
			else(qName.equals("namedArgument")){
				p.addArg(stringName,stringDescription,stringType,stringDefault,stringType);
			}
			System.out.println("End Element :" + qName);
		}
			
		
		public void characters(char ch[], int start, int length) throws SAXException {

			if (name) {
				stringName=new String(ch, start, length);
				System.out.println("name : " + new String(ch, start, length));
				name = false;
				
			}

			if (type) {//float int bool sting
				
				if (new String(ch, start, length).equals("STRING")){
					argType=ARGUMENT.Type.STRING;
				}
				else if(new String(ch, start, length).equals("FLOAT")){
					argType=Argument.Type.FLOAT;
				}
				else if (new String(ch, start, length).equals("INTEGER")){
					argType=ARGUMENT.Type.INTEGER;
				}
				else{
					argType=ARGUMENT.Type.BOOLEAN;
				}
				System.out.println("type : " + new String(ch, start, length));
				type = false;
			}

			if (description) {
				stringDescription=new String(ch, start, length);
				System.out.println("description : " + new String(ch, start, length));
				description = false;
			}
			
			if (defaults) {
				stringDefault=new String(ch, start, length);
				System.out.println("defaults : " + new String(ch, start, length));
				defaults = false;
			}
			
			if (shorthand) {
				stringShorthand= new String(ch, start, length);
				System.out.println("shorthand : " + new String(ch, start, length));
				shorthand= false;
			}
		}	
	};
	
	public ArgParser getArgParser(){
		return p;
	}
				
	public static void main(String argv[]) {
		SaveToXML readWrite = new SaveToXML();
	    try{
			InputStream xmlInput = new FileInputStream("readXML.xsl");
		    SAXParserFactory factory = SAXParserFactory.newInstance();
		    SAXParser saxParser = factory.newSAXParser();

		    saxParser.parse(xmlInput,readWrite.handler);

		    //DefaultHandler handler = new DefaultHandler() {
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(readWrite.getNames());
		System.out.println(readWrite.getType());
		  
   }


}
	
	
	
	
	
	
	
}