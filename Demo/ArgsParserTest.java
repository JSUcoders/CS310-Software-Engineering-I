package edu.jsu.mcis

import org.junit.*;
import static org.junit.Assert.*;

public class ArgsParserTest{
    @Test
    public void testNewInstanceHasNoArguments(){
        ArgsParser p = new ArgParser();
        assertEquals(0, p.getNumArguments);
    }
    
    @Test
    public void  testArgumentIsAddedCorrectly(){
       // ArgsParser p = new
    }
    @Test
    public void testArgumentValueIsParsedCorrectly(){
        String[] s = {"7"};
        ArgsParser p = new ArgsParser();
        p.addArg("length");
        p.parse(s);
        assetEquals("17", p.getArg("length"));
    }
    
   /* @Test
    public void testVolumeIsCalculatedCorrectly(){
        String[] s = {"7","5","2"};
        ArgsParser p= new ArgsParser();
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(s);
        float length = Float.parseFloat(p.getArg("length"));
        float width = Float.parseFloat(p.getArg("width"));
        float height = Float.parseFloat(p.getArg("height"));
        float volume = length*width*height;
    }*/
    
}