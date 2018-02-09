package tp.pr3.exceptions.controllerExceptions;

/**
 * 
 * extiende Exception
 *
 */
public abstract class ControllerExceptions extends Exception {
	
	/**
	 * constructor que llama a la superclase
	 * con su mensaje especifico
	 */
	public ControllerExceptions(String message) {
		super(message);
	}
}