package tp.pr1;

/**
 * Utilizaremos esta clase para establecer la posici�n de las celdas en el
 * tablero.
 * 
 

 */
public class Position {
	private int x;
	private int y;

	/**
	 * Aqu� se crear� una nueva posici�n con las coordenadas que se pasan por
	 * par�metros.
	 * 
	 * @param a
	 * @param b
	 */
	public Position(int a, int b) {
		this.x = a;
		this.y = b;
	}

	/**
	 * Con este m�todo obtendremos la coordenada en el eje X de esta posici�n.
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Con este m�todo obtendremos la coordenada en el eje Y de este posici�n.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Con este m�todo obtendremos la posici�n situada a la derecha de nuestra
	 * posici�n actual.
	 * 
	 * @return
	 */
	public Position neighbor() {
		return new Position(this.x, this.y + 1); // We only need right neighbor in our case
	}
}