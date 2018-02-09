package tp.pr3.exceptions.gameOverExceptions;

/**
 * 
 * extiende Exception
 *
 */
public abstract class GameOverExceptions extends Exception {
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public GameOverExceptions(String message) {
		super(message);
	}
}