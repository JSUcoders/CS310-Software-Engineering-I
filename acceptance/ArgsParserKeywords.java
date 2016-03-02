import edu.jsu.mcis.*;
import java.util.*;
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
            p.addArg("length", Argument.DataType.FLOAT);
            p.addArg("width", Argument.DataType.FLOAT);
            p.addArg("height", Argument.DataType.FLOAT);
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
    public Object getType(){
        List<String> a = p.getOptionalArgNames();
        List<String> b = p.getOptionalArgValues();
        for(int i =0; i< a.size();i++){
            if(a.get(i).equals("--type")){
                return b.get(i);
            }
            
            
        }
        
        return b.get(0);
    }
    public Object getDigits(){
        List<String> a = p.getOptionalArgNames();
        List<String> b = p.getOptionalArgValues();
        for(int i =0; i< a.size();i++){
            if(a.get(i).equals("--digits")){
                return b.get(i);
            }

        }
        return b.get(1);
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
            q.addArg("length", Argument.DataType.FLOAT);
            q.addArg("width", Argument.DataType.FLOAT);
            q.addArg("height",Argument.DataType.FLOAT);
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