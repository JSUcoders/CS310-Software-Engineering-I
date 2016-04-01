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
       assertEquals(1, p.getNumOfArguments() );
       
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
		String[] s = {"7", "3", "2", "42", "34"};
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
        String[] s = {"-h"};
        p.addArg("length","length the length of the box(float)");
        p.addArg("width","width the width of the box(float)");
        p.addArg("height","height the height of the box(float)");
        
        p.parse(s);
    }
    
    @Test
    public void testProgramDescriptionIsAdded(){
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
		p.addArg("length", Argument.DataType.FLOAT);
		
		assertEquals(Argument.DataType.FLOAT, p.getDataType("length"));
	}
	
	@Test
	public void testDefaultDataTypeIsString(){
		ArgsParser p = new ArgsParser();
		p.addArg("length");
		
		assertEquals(Argument.DataType.STRING, p.getDataType("length"));
	}
	
	@Test
	public void testGetArgParsesArguments(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "5", "2"};
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
		
		p.parse(s);
		
		assertEquals((float)7, p.getArg("length"));
		assertEquals((float)5, p.getArg("width"));
		assertEquals((float)2, p.getArg("height"));
	}
    @Test
	public void testGetArgParsesArgumentsOfTypeInt(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "5", "2"};
		p.addArg("length", Argument.DataType.INT);
		p.addArg("width", Argument.DataType.INT);
		p.addArg("height", Argument.DataType.INT);
		
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
		p.addArg("test1", Argument.DataType.BOOL);
		p.addArg("test2", Argument.DataType.BOOL);
		p.addArg("test3", Argument.DataType.BOOL);
		
		p.parse(s);
		
		assertTrue( (boolean)p.getArg("test1"));
		assertFalse( (boolean)p.getArg("test2"));
		assertTrue( (boolean)p.getArg("test3"));
	}
    @Test
	public void testGetArgParsesArgumentsOfTypeString(){
		ArgsParser p = new ArgsParser();
		String[] s = {"len", "wid", "hei"};
		p.addArg("String1", Argument.DataType.STRING);
		p.addArg("String2", Argument.DataType.STRING);
		p.addArg("String3", Argument.DataType.STRING);
		
		p.parse(s);
		
		assertEquals("len", p.getArg("String1"));
		assertEquals("wid", p.getArg("String2"));
		assertEquals("hei", p.getArg("String3"));
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForNonFloats(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"7", "something", "2"};
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
		
        try {
            p.parse(s);
        }
        catch(InvalidArgumentException e) {
            System.out.println(e.getExceptionOutput());
            throw e;
        }
	}
    
    
    @Test(expected = InvalidArgumentException.class)
	public void testInvalidArgExceptionIsThrownForNonInts(){
		ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
		String[] s = {"8", "7", "bob"};
		p.addArg("length", Argument.DataType.INT);
		p.addArg("width", Argument.DataType.INT);
		p.addArg("height", Argument.DataType.INT);
		
		try {
            p.parse(s);
        }
        catch(InvalidArgumentException e) {
            System.out.println(e.getExceptionOutput());
            throw e;
        }
	}
   
	@Test
	public void testAllDataTypesGetParsed(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "true", "bob"};
        p.setProgramName("VolumeCalculator");
		p.addArg("Int", Argument.DataType.INT);
		p.addArg("Bool", Argument.DataType.BOOL);
		p.addArg("String");
		
		p.parse(s);
		
		assertEquals(Argument.DataType.INT, p.getDataType("Int"));
		assertEquals(Argument.DataType.BOOL, p.getDataType("Bool"));
		assertEquals(Argument.DataType.STRING, p.getDataType("String"));
	
	}
    
   
    @Test
    public void testDefaultOptionalTypesExist(){
        ArgsParser p =  new ArgsParser();
        String[] s = {"7", "3","2",};
        p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--type", "box");
        p.addArg("--digits", "4");
        p.parse(s);
        assertEquals("box", p.getArg("--type"));
        assertEquals("4", p.getArg("--digits"));
    }
    @Test
    public void testAddingOptionalArguments(){
        ArgsParser p =  new ArgsParser();
        String[] s = {"--type", "ellipsoid","7","3","--digits","1","2"};
        p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--type", "box");
        p.addArg("--digits", "4");
        p.parse(s);   
       
        assertEquals("ellipsoid", p.getArg("--type"));
        assertEquals("1", p.getArg("--digits"));
    }
    
    @Test(expected = TooManyArgsException.class)
	public void testExceptionIsThrownWhenTooManyArguments2(){
		ArgsParser p = new ArgsParser();
		p.setProgramName("VolumeCalculator");
		String[] s = {"--type", "ellipsoid","7","3","--digits","1","2", "--hello","6", "43","8"};
		p.addArg("length");
		p.addArg("width");
		p.addArg("height");
		p.addArg("--type", "box");
		p.addArg("--digits", "4");
        p.addArg("--hello", "3");
        try{
            p.parse(s);
        }catch(TooManyArgsException e){
            
			System.out.println(e.getExceptionOutput());
            throw e;
        }
		
        
	}
    
    @Test(expected = TooManyArgsException.class)
    public void testTooManyArgsWithDefault(){
        ArgsParser p =  new ArgsParser();
        String[] s = {"7", "3","2","43"};
        p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--type", "box");
        p.addArg("--digits", "4");
        p.parse(s);
    }
   
   @Test
   public void testFlagArgumentIsTrue(){
       ArgsParser p = new ArgsParser();
       String[] s = {"7", "--myArg","3","2"};
       p.addArg("--myArg", "false");
       p.addArg("length");
       p.addArg("width");
       p.addArg("height");
       p.parse(s);

       assertEquals("true", p.getArg("--myArg"));
   }

   
    @Test
    public void testAddingOptionalArgumentsShortNames(){
        ArgsParser p =  new ArgsParser();
        String[] s = {"-t", "ellipsoid","7","3","-d","1","2"};
        p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--type", "box","-t");
        p.addArg("--digits", "4","-d");
        p.parse(s);
         
        assertEquals("ellipsoid", p.getArg("--type"));
        assertEquals("1", p.getArg("--digits"));
        assertEquals((float) 7, p.getArg("length"));
        assertEquals((float) 3, p.getArg("width"));
        assertEquals((float) 2, p.getArg("height"));
       
        
    }
   
   
   @Test(expected = HelpException.class)
   public void testHelpExceptionIsThrownNotAtBeginningOfCommandLine(){
       ArgsParser p = new ArgsParser();
        p.setProgramName("VolumeCalculator");
        p.setProgramDescription("Calculate the volume of a box.");
        String[] s = {"7", "--help","3","2"};
        p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
        p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
        p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
        
        try{
            p.parse(s);
        }catch(HelpException e){
            assertEquals("usage: java VolumeCalculator length width height\nCalculate the volume of a box.\npositional arguments:\nlength the length of the box(float)\nwidth the width of the box(float)\nheight the height of the box(float)", e.getExceptionOutput());
            throw e;
        }
   }
   
    @Test(expected = UnknownArgumentException.class)
	public void testUnknownArgumentException(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "myval", "3", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
		try{
			p.parse(s);
		}catch(UnknownArgumentException e){
			System.out.println(e.getExceptionOutput());
			
			throw e;
		}
   }
   
    @Test(expected = UnknownArgumentException.class)
	public void testMultipleUnknownArguments(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "myval", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
		try{
			p.parse(s);
		}catch(UnknownArgumentException e){
			System.out.println(e.getExceptionOutput());
			
			throw e;
		}
   }
   
    @Test
	public void testOptionalArgumentsGetADataType(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
	    assertEquals(5, p.getArg("--myArg"));
        assertEquals("otherValue", p.getArg("--otherArg"));
        assertEquals((float) 8, p.getArg("--anotherArg"));    
		
   }
   
    @Test(expected = ThatArgumentDoesNotExistException.class)
	public void testGetArgThrowsExceptionWhenOptionalArgNameIsIncorrect(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
	    try{
            Object o = p.getArg("--z-axis");
        }catch(ThatArgumentDoesNotExistException e){
            System.out.println(e.getExceptionOutput());
            throw e;
        }   
		
   }
   
    @Test
	public void testGetDataTypeForOptionalArgument(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
	    assertEquals(OptionalArgument.DataType.INT, p.getDataType("--myArg"));
        assertEquals(OptionalArgument.DataType.FLOAT, p.getDataType("--anotherArg"));
        assertEquals(OptionalArgument.DataType.STRING, p.getDataType("--otherArg"));    
		
   }
   
   @Test(expected = ThatArgumentDoesNotExistException.class)
	public void testGetDataTypeForOptionalArgumentThrowsExceptionWhenIncorrectArgIsGiven(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
        try{
            Argument.DataType d = p.getDataType("--z-axis");
        }catch(ThatArgumentDoesNotExistException e){
            System.out.println(e.getExceptionOutput());
            throw e;
        }  
	       
		
   }
   
   @Test(expected = ThatArgumentDoesNotExistException.class)
	public void testGetDataTypeForArgumentThrowsExceptionWhenIncorrectArgIsGiven(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
        try{
            Argument.DataType d = p.getDataType("z-axis");
        }catch(ThatArgumentDoesNotExistException e){
            System.out.println(e.getExceptionOutput());
            throw e;
        }  
	       
		
   }
   
   @Test(expected = ThatArgumentDoesNotExistException.class)
	public void testGetArgThrowsExceptionWhenArgNameIsIncorrect(){
		ArgsParser p = new ArgsParser();
		String[] s = {"7", "--myArg", "5", "3", "--otherArg", "otherValue", "2"};
		p.setProgramName("VolumeCalculator");
		p.addArg("length", Argument.DataType.FLOAT);
		p.addArg("width", Argument.DataType.FLOAT);
		p.addArg("height", Argument.DataType.FLOAT);
        p.addArg("--myArg","this is a test for myArg","1",OptionalArgument.DataType.INT);
        p.addArg("--otherArg", "this is a test for otherArg","hello", OptionalArgument.DataType.STRING);
        p.addArg("--anotherArg", "this is a test for anotherArg", "8", OptionalArgument.DataType.FLOAT);
	    p.parse(s);
	    try{
            Object o = p.getArg("z-axis");
        }catch(ThatArgumentDoesNotExistException e){
            System.out.println(e.getExceptionOutput());
            throw e;
        }   
		
   }
   

   @Test
   public void testThatDifferentAddArgsWork(){
       ArgsParser p = new ArgsParser();
       p.addArg("--aName", "this is a test", "hi", "-a");
       p.addArg("--bName", "7", OptionalArgument.DataType.INT,"-b");
       p.addArg("--cName", "this is another test", "hello", OptionalArgument.DataType.STRING, "-c");
       p.addArg("--dName", "8", OptionalArgument.DataType.FLOAT);
       p.addArg("--eName", "code", "oh look another test");
       assertEquals(7, p.getArg("--bName"));
       assertEquals("hello", p.getArg("--cName"));
       assertEquals(OptionalArgument.DataType.STRING, p.getDataType("--aName"));
       assertEquals(OptionalArgument.DataType.FLOAT, p.getDataType("--dName"));
       assertEquals("code", p.getArg("--eName"));
   }
   
   

   
   
    @Test
	public void testSavingtoXMLFile(){
		ArgsParser p = new ArgsParser();
		p.setProgramName("VolumeCalculator");
		p.setProgramDescription("Calculate the volume of a box.");
        p.addArg("length","length the length of the box(float)",Argument.DataType.FLOAT );
        p.addArg("width",  "width the width of the box(float)", Argument.DataType.FLOAT);
        p.addArg("height", "height the height of the box(float)", Argument.DataType.FLOAT);
        p.addArg("--help","false");
		p.addArg("--type", "the type","box",OptionalArgument.DataType.STRING,"-t");
        p.addArg("--digits","4");
		p.saveXML("newXML.xml");
	
    }
   
    @Test
    public void testLoadingFromXMLFile(){
        ArgsParser p = new ArgsParser();
        SaveToXML rw = new SaveToXML();   
        p=rw.parseXML("newXML.xml");
        assertEquals(Argument.DataType.FLOAT,p.getDataType("length"));
    
    }
    
    @Test
    public void testLoadingFromXMLFile2(){
        ArgsParser p = new ArgsParser();
        SaveToXML rw = new SaveToXML();   
        p=rw.parseXML("newXML.xml");
        assertEquals("4",p.getArg("--digits"));

    }
}