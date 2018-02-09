package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class UnknownCommandException extends ControllerExceptions {
	private static final String ExceptionMessage = "Unknown command. Use ’help’ to see the available commands";
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public UnknownCommandException() {
		super(ExceptionMessage);
	}
}
