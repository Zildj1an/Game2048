package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;
import tp.pr3.exceptions.gameOverExceptions.LoseException;

/**
 * Gracias a esta clase podremos empezar de nuevo un juego con los mismos
 * par�metros que se introdujeron la �ltima vez.
 * 
 

 */
public class ResetCommand extends NoParamsCommand {
	private static final String commandInfo = "reset";
	private static final String helpInfo = "This command starts a new game with the same parameters.";

	public boolean execute(Game game) throws LoseException {
		game.reset();
		return true;
	}

	/**
	 * Aqu� se crear� un comando tipo Reset utilizando super.
	 */
	public ResetCommand() {
		super(commandInfo, helpInfo);
	}
}