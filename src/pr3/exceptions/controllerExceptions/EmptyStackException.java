package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class EmptyStackException extends ControllerExceptions {
	
	public EmptyStackException() {
		super("");
	}
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public EmptyStackException(String commandName) {
		super(commandName);
	}
}
