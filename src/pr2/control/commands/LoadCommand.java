package tp.pr2.control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tp.p2.logic.multigames.Game;
import tp.p2.logic.multigames.GameType;
import tp.pr2.util.MyStringUtils;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.controllerExceptions.FewParamsException;
import tp.pr3.exceptions.controllerExceptions.FileNotFoundException;
import tp.pr3.exceptions.controllerExceptions.InvalidCharactersException;
import tp.pr3.exceptions.controllerExceptions.InvalidFormatException;
import tp.pr3.exceptions.controllerExceptions.ParseException;

/**
 * Gracias a esta clase podremos cargar una partida guardada.
 * 
 

 */
public class LoadCommand extends Command {
	
	private static final String commandInfo = "load";
	private static final String helpInfo = "Loads the state of the game previously stored in a file.";
	private String filename;
	/**
	 * Aquí se creará un comando tipo Load utilizando super.
	 */
	public LoadCommand() {
		super(commandInfo, helpInfo);
	}
	
	/**
	 * Crea un BufferedReader para leer el archivo recibido
	 * utiliza ademas el load de la clase game
	 * avisa de que se ha completado satisfactoriamente este proceso
	 * lanza excepciones InvalidFormatException, IOException
	 */
	public boolean execute(Game game) throws InvalidFormatException, IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		try{
			String title = in.readLine();
			if(!title.equals("This file stores a saved 2048 game"))
				throw new InvalidFormatException();
			
			title = in.readLine();
			if (!title.equals(""))
				throw new InvalidFormatException();
			
			GameType tipo = game.load(in);
			System.out.println("Game successfully loaded from file: " + tipo.toString());
			in.close();
			return true;
		}
		catch (InvalidFormatException ex) {
			throw new InvalidFormatException();
		}
		finally {
			in.close();
		}
	}

	/**
	 * parsea con el escaner y los commandWords
	 * lanza excepción de tipo ControllerExceptions
	 */
	public Command parse(String[] commandWords, Scanner user) throws ControllerExceptions{
		
		if (commandWords[0].equalsIgnoreCase(commandName))
		{
			if (commandWords.length == 1)
				throw new FewParamsException("Load must be followed by a filename.");
			
			if (commandWords.length > 2)
				throw new ParseException("Invalid filename: the filename contains spaces");
				
			if(MyStringUtils.validFileName(commandWords[1])){
				File file = new File(commandWords[1]); 
				if (file.exists()){
					filename = commandWords[1];
					return this;
				}
				else
					throw new FileNotFoundException();
			}
			else
				throw new InvalidCharactersException();
		}
		else
			return null;
	}
}