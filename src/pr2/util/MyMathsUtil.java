package tp.pr2.util;

/**
 * Esta clase ayudará al funcionamientod el juego con distintas utilidades
 * matemáticas como la sucesión de Fibonacci.
 * 
 

 */
public class MyMathsUtil {
	// Convert from long to int since we will not need large numbers
	/**
	 * Este método nos devolverá el siguiente número en la sucesión de Fibonacci al
	 * que nos pase por parámetro.
	 * 
	 * @param previous
	 * @return
	 */
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}
}