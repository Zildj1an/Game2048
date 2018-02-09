package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;
import tp.pr3.exceptions.controllerExceptions.EmptyStackException;

/**
 * Gracias a esta clase podremos rehacer un movimiento previamente deshecho.
 * 
 

 */
public class RedoCommand extends NoParamsCommand {

	private static final String commandInfo = "redo";
	private static final String helpInfo = "Allows a previously undone command to be re-executed.";

	/**
	 * Aquí se creará un comando tipo Redo utilizando super.
	 */
	public RedoCommand() {
		super(commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws EmptyStackException {
		game.redo();
		return true;
	}
}
