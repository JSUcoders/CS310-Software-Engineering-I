import edu.jsu.mcis.*;
import java.util.Arrays;
public class ArgsParserKeywords{
	private ArgsParser p;
	private String programOutput;
	public void startVolumeCalculatorWithArguments(String[] args){
        String message = "VolumeCalculator";
        String description = "Calculate the volume of a box";
        String[] argDescripts = {"length the length of the box" , "width the width of the box", "height the height of the box"};
        p = new ArgsParser();
        p.setProgramName(message);
        p.setProgramDescription(description);
        try{
            p.addArg("length", float.class);
            p.addArg("width", float.class);
            p.addArg("height", float.class);
            p.addArgDescriptions(argDescripts);
            p.parse(args);
			
			float length = (float) p.getArg("length");
			float width = (float) p.getArg("width");
			float height = (float) p.getArg("height");
			float volume = length * width * height;
			setProgramOutput(String.valueOf(volume));
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
        catch(InvalidArgumentException e){
            setProgramOutput(e.getExceptionOutput());
        }
		catch(RuntimeException r){
            
        }
		
	}
	
	public Object getLength(){
		return p.getArg("length");
	}
	
	public Object getWidth(){
		return p.getArg("width");
	}
	
	public Object getHeight(){
		return p.getArg("height");
	}
	
	public String getProgramOutput(){
        
		/**
		float length = Float.parseFloat(p.getArg("length"));
		float width = Float.parseFloat(p.getArg("width"));
		float height = Float.parseFloat(p.getArg("height"));
		
		return String.valueOf(length * width * height);
		**/
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
	
	public Object getPet(){
		return p.getArg("pet");
	}
	
	public Object getNumber(){
		return p.getArg("number");
	}
	
	public Object getRainy(){
		return p.getArg("rainy");
	}
	
	public Object getBathrooms(){
		return p.getArg("bathrooms");
	}
	public void startProgramWithArguments(String[] args){
		ArgsParser q = new ArgsParser();
		setProgramOutput(" ");
		String message = "VolumeCalculator";
        String description = "Calculate the volume of a box.";
        String[] argDescripts = {"length the length of the box(float)" , "width the width of the box(float)", "height the height of the box(float)"};
        q.setProgramName(message);
        q.setProgramDescription(description);
        
		try{
            q.addArg("length", float.class);
            q.addArg("width", float.class);
            q.addArg("height",float.class);
            q.addArgDescriptions(argDescripts);
            q.parse(args);
		}
		catch(HelpException e){
			setProgramOutput(e.getExceptionOutput());
		}
        catch(InvalidArgumentException e){
            setProgramOutput(e.getExceptionOutput());
        }
        catch(RuntimeException r){
            
        }
	}
}