import edu.jsu.mcis.*;

public class helpDemo{
	
	public static void main(String[] args){
	
	ArgsParser p = new ArgsParser();
    p.setProgramName("the name of the program");
    p.setProgramDescription("the program's description");  
    p.addArg("length", "the length of something", Argument.DataType.FLOAT);
    p.addArg("width", "the width of something", Argument.DataType.FLOAT);
    p.addArg("height", "the height of something", Argument.DataType.FLOAT);
	
	 try{
            p.parse(args);
        }
        catch(HelpException e){
			System.out.println(e.getExceptionOutput());
		}
		
	
	
	}
	
}