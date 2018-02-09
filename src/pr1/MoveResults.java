package tp.pr1;

/**
 * Gracias a esta clase podremos almacenar los resultados de realizar un
 * movimiento.
 * 
 

 */
public class MoveResults {
	private boolean moved;
	private int scored;

	/**
	 * Aqu� se crear� una instancia de esta clase.
	 */
	public MoveResults() {
		scored = 0;
		moved = false;
	}

	/**
	 * Aqu� obtendremos la puntuaci�n obtenida al realizar el movimiento.
	 * 
	 * @return
	 */
	public int getScored() {
		return this.scored;
	}

	/**
	 * Aqu� podremos almacenar los puntos que se obtengan cada vez que se efect�e
	 * una mezcla al realizar un movimiento.
	 * 
	 * @param num
	 */
	public void setScored(int num) {
		this.scored += num;
	}

	/**
	 * En este m�todo se establece si efectivamente se ha producido un movimiento o
	 * el tablero permanece igual.
	 * 
	 * @param moved
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	/**
	 * Con este m�todo podremos saber si efectivamente se ha realizado un
	 * movimiento.
	 * 
	 * @return
	 */
	public boolean getMoved() {
		return this.moved;
	}
}