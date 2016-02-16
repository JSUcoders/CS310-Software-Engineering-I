import edu.jsu.mcis.*;
import java.util.Arrays;
public class ArgsParserKeywords{
	private ArgsParser p;
	private String programOutput;
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
			
			float length = Float.parseFloat(p.getArg("length"));
			float width = Float.parseFloat(p.getArg("width"));
			float height = Float.parseFloat(p.getArg("height"));
			setProgramOutput(String.valueOf(length * width * height));
		}
		catch(HelpException e){
			setProgramOutput(e.getExceptionOutput());
		}
		
		catch(TooFewArgsException e){
			setProgramOutput(e.getExceptionOutput());
		}
		
		catch(TooManyArgsException e){
			setProgramOutput(e.getExceptionOutput());
		}
        
		catch(RuntimeException r){
            
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
        
	
		return programOutput;
	}
	public void setProgramOutput(String a){
		programOutput = a;
	};
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
	public void startProgramWithArguments(String[] args){
		ArgsParser q = new ArgsParser();
		setProgramOutput(" ");
		String message = "VolumeCalculator";
        String description = "Calculate the volume of a box.";
        
        q.setProgramName(message);
        q.setProgramDescription(description);
        
		try{
            q.addArg("length");
            q.addArg("width");
            q.addArg("height");
            q.parse(args);
		}
		catch(HelpException e){
			setProgramOutput(e.getExceptionOutput());
		}
	}
}