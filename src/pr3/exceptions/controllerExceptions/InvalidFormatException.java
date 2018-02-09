package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class InvalidFormatException extends ControllerExceptions {
	private static final String ExceptionMessage = "Load failed: invalid file format";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public InvalidFormatException() {
		super(ExceptionMessage);
	}
}
