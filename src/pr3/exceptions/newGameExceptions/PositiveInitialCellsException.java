package tp.pr3.exceptions.newGameExceptions;

/**
 * 
 * extiende NewGameExceptions
 *
 */
public class PositiveInitialCellsException extends NewGameExceptions {
	private static final String ExceptionMessage = " The number of initial cells must be positive";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public PositiveInitialCellsException () {
		super(ExceptionMessage);
	}
}
