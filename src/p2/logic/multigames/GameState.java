package tp.p2.logic.multigames;

/**
 * Esta clase ser� usada para almacenar estados del juego.
 * 
 * @author Carlos Bilbao
 * @author �lvaro L�pez
 */
public class GameState {
	private int score;
	private int[][] boardState;

	/**
	 * Aqu� crearemos una instancia de la clase GameState.
	 */
	public GameState() {
		score = 0;
	}

	/**
	 * Aqu� guardaremos la puntuaci�n de estado.
	 * 
	 * @param value
	 */
	public void setScore(int value) {
		score = value;
	}

	/**
	 * Con este m�todo obtendremos la puntuaci�n de un estado.
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Este m�todo devolver� una matriz de n�meros enteros con los valores de las
	 * celdas del tablero.
	 * 
	 * @return
	 */
	public int[][] getBoardState() {
		return boardState;
	}

	/**
	 * Este m�todo almacenar� la matriz de n�meros enteros con los valores de las
	 * celdas del tablero en el estado.
	 * 
	 * @param boardState
	 */
	public void setBoardState(int[][] boardState) {
		this.boardState = boardState;
	}
}