import edu.jsu.mcis.*;
import java.util.Arrays;
public class ArgsParserKeywords{
	private ArgsParser p;
	
	public void startVolumeCalculatorWithArguments(String[] args){
        String message = "VolumeCalculator";
        String description = "Calculate the volume of a box";
        p = new ArgsParser();
        p.setProgramName(message);
        p.setProgramDescription(description);
        try{
            
            p.addArg("length");
            p.addArg("width");
            p.addArg("height");
            p.parse(args);
        }catch(RuntimeException r){
            
        }
		
	}
	
	public String getLength(){
		return p.getArg("length");
	}
	
	public String getWidth(){
		return p.getArg("width");
	}
	
	public String getHeight(){
		return p.getArg("height");
	}
	
	public String getProgramOutput(){
        
		float length = Float.parseFloat(p.getArg("length"));
		float width = Float.parseFloat(p.getArg("width"));
		float height = Float.parseFloat(p.getArg("height"));
		
		return String.valueOf(length * width * height);
	}
	
	public void startAbsurdProgramWithArguments(String[] args){
		p = new ArgsParser();
		p.addArg("pet");
		p.addArg("number");
		p.addArg("rainy");
		p.addArg("bathrooms");
		
		p.parse(args);
	}
	
	public String getPet(){
		return p.getArg("pet");
	}
	
	public String getNumber(){
		return p.getArg("number");
	}
	
	public String getRainy(){
		return p.getArg("rainy");
	}
	
	public String getBathrooms(){
		return p.getArg("bathrooms");
	}
}