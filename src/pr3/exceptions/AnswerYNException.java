package tp.pr3.exceptions;

/**
 * 
 * extiende Exception para pedir al usuario
 * que conteste con Y o N
 *
 */
public class AnswerYNException extends Exception {
	private static final String ExceptionMessage = "Please answer ’Y’ or ’N’";
	
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public AnswerYNException () {
		super(ExceptionMessage);
	}
}
