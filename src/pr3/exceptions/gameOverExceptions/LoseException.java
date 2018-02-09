package tp.pr3.exceptions.gameOverExceptions;

/**
 * 
 * extiende GameOverExceptions
 *
 */
public class LoseException extends GameOverExceptions{
	private static final String ExceptionMessage = "Game Over!";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public LoseException() {
		super(ExceptionMessage);
	}
}
