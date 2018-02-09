package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;
import tp.pr3.exceptions.controllerExceptions.EmptyStackException;

/**
 * Gracias a esta clase podremos deshacer un movimiento.
 * 
 

 */
public class UndoCommand extends NoParamsCommand {
	private static final String commandInfo = "undo";
	private static final String helpInfo = "Restores the state of the game previous to executing the last move.";

	/**
	 * Aquí se creará un comando tipo Undo utilizando super.
	 */
	public UndoCommand() {
		super(commandInfo, helpInfo);
	}
	
	/**
	 * para el undo llama a game.undo
	 * lanza exepcion de tipo EmptyStackException
	 */
	public boolean execute(Game game) throws EmptyStackException {
		game.undo();
		return true;
	}
}