package tp.pr1;

/**
 * Utilizaremos esta clase para establecer la posición de las celdas en el
 * tablero.
 * 
 

 */
public class Position {
	private int x;
	private int y;

	/**
	 * Aquí se creará una nueva posición con las coordenadas que se pasan por
	 * parámetros.
	 * 
	 * @param a
	 * @param b
	 */
	public Position(int a, int b) {
		this.x = a;
		this.y = b;
	}

	/**
	 * Con este método obtendremos la coordenada en el eje X de esta posición.
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
	 * Con este método obtendremos la coordenada en el eje Y de este posición.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Con este método obtendremos la posición situada a la derecha de nuestra
	 * posición actual.
	 * 
	 * @return
	 */
	public Position neighbor() {
		return new Position(this.x, this.y + 1); // We only need right neighbor in our case
	}
}