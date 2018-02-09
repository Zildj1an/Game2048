package tp.pr3.exceptions.newGameExceptions;
/**
 * 
 * extiende NewGameExceptions
 *
 */
public class SingleIntegerException extends NewGameExceptions {
	private static final String ExceptionMessage = " Please provide a single integer or press return";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public SingleIntegerException () {
		super(ExceptionMessage);
	}
}
