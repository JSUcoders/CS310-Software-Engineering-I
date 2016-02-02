package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class VolumeCalculatorTest {
    
    private VolumeCalculator a;
	
	@Before 
	public void setup(){
		a = new VolumeCalculator();
		
		
	}
	
	
	@Test
	public void testGetLength(){
		boolean result = false;
		a.VolumeCalculator(1,2,3);
		
		if(a.getLength == 1){
			result = true;
		} 
		assertTrue(result);
	}
	@Test
	public void testGetWidth(){
		boolean result = false;
		a.VolumeCalculator(1,2,3);
		
		if(a.getWidth == 2){
			result = true;
		} 
		assertTrue(result);
	}
	
	@Test
	public void testGetHight(){
		boolean result = false;
		a.VolumeCalculator(1,2,3);
		
		if(a.getHight == 3){
			result = true;
		} 
		assertTrue(result);
	}
	
	@Test
	public void testGetVolume(){
		boolean result = false;
		a.VolumeCalculator(1,2,3);
		
		if(a.getVolume == 3){
			result = true;
		} 
		assertTrue(result);
	}
	
}