package edu.jsu.mcis;


public class VolumeCalculator {
    private int length =0;
	private int width =0; 
	private int hight =0;
    VolumeCalculator(int a,int b,int c){
		length = a;
		width = b;
		hight = c;
	}
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	
	public int getHight(){
		return hight;
	}
	
	
	public int getVolume(){
		int volume = length * width * hight;
		return volume;
	}
	
    
    public static void main(String[] args) {
      
    }
}












