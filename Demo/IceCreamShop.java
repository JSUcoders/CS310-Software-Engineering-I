import edu.jsu.mcis.*;
import java.util.*;

public class IceCreamShop{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
        p.setProgramName("Ice cream shop");
        p.setProgramDescription("This program runs a very basic simulation of an ice cream shop");
		p.addArg("flavor", "flavor of the ice cream", Argument.DataType.STRING);
		p.addArg("container", "how the ice cream is served", Argument.DataType.STRING);
		p.addArg("price", "price of the ice cream", Argument.DataType.FLOAT);
		p.addArg("--toppings", "toppings of the ice cream", "whip cream", Argument.DataType.STRING, "-t");
        p.saveXML("IceCreamShop.xml");
		try{
			p.parse(args);
			System.out.println("You have ordered a: " + p.getArg("flavor") + " " + p.getArg("container") + " for the price of: "
			+ "$" + (float)(p.getArg("price")) + "." + " You have a topping of: " + p.getArg("--toppings"));            
		}catch(RestrictedArgumentException e){
			System.out.println(e.getExceptionOutput());
		}
	}
}