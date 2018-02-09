package tp.pr2.control.commands;

import java.util.Scanner;

/**
 * De esta clase heredarán todos los tipos de comandos que no tenga ningún tipo de parámetro.
 

 */
public abstract class NoParamsCommand extends Command
{
	/**
	 * Aquí se utilizará super para crear cada tipo de comando.
	 * @param commandInfo
	 * @param helpInfo
	 */
	NoParamsCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo,helpInfo);
	}
	
	/**
	 * parsea con el escaner y los commandWords
	 * devuelve null en caso de que no sea el caso
	 */
	public Command parse(String[] commandWords, Scanner user)
	{
		if (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName))
			return this;
		else
			return null;
	}
}