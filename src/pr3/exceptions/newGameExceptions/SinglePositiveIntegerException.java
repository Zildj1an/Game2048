package tp.pr3.exceptions.newGameExceptions;

/**
 * 
 * extiende NewGameExceptions
 *
 */
public class SinglePositiveIntegerException extends NewGameExceptions {
	private static final String ExceptionMessage = " Please provide a single positive integer or press return";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public SinglePositiveIntegerException () {
		super(ExceptionMessage);
	}
}
