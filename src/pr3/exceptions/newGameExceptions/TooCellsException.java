package tp.pr3.exceptions.newGameExceptions;
/**
 * 
 * extiende NewGameExceptions
 *
 */
public class TooCellsException extends NewGameExceptions {
	private static final String ExceptionMessage = " The number of initial cells must be less or equal to the number of cells on the board";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public TooCellsException () {
		super(ExceptionMessage);
	}
}
