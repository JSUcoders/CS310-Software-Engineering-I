package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class VolumeCalculatorTest {
    
    private VolumeCalculator a;
	
	@Before 
	public void setup(){
		a = new VolumeCalculator(1,2,3);
	}
	
	
	@Test
	public void testGetLength(){
		boolean result = false;
		if(a.getLength() == 1){
			result = true;
		} 
		assertTrue(result);
	}
	@Test
	public void testGetWidth(){
		boolean result = false;
		if(a.getWidth() == 2){
			result = true;
		} 
		assertTrue(result);
	}
	
	@Test
	public void testGetHight(){
		boolean result = false;
		if(a.getHight() == 3){
			result = true;
		} 
		assertTrue(result);
	}
	
	@Test
	public void testGetVolume(){
		boolean result = false;
		if(a.getVolume() == 6){
			result = true;
		} 
		assertTrue(result);
	}
	
}
