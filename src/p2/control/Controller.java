package tp.p2.control;

import java.io.IOException;
import java.util.Scanner;

import tp.p2.logic.multigames.Game;
import tp.pr2.control.commands.Command;
import tp.pr2.control.commands.CommandParser;
import tp.pr3.exceptions.controllerExceptions.ControllerExceptions;
import tp.pr3.exceptions.gameOverExceptions.GameOverExceptions;

/**
 * Desde esta clase controlaremos el juego ejecutando los comandos que
 * introduzca el usuario.
 * 
 * @author Carlos Bilbao
 * @author Álvaro López
 *
 */
public class Controller {
	private Game game;
	private Scanner user;

	public Controller(Game game) {
		this.game = game;
		user = new Scanner(System.in);
	}

	/**
	 * Este método hará que el juego esté en funcionamiento. Leerá los comandos y
	 * los ejecutará, imprimirá el tablero y comprobará si el jugador ha ganado o
	 * perdido la partida.
	 */
	public void run() {
		String userAns;
		String palabras[];
		Command command;
		System.out.println(game);

		while (!game.getExit()) {
			System.out.print("Command > ");
			userAns = user.nextLine();
			userAns = userAns.trim();
			palabras = userAns.split("\\s+");
			try
			{
				command = CommandParser.parseCommand(palabras, user);
				if(command.execute(game))
					System.out.println(game);
			} 
			catch(ControllerExceptions | IOException | GameOverExceptions e) 
			{
				if (e instanceof IOException)
			  		System.err.println("Sorry, problem with the input/output");
				else if (e instanceof ControllerExceptions)
					System.err.println(e.getMessage());
				else if (e instanceof GameOverExceptions)
					System.out.println(e.getMessage());
			}
		}
		
		System.out.println("Good bye!");
		user.close();
	}
}