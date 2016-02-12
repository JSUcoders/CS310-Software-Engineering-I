import edu.jsu.mcis.*;

public class VCDemo{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
        p.addArg("length");
        p.addArg("width");
        p.addArg("height");
        p.parse(args);
        System.out.println(p.getArg("width"));
    }
}