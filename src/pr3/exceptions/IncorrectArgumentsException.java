package tp.pr3.exceptions;

/**
 * 
 * extiende Exception para pedir al usuario
 * que conteste con argumentos correctos
 *
 */
public class IncorrectArgumentsException extends Exception {
	private static final String ExceptionMessage = "Usage: ./game2048 <size> <cells> [<seed>]";
	
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public IncorrectArgumentsException () {
		super(ExceptionMessage);
	}
}
