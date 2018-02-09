package tp.pr3.exceptions.gameOverExceptions;

/**
 * 
 * extiende GameOverExceptions
 *
 */
public class WinException extends GameOverExceptions{
	private static final String ExceptionMessage = "Congratulations! You have won!";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public WinException() {
		super(ExceptionMessage);
	}
}
