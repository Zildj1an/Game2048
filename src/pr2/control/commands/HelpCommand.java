package tp.pr2.control.commands;

import tp.p2.logic.multigames.Game;

/**
 * Gracias a esta clase el usuario podrá ver qué comandos están disponibles en
 * el juego.
 * 
 

 */
class HelpCommand extends NoParamsCommand {
	private static final String commandInfo = "help";
	private static final String helpInfo = "This command displays this manual.";

	/**
	 * Aquí se creará un comando tipo Help utilizando super.
	 */
	public HelpCommand() {
		super(commandInfo, helpInfo);
	}

	public boolean execute(Game game) {
		System.out.println("The available commands are:");
		System.out.println(CommandParser.commandHelp());
		return false;
	}
}