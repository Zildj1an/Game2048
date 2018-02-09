package tp.pr2.control.commands;

import java.io.IOException;
import java.util.Scanner;
import tp.p2.logic.multigames.Game;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.EmptyStackException;
import tp.pr3.exceptions.controllerExceptions.InvalidFormatException;
import tp.pr3.exceptions.gameOverExceptions.LoseException;
import tp.pr3.exceptions.gameOverExceptions.WinException;

/**
 * Esta ser� la clase padre de la que heredar�n todos los tipos de comandos.
 * 
 

 */
public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;

	/**
	 * Aqu� se crear� una instancia de un tipo de comando, con su propio nombre y
	 * texto de ayuda.
	 * 
	 * @param commandInfo
	 * @param helpInfo
	 */
	public Command(String commandInfo, String helpInfo) {
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}

	/**
	 * Este m�todo ejecutar� el comando.
	 * 
	 * @param game
	 * @param controller
	 * @throws IOException 
	 * @throws LoseException 
	 * @throws WinException 
	 * @throws Exception 
	 */
	public abstract boolean execute(Game game) throws InvalidFormatException, EmptyStackException, IOException, WinException, LoseException;

	/**
	 * Este m�todo comprobar� las Strings que el usuario ha introducido coinciden
	 * con las del tipo de comando.
	 * 
	 * @param commandWords
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public abstract Command parse(String[] commandWords, Scanner user) throws ControllerExceptions;

	/**
	 * Este m�todo imprimir� el texto de ayuda que ser� ejecutado cuando el usuario
	 * introduzca 'help'.
	 * 
	 * @return
	 */
	public String helpText() {
		if (commandText == "move")
			commandText += " <direction>";
		if (commandText == "play")
			commandText += " <game>";
		if ((commandText == "load") || (commandText == "save")) 
			commandText += " <filename>";
		return "   " + commandText + ": " + helpText + "\n";
	}
}