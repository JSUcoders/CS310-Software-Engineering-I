import edu.jsu.mcis.*;
import java.util.*;

public class VCDemo{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
        List<String> restricted  =  Arrays.asList("5", "8", "10"); 
        String message = "VolumeCalculator";
        String description = "Calculate the volume of a box";
        p.addArg("length","length the length of the box",Argument.DataType.FLOAT);
        p.setRestricted("length", restricted);
        p.addArg("width","width the width of the box",Argument.DataType.FLOAT);
        p.addArg("height","height the height of the box",Argument.DataType.FLOAT);
        p.addArg("--type","the shape of the object","box",OptionalArgument.DataType.STRING,"-t");
        p.setOptArgToRequired("--type");
        p.addArg("--digits","the amount of digits in the volume","4",OptionalArgument.DataType.INT,"-d");
        
        p.setProgramName(message);
        p.setProgramDescription(description);
        p.saveXML("VCDemo.xml");
        try{ 
            p.parse(args);
            System.out.println("The length is: "+ p.getArg("length"));
            System.out.println("The datatype of width is: " + p.getDataType("width"));
            System.out.println("The optional argument type has the default value of: " + p.getArg("--type"));
            System.out.println("The optional argument digits has the datatype of: " + p.getDataType("--digits"));
            System.out.println("The volume is: " + ( (float)p.getArg("length") * (float)p.getArg("width") * (float)p.getArg("height")));
        }
        catch(HelpException e){
            System.out.println(e.getExceptionOutput());
            //throw e;
        }
        catch(RequiredArgumentsNeededException e){
            System.out.println(e.getExceptionOutput());
            //throw e;
        }
        catch(RestrictedArgumentException e){
            System.out.println(e.getExceptionOutput());
        }
        
       
        
        
        
    }
}


