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
       assertEquals(1, p.getNumOfNameArgs() );
       
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
    @Test(expected = TooFewArgsException.class)
    public void testExceptionIsThrownWhenNoArguments(){
        ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
        String[] s = {};
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
	
	@Test
	public void testGetArgParsesArguments(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "5", "2"};
		p.addArg("length", float.class);
		p.addArg("width", float.class);
		p.addArg("height", float.class);
		
		p.parse(s);
		
		assertEquals((float)7, p.getArg("length"));
		assertEquals((float)5, p.getArg("width"));
		assertEquals((float)2, p.getArg("height"));
	}
    @Test
	public void testGetArgParsesArgumentsOfTypeInt(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "5", "2"};
		p.addArg("length", int.class);
		p.addArg("width", int.class);
		p.addArg("height", int.class);
		
		p.parse(s);
		
		assertEquals(7, p.getArg("length"));
		assertEquals(5, p.getArg("width"));
		assertEquals(2, p.getArg("height"));
	}
    @Test
	public void testGetArgParsesArgumentsOfTypeBoolean(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"true", "false", "True"};
		p.addArg("test 1", boolean.class);
		p.addArg("test 2", boolean.class);
		p.addArg("test 3", boolean.class);
		
		p.parse(s);
		
		assertTrue( (boolean)p.getArg("test 1"));
		assertFalse( (boolean)p.getArg("test 2"));
		assertTrue( (boolean)p.getArg("test 3"));
	}
    @Test
	public void testGetArgParsesArgumentsOfTypeString(){
		ArgsParser p = new ArgsParser();
		String[] s = {"len", "wid", "hei"};
		p.addArg("String 1", String.class);
		p.addArg("String 2", String.class);
		p.addArg("String 3", String.class);
		
		p.parse(s);
		
		assertEquals("len", p.getArg("String 1"));
		assertEquals("wid", p.getArg("String 2"));
		assertEquals("hei", p.getArg("String 3"));
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForNonFloats(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"7", "something", "2"};
		p.addArg("length", float.class);
		p.addArg("width", float.class);
		p.addArg("height", float.class);
		
		p.parse(s);
	}
    
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForChars(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"m", "p", "u"};
		p.addArg("length", char.class);
		p.addArg("width", char.class);
		p.addArg("height", char.class);
		
		p.parse(s);
	}
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForNonInts(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"8", "7", "bob"};
		p.addArg("length", int.class);
		p.addArg("width", int.class);
		p.addArg("height", int.class);
		
		p.parse(s);
	}
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForBytes(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"1", "0", "0"};
		p.addArg("length", byte.class);
		p.addArg("width", byte.class);
		p.addArg("height", byte.class);
        
		
		p.parse(s);
	}
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForLongs(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"342344", "909889", "57868"};
		p.addArg("length", long.class);
		p.addArg("width", long.class);
		p.addArg("height", long.class);
		
		p.parse(s);
	}
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForShorts(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"-32768", "30000", "20000"};
		p.addArg("length", short.class);
		p.addArg("width", short.class);
		p.addArg("height", short.class);
		
		p.parse(s);
	}
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForDoubles(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"69.0", "6.9", "0.69"};
		p.addArg("length", double.class);
		p.addArg("width", double.class);
		p.addArg("height", double.class);
		
		p.parse(s);
	}
	
	@Test
	public void testAllDataTypesGetParsed(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "true", "bob"};
        p.setProgramName("VolumeCalculator");
		p.addArg("Int", int.class);
		p.addArg("Bool", boolean.class);
		p.addArg("String");
		
		p.parse(s);
		
		assertEquals(ArgsParser.DataType.INT, p.getDataType("Int"));
		assertEquals(ArgsParser.DataType.BOOL, p.getDataType("Bool"));
		assertEquals(ArgsParser.DataType.STRING, p.getDataType("String"));
	
	}
    
    
   
    
}