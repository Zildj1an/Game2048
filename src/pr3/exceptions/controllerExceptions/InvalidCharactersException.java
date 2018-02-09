package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class InvalidCharactersException extends ControllerExceptions {
	private static final String ExceptionMessage = "Invalid filename: the filename contains invalid characters";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public InvalidCharactersException() {
		super(ExceptionMessage);
	}
}
