package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class ParseException extends ControllerExceptions {
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public ParseException(String commandName) {
		super(commandName);
	}
}