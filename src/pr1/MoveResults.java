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
	 * Aquí se creará una instancia de esta clase.
	 */
	public MoveResults() {
		scored = 0;
		moved = false;
	}

	/**
	 * Aquí obtendremos la puntuación obtenida al realizar el movimiento.
	 * 
	 * @return
	 */
	public int getScored() {
		return this.scored;
	}

	/**
	 * Aquí podremos almacenar los puntos que se obtengan cada vez que se efectúe
	 * una mezcla al realizar un movimiento.
	 * 
	 * @param num
	 */
	public void setScored(int num) {
		this.scored += num;
	}

	/**
	 * En este método se establece si efectivamente se ha producido un movimiento o
	 * el tablero permanece igual.
	 * 
	 * @param moved
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	/**
	 * Con este método podremos saber si efectivamente se ha realizado un
	 * movimiento.
	 * 
	 * @return
	 */
	public boolean getMoved() {
		return this.moved;
	}
}