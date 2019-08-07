import edu.jsu.mcis.*;
import java.util.*;

public class IceCreamShopLoad{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
        SaveToXML sTX = new SaveToXML();
        p = sTX.parseXML("IceCreamShop.xml");
		try{
			p.parse(args);
			System.out.println("You have ordered a: " + p.getArg("flavor") + " " + p.getArg("container") + " for the price of: "
			+ "$" + (float)(p.getArg("price")) + "." + " You have a topping of: " + p.getArg("--toppings"));            
		}catch(RestrictedArgumentException e){
			System.out.println(e.getExceptionOutput());
		}
	}
}