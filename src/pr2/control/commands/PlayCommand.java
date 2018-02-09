package tp.pr2.control.commands;

import java.util.Random;
import java.util.Scanner;

import tp.p2.logic.multigames.Game;
import tp.p2.logic.multigames.GameType;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.FewParamsException;
import tp.pr3.exceptions.controllerExceptions.ParseException;
import tp.pr3.exceptions.gameOverExceptions.LoseException;
import tp.pr3.exceptions.newGameExceptions.NewGameExceptions;
import tp.pr3.exceptions.newGameExceptions.PositiveInitialCellsException;
import tp.pr3.exceptions.newGameExceptions.PositiveSizeBoardException;
import tp.pr3.exceptions.newGameExceptions.SingleIntegerException;
import tp.pr3.exceptions.newGameExceptions.SinglePositiveIntegerException;
import tp.pr3.exceptions.newGameExceptions.TooCellsException;

/**
 * Gracias a esta clase podremos cambiar los parámetros del juego.
 * 
 

 */
public class PlayCommand extends Command {
	protected GameType tipo;
	protected long randomSeed;
	protected int boardSize;
	protected int initialCells;
	private static final String commandInfo = "play";
	private static final String helpInfo = "Starts a new game of one of the game types: " + GameType.externaliseAll() + ".";

	/**
	 * Aquí se creará un comando tipo Play utilizando super.
	 */
	public PlayCommand() {
		super(commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws LoseException {
		game.setNewGame(randomSeed, boardSize, initialCells, tipo);
		return true;
	}

	/**
	 * Este método comprobará si la entrada del usuario coincide con la del comando
	 * PLay. Si es así, tendrá que utilizar el Scanner de controller para que el
	 * usuario introduzca los parámetros del nuevo juego.
	 * @throws FewParamsException 
	 */
	public Command parse(String[] commandWords, Scanner user)throws ControllerExceptions {
		String[] palabras;
		String input;
		boolean sizeOK = false, cellsOK = false, seedOK = false;

		if (commandWords[0].equalsIgnoreCase(commandName))
		{
			if (commandWords.length == 1)
				throw new FewParamsException("Play must be followed by a game type: " + GameType.externaliseAll() + ".");
			
			if(commandWords.length > 2)
				throw new ParseException("Unknown game type for play command");
					
			tipo = GameType.parse(commandWords[1]);
			
			System.out.println("*** You have chosen to play the game: " + tipo.toString() + " ***");
			while (!sizeOK) {
				System.out.print("Please enter the size of the board: ");
				input = user.nextLine().toLowerCase().trim();
				palabras = input.split("\\s+");
				try
				{
					if (palabras.length != 1)
						throw new SinglePositiveIntegerException();
					
					if (palabras.length == 1 && palabras[0].equalsIgnoreCase("")) {
						boardSize = 4;
						System.out.println(" Using the default size of the board: " + boardSize);
						sizeOK = true;
					}
					else { 
						if (Integer.parseInt(palabras[0]) <= 0)
							throw new PositiveSizeBoardException();
					
						boardSize = Integer.parseInt(palabras[0]);
						sizeOK = true;
					}
				}
				catch (NewGameExceptions | NumberFormatException e)
				{
					if (e instanceof NumberFormatException)
						System.err.println(" The size of the board must be a number");
					else
						System.err.println(e.getMessage());
				}
			}
			while (!cellsOK) {
				System.out.print("Please enter the number of initial cells: ");
				input = user.nextLine().toLowerCase().trim();
				palabras = input.split("\\s+");
				try
				{
					if (palabras.length != 1) 
						throw new SinglePositiveIntegerException();
					
					if (palabras.length == 1 && palabras[0].equalsIgnoreCase("")) {
						initialCells = 2;
						System.out.println(" Using the default number of initial cells: " + initialCells);
						cellsOK = true;
					}
					else {
						if (Integer.parseInt(palabras[0]) <= 0)
							throw new PositiveInitialCellsException();
						else if (Integer.parseInt(palabras[0]) > (boardSize * boardSize))
							throw new TooCellsException();
					
						initialCells = Integer.parseInt(palabras[0]);
						cellsOK = true;	
					}
				}
				catch (NewGameExceptions | NumberFormatException e)
				{
					if (e instanceof NumberFormatException)
						System.err.println(" The size of the board must be a number");
					else
						System.err.println(e.getMessage());
				}
			}
			while (!seedOK) {
				System.out.print("Please enter the seed for the pseudo-random number generator:");
				input = user.nextLine().toLowerCase().trim();
				palabras = input.split("\\s+");
				
				try
				{
					if (palabras.length != 1)
						throw new SingleIntegerException();
					
					if (palabras.length == 1 && palabras[0].equalsIgnoreCase("")) {
						Random random = new Random();
						randomSeed = random.nextInt();
						System.out.println(" Using the default seed for the pseudo-random number generator: " + randomSeed);
						seedOK = true;
					}
					else {
						randomSeed = Long.parseLong(palabras[0]);
						seedOK = true;
					}
				}
				catch (NewGameExceptions | NumberFormatException e)
				{
					if (e instanceof NumberFormatException)
						System.err.println("The seed for the pseudo-random number generator must be a number");
					else
						System.err.println(e.getMessage());
				}
			}
			return this;
		} 
		else
			return null;
	}
}