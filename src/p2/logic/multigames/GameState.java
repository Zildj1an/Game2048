package tp.p2.logic.multigames;

/**
 * Esta clase será usada para almacenar estados del juego.
 * 
 * @author Carlos Bilbao
 * @author Álvaro López
 */
public class GameState {
	private int score;
	private int[][] boardState;

	/**
	 * Aquí crearemos una instancia de la clase GameState.
	 */
	public GameState() {
		score = 0;
	}

	/**
	 * Aquí guardaremos la puntuación de estado.
	 * 
	 * @param value
	 */
	public void setScore(int value) {
		score = value;
	}

	/**
	 * Con este método obtendremos la puntuación de un estado.
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Este método devolverá una matriz de números enteros con los valores de las
	 * celdas del tablero.
	 * 
	 * @return
	 */
	public int[][] getBoardState() {
		return boardState;
	}

	/**
	 * Este método almacenará la matriz de números enteros con los valores de las
	 * celdas del tablero en el estado.
	 * 
	 * @param boardState
	 */
	public void setBoardState(int[][] boardState) {
		this.boardState = boardState;
	}
}