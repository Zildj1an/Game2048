package tp.pr2.control.commands;

import java.util.Scanner;

import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.UnknownCommandException;

/**
 * Esta clase servirá para mostrar la ayuda y para comprobar si existe algún
 * comando que coincida con las Strings que ha introducido el usuario en
 * Controller.
 * 
 

 */
public class CommandParser {
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(),
			new MoveCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand(), new SaveCommand(), new LoadCommand() };

	/**
	 * Este método comrueba si existe algún comando que coincida con las Strings que
	 * ha introducido el usuario en Controller.
	 * 
	 * @param commandWords
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public static Command parseCommand(String[] commandWords, Scanner user) throws ControllerExceptions {
		for (Command c : availableCommands) {
			if (c.parse(commandWords, user) != null)
				return c;
		}
		throw new UnknownCommandException();
	}

	/**
	 * Este método imprime la ayuda para el usuario.
	 * 
	 * @return
	 */
	public static String commandHelp() {
		String help = "";

		for (Command c : availableCommands) {
			help += c.helpText();
		}

		return help;
	}
}