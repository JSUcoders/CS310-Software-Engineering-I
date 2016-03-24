import edu.jsu.mcis.*;

public class VCDemo{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
        String message = "VolumeCalculator";
        String description = "Calculate the volume of a box";
        String[] argDescripts = {"length the length of the box" , "width the width of the box", "height the height of the box"};
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.addArgDescriptions(argDescripts);
        p.setProgramName(message);
        p.setProgramDescription(description);
        try{
            p.parse(args);
        }
        catch(HelpException e){
			System.out.println(e.getExceptionOutput());
		}
		
		catch(TooFewArgsException e){
			System.out.println(e.getExceptionOutput());
		}
		
		catch(TooManyArgsException e){
			System.out.println(e.getExceptionOutput());
		}
        catch(InvalidArgumentException e){
            System.out.println(e.getExceptionOutput());
        }
        
        //System.out.println(p.getArg("length"));
    }
}


//javac -cp .;../build/libs/CS310-Software-Engineering-I-1.0.jar VCDemo.java
//java -cp .;../build/libs/CS310-Software-Engineering-I-1.0.jar VCDemo 7 9 3
