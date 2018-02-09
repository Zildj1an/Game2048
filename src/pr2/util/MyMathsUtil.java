package tp.pr2.util;

/**
 * Esta clase ayudar� al funcionamientod el juego con distintas utilidades
 * matem�ticas como la sucesi�n de Fibonacci.
 * 
 

 */
public class MyMathsUtil {
	// Convert from long to int since we will not need large numbers
	/**
	 * Este m�todo nos devolver� el siguiente n�mero en la sucesi�n de Fibonacci al
	 * que nos pase por par�metro.
	 * 
	 * @param previous
	 * @return
	 */
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}
}