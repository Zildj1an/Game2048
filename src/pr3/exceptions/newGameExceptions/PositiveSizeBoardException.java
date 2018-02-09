package tp.pr3.exceptions.newGameExceptions;

/**
 * 
 * extiende NewGameExceptions
 *
 */
public class PositiveSizeBoardException extends NewGameExceptions {
	private static final String ExceptionMessage = " The size of the board must be positive";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public PositiveSizeBoardException () {
		super(ExceptionMessage);
	}
}
