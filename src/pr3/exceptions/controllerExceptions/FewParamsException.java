package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende ControllerExceptions
 *
 */
public class FewParamsException extends ControllerExceptions {
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public FewParamsException(String commandName) {
		super(commandName);
	}
}
