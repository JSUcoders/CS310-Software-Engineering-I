import edu.jsu.mcis.*;
import java.util.*;

public class videoGame{
    public static void main(String[] args){
        
        ArgsParser p = new ArgsParser();
		
		List<String> restrictedPlayers = Arrays.asList("1", "2", "3", "4");
		List<String> restrictedGenre = Arrays.asList("rpg", "fps", "puzzle", "horror", "adventure");
		
		String message = "videoGame";
		String description = "Sets up a video game with number of players, genre, and price";
		
		p.addArg("players", "players the number of players", Argument.DataType.INT);
		p.addArg("genre", "genre the genre of the game", Argument.DataType.STRING);
		p.addArg("price", "price the price of the game", Argument.DataType.FLOAT);
		p.addArg("--platform", "platform the platform the game is on", "PC", Argument.DataType.STRING, "-p");
		
		p.setRestricted("players", restrictedPlayers);
		p.setRestricted("genre", restrictedGenre);
		
		p.setProgramName(message);
        p.setProgramDescription(description);
		try{
			p.parse(args);
			System.out.println("You have a game with " + Integer.toString((int)p.getArg("players")) + " player(s). The game is a(n) " + p.getArg("genre") +
			" and costs $" + (float)p.getArg("price") + ". The game is for the " + p.getArg("--platform") + ".");
		}catch(RestrictedArgumentException e){
			System.out.println(e.getExceptionOutput());
		}
	}
}