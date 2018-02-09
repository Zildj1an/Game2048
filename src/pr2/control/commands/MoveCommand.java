package tp.pr2.control.commands;

import java.util.Scanner;

import tp.p2.logic.multigames.Game;
import tp.pr1.Direction;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.FewParamsException;
import tp.pr3.exceptions.controllerExceptions.ParseException;
import tp.pr3.exceptions.gameOverExceptions.LoseException;
import tp.pr3.exceptions.gameOverExceptions.WinException;

/**
 * Gracias a esta clase podremos movernos en el tablero en una dirección.
 

 */
public class MoveCommand extends Command
{
	private Direction dir;
	private static final String commandInfo = "move";
	private static final String helpInfo = "This command executes a movement in the given direction.";
	
	/**
	 * Aquí se creará un comando tipo Move utilizando super.
	 */
	public MoveCommand()
	{
		super(commandInfo,helpInfo);
	}
	
	/**
	 * Llama a game.move(dir) para ejecutarlo
	 * lanza excepciones de tipo WinException, LoseException
	 */
	public boolean execute(Game game) throws WinException, LoseException
	{
		return game.move(this.dir);
	}

	/**
	 * parsea con el escaner y los commandWords
	 * lanza excepción de tipo ControllerExceptions
	 */
	public Command parse(String[] commandWords, Scanner user) throws ControllerExceptions {
		
		if (commandWords[0].equalsIgnoreCase(commandName))
		{
			if (commandWords.length == 1)
				throw new FewParamsException("Move must be followed by a direction: " + Direction.externaliseAll() + ".");
			
			if (commandWords.length > 2)
				throw new ParseException("Unknown direction for move command");
			
			dir = Direction.parse(commandWords[1]);
			
			return this;		
		}
		else
			return null;
	}
}