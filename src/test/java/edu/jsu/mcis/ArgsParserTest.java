package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgsParserTest{
    
   
    @Test
    public void testNewInstanceHasNoArguments(){
        ArgsParser p = new ArgsParser();
        assertEquals(0, p.getNumOfArguments());
    }
    
    @Test
    public void  testArgumentIsAddedCorrectly(){
       ArgsParser p = new ArgsParser();
       p.addArg("length");
       assertEquals(1,p.getNumOfNameArgs() );
       
    }
    
    @Test
    public void testArgumentValueIsParsedCorrectly(){
        String[] s = {"17", "5", "12"};
        ArgsParser p = new ArgsParser();
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(s);
        assertEquals("17", p.getArg("length"));
        assertEquals("5", p.getArg("width"));
        assertEquals("12", p.getArg("height"));
        assertEquals(3, p.getNumOfArguments());
        
    }
    
    @Test(expected = TooFewArgsException.class)
    public void testExceptionIsThrownWhenTooFewArguments(){
        ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
        String[] s = {"7", "3"};
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(s);
        
    }
	
	@Test(expected = TooManyArgsException.class)
	public void testExceptionIsThrownWhenTooManyArguments(){
		ArgsParser p = new ArgsParser();
		p.setProgramName("VolumeCalculator");
		String[] s = {"7", "3", "2", "42"};
		p.addArg("length");
		p.addArg("width");
		p.addArg("height");
		p.parse(s);
	}
    
    @Test(expected = HelpException.class)
    public void testHelpExceptionIsThrown(){
        ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
        p.setProgramDescription("Calculate the volume of a box");
        String[] argDescripts = {"length the length of the box(float)" , "width the width of the box(float)", "height the height of the box(float)"};
        String[] s = {"-h"};
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.addArgDescriptions(argDescripts);
        p.parse(s);
    }
    
    @Test
    public void testDescriptionIsAdded(){
        ArgsParser p = new ArgsParser();
        p.setProgramDescription("This is a test");
        assertEquals("This is a test", p.getProgramDescription());
    }
    
    @Test
    public void testProgramNameIsAddedCorrectly(){
        ArgsParser p = new ArgsParser();
        p.setProgramName("Test");
        assertEquals("Test", p.getProgramName());
    }
    
	@Test
	public void testDataTypeIsAddedCorrectly(){
		ArgsParser p = new ArgsParser();
		p.addArg("length", float.class);
		
		assertEquals(ArgsParser.DataType.FLOAT, p.getDataType("length"));
	}
	
	@Test
	public void testDefaultDataTypeIsString(){
		ArgsParser p = new ArgsParser();
		p.addArg("length");
		
		assertEquals(ArgsParser.DataType.STRING, p.getDataType("length"));
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrown(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "something", "2"};
		p.addArg("length", float.class);
		p.addArg("width", float.class);
		p.addArg("height", float.class);
		
		p.parse(s);
	}
   
    
}