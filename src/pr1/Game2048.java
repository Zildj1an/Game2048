package tp.pr1;

import java.util.Random;

import tp.p2.control.Controller;
import tp.p2.logic.multigames.Game;
import tp.p2.logic.multigames.GameType;
import tp.pr3.exceptions.IncorrectArgumentsException;
import tp.pr3.exceptions.gameOverExceptions.GameOverExceptions;
import tp.pr3.exceptions.gameOverExceptions.LoseException;
import tp.pr3.exceptions.newGameExceptions.TooCellsException;

/**
 * Esta será nuestra clase principal, en la que podremos jugar al popular juego
 * 2048 y a distintas versiones derivadas de este.
 */
public class Game2048 {

	/**
	 * Este será el main, el método principal en el que comenzaremos el juego con
	 * los argumentos introducidos por el usuario.
	 * 
	 * @param args
	 * @throws LoseException 
	 */
	public static void main(String[] args) throws LoseException {	
	    try 
	    {
	    	int size, numInitial;
			long seed;
			Random random = new Random();
			
	    	if (args.length < 2 || args.length > 3)
	  	      throw new IncorrectArgumentsException();
	    	
	        size = Integer.parseInt(args[0]);
	        numInitial = Integer.parseInt(args[1]);
	        if (args.length == 2) {
				seed = random.nextInt();
			} 
	        else {
				seed = Long.parseLong(args[2]);
			}
	        if(numInitial > size * size)
	        	throw new TooCellsException();
	        Game.ASCII_Art();
	        Game game = new Game(seed, size, numInitial, GameType.ORIGINAL);
			Controller controller = new Controller(game);
			controller.run();  
	    
	    } //Tres "familias" de excepciones nuevas
	    catch(NumberFormatException | IncorrectArgumentsException | GameOverExceptions | TooCellsException e)
	    {    	
	    	if (e instanceof NumberFormatException)
	    		System.err.println("The command-line arguments must be numbers");
	    	else if (e instanceof IncorrectArgumentsException)
	    		System.err.println(e.getMessage());
	    	else if (e instanceof GameOverExceptions)
	    		System.out.println(e.getMessage());
	    	else if (e instanceof TooCellsException)
	    		System.err.println(e.getMessage());
	    }
	}
}
