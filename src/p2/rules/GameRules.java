package tp.p2.rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;
import tp.pr2.util.ArrayAsList;

/**
 * Esta interfaz se utilizar� para controlar las reglas de cada juego.
 * 
 */
public interface GameRules {
	/**
	 * Este m�todo a�adir� una nueva celda en la posici�n que le pasen por
	 * par�metro. Urilizar� el par�metro rand para crear el valor que le asignar� a
	 * esa celda seg�n las reglas del juego.
	 * 
	 * @param board
	 * @param pos
	 * @param rand
	 */
	void addNewCellAt(Board board, Position pos, Random rand);

	/**
	 * Este m�todo se encargar� de hacer la mezc� entre los valores de dos celdas
	 * adyacentes.
	 * 
	 * @param self
	 * @param other
	 * @return
	 */
	int merge(Cell self, Cell other);

	/**
	 * Este m�todo devuelve el mejor valor del tablero seg�n las reglas del juego.
	 * 
	 * @param board
	 * @return
	 */
	int getWinValue(Board board);

	/**
	 * Este m�todo comprueba si el usuario ha ganado la partida. Depender� de las
	 * reglas de juego.
	 * 
	 * @param board
	 * @return
	 */
	boolean win(Board board);

	/**
	 * Este m�todo devolver� true cuando se pueda hacer una mezcla entre los valores
	 * 'a' y 'b'. Ser� distntio seg�n las reglas del juego.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	boolean moveCondition(int a, int b);

	/**
	 * Este m�todo comprobar� si el usuario ha perdido. Para ello, comprobar� si es
	 * posible realizar un nuevo movimiento.
	 * 
	 * @param board
	 * @return
	 */
	default boolean lose(Board board) {
		return !board.possibleMove(this);
	}

	/**
	 * Este m�todo crear� un nuevo tablero del tama�o que se pase por par�metro.
	 * 
	 * @param size
	 * @return
	 */
	default Board createBoard(int size) {
		return new Board(size);
	}

	/**
	 * Este m�todo servir� para a�adir una nueva celda al tablero llamando al m�todo
	 * addNewCellAt
	 * 
	 * @param board
	 * @param rand
	 */
	default void addNewCell(Board board, Random rand){
		ArrayAsList list = new ArrayAsList(board.getSize());
		list = board.freeList(list);
		Position pos1 = (Position) ArrayAsList.choice(list, rand);
		addNewCellAt(board, pos1, rand);
	}

	/**
	 * Con este m�todo inicializaremos el tablero con tantas celdas como nos indique
	 * el par�metro numCells. Para ello llamar� al m�todo addNewCellAt.
	 * 
	 * @param board
	 * @param numCells
	 * @param rand
	 */
	default void initBoard(Board board, int numCells, Random rand){
		ArrayAsList list = new ArrayAsList(board.getSize());
		list = board.freeList(list);
		ArrayAsList.shuffle(list, rand); // For random list shuffle
		Position pos1;
		for (int i = 0; i < numCells; i++) {
			pos1 = (Position) list.get(i); // Random position
			addNewCellAt(board, pos1, rand);
		}
	}
}
