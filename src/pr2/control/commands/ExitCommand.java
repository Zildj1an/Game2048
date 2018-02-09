package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;

/**
 * Gracias a esta clase el usuario podr� salir del juego cuando quiera.
 * 
 

 */
public class ExitCommand extends NoParamsCommand {
	private static final String commandInfo = "exit";
	private static final String helpInfo = "This command exits the game.";

	public boolean execute(Game game) {
		game.exit();
		return false;
	}

	/**
	 * Aqu� se crear� un comando tipo Exit utilizando super.
	 */
	public ExitCommand() {
		super(commandInfo, helpInfo);
	}
}