package edu.jsu.mcis;

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
			else if(qName.equals("optionalArgument")){
				p.addArg(stringName,stringDescription,stringDefault,stringType);
			}
			System.out.println("End Element :" + qName);
		}
			
		
		public void characters(char ch[], int start, int length) throws SAXException {

			if (name) {
				stringName=new String(ch, start, length);
				System.out.println("name : " + new String(ch, start, length));
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
				System.out.println("type : " + new String(ch, start, length));
				type = false;
			}

			if (description) {
				stringDescription=new String(ch, start, length);
				System.out.println("description : " + new String(ch, start, length));
				description = false;
			}
			
			if (defaultValue) {
				stringDefault=new String(ch, start, length);
				System.out.println("defaultValue : " + new String(ch, start, length));
				defaultValue = false;
			}
			
			if (shorthand) {
				stringShorthand= new String(ch, start, length);
				System.out.println("shorthand : " + new String(ch, start, length));
				shorthand= false;
			}
		}	
	};
	
	public ArgsParser getArgsParser(){
		return p;
	}
				
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
