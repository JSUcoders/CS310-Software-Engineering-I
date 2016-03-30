import jsu.edu.mcis.*;

public class helpDemo{
	
	public static void main(String[] args){
	
	ArgsParser p = new ArgsParser();
	p.addArg("-h");
	
	 try{
            p.parse(args);
        }
        catch(HelpException e){
			System.out.println(e.getExceptionOutput());
		}
		
	
	
	}
	
}