package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class FileNotFoundException extends ControllerExceptions {
	private static final String ExceptionMessage = "File not found";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public FileNotFoundException() {
		super(ExceptionMessage);
	}
}
