package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;

/**
 * Gracias a esta clase el usuario podrá salir del juego cuando quiera.
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
	 * Aquí se creará un comando tipo Exit utilizando super.
	 */
	public ExitCommand() {
		super(commandInfo, helpInfo);
	}
}