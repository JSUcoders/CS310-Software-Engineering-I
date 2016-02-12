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
        String[] s = {"17"};
        ArgsParser p = new ArgsParser();
        p.addArg("length");
        p.parse(s);
        assertEquals("17", p.getArg("length"));
        assertEquals(1, p.getNumOfArguments());
        
    }
    
    @Test(expected = TooFewArgsException.class)
    public void testExceptionIsThrownWhenTooFewArguments(){
        ArgsParser p = new ArgsParser();
        
        String[] s = {"7", "3"};
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(s);
        
    }
	
	@Test(expected = TooManyArgsException.class)
	public void testExceptionIsThrownWhenTooManyArguments(){
		ArgsParser p = new ArgsParser();
		
		String[] s = {"7", "3", "2", "42"};
		p.addArg("length");
		p.addArg("width");
		p.addArg("height");
		p.parse(s);
	}
    
    @Test(expected = HelpException.class)
    public void testHelpExceptionIsThrown(){
        ArgsParser p = new ArgsParser();
        String[] s = {"-h"};
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(s);
    }
    
   
    
}