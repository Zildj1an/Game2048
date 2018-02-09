package tp.pr2.control.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.p2.logic.multigames.Game;
import tp.pr2.util.MyStringUtils;
import tp.pr3.exceptions.AnswerYNException;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.FewParamsException;
import tp.pr3.exceptions.controllerExceptions.InvalidCharactersException;
import tp.pr3.exceptions.controllerExceptions.ParseException;

/**
 * Gracias a esta clase podremos cargar una partida guardada.
 * 
 

 */
public class SaveCommand extends Command {
	
	private boolean filename_confirmed;
	private String filename;
	public static final String filenameInUseMsg = "The file already exists; do you want to overwrite it ? (Y/N)";
	private static final String commandInfo = "save";
	private static final String helpInfo = "Saves the current state of the game into a file.";

	/**
	 * Aquí se creará un comando tipo Load utilizando super.
	 */
	public SaveCommand() {
		super(commandInfo, helpInfo);
	}
	
	/**
	 * Comienza el proceso de ejecucion del guardado
	 * creando un BufferedWriter out y llamando a game.store(out)
	 */
	public boolean execute(Game game) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		out.write("This file stores a saved 2048 game");
		out.newLine();
		out.newLine();
		game.store(out);
		out.close();
		System.out.println("Game successfully saved to file; use load command to reload it.");
		return false;
	}
	
	/**
	 * parsea con el escaner y los commandWords
	 * lanza excepción de tipo ControllerExceptions
	 */
	public Command parse(String[] commandWords, Scanner user) throws ControllerExceptions{
		
		if (commandWords[0].equalsIgnoreCase(commandName))
		{
			if (commandWords.length == 1)
				throw new FewParamsException("Save must be followed by a filename.");
			
			if (commandWords.length > 2)
				throw new ParseException("Invalid filename: the filename contains spaces");
			
			filename = confirmFileNameStringForWrite(commandWords[1], user);
			return this;
		}
		else
			return null;
	}
	
	/**
	 *  Comprueba que es un valid filename, llamado por askName de esta clase
	 * @param filenameString
	 * @param in
	 * @return loadName
	 * @throws ControllerExceptions
	 */
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws ControllerExceptions { 
		String loadName = filenameString; 
		filename_confirmed = false; 
		while (!filename_confirmed) { 
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName); 
				if (!file.exists())
					filename_confirmed = true;
				else
					loadName = getLoadName(loadName, in);
			}
			else
				throw new InvalidCharactersException();
		}
		return loadName;
	}
	
	/**
	 * Pide el nombre del archivo y comprueba su validez con confirmFileNameStringForWrite
	 * @param in
	 * @return
	 * @throws ControllerExceptions
	 */
	String askName(Scanner in) throws ControllerExceptions{
		String answer = "";
		System.out.println("Please enter another filename:");
		String[] filename = in.nextLine().toLowerCase().trim().split("\\s+");
		if(filename.length > 1)
			throw new ParseException(commandName);
		
		if (!MyStringUtils.validFileName(filename[0]))
			throw new InvalidCharactersException();
			
		answer = filename[0];
		return answer;
	}

	/**
	 * Analiza la respuesta 
	 * @param filenameString
	 * @param in
	 * @return
	 * @throws ControllerExceptions
	 */
	public String getLoadName(String filenameString, Scanner in) throws ControllerExceptions{
		String newFilename = null;
		boolean	yesOrNo = false;
		while(!yesOrNo) {
			System.out.print(filenameInUseMsg +	": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			try
			{
				if (responseYorN.length == 1) {
					switch (responseYorN[0]) {
						case "y":
							yesOrNo = true;
							File archivo = new File(filenameString);
							archivo.delete();
							break;
						case "n":
							yesOrNo = true;
							filenameString = askName(in);
							break;
						default:
							throw new AnswerYNException();
					}
				}
				else {
					throw new AnswerYNException();
				}
			}
			catch (AnswerYNException e)
			{
				System.err.println(e.getMessage());
			}
		}
		newFilename = filenameString;
		return newFilename;
	}
}



	