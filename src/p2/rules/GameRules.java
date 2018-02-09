package tp.p2.rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;
import tp.pr2.util.ArrayAsList;

/**
 * Esta interfaz se utilizará para controlar las reglas de cada juego.
 * 
 */
public interface GameRules {
	/**
	 * Este método añadirá una nueva celda en la posición que le pasen por
	 * parámetro. Urilizará el parámetro rand para crear el valor que le asignará a
	 * esa celda según las reglas del juego.
	 * 
	 * @param board
	 * @param pos
	 * @param rand
	 */
	void addNewCellAt(Board board, Position pos, Random rand);

	/**
	 * Este método se encargará de hacer la mezcá entre los valores de dos celdas
	 * adyacentes.
	 * 
	 * @param self
	 * @param other
	 * @return
	 */
	int merge(Cell self, Cell other);

	/**
	 * Este método devuelve el mejor valor del tablero según las reglas del juego.
	 * 
	 * @param board
	 * @return
	 */
	int getWinValue(Board board);

	/**
	 * Este método comprueba si el usuario ha ganado la partida. Dependerá de las
	 * reglas de juego.
	 * 
	 * @param board
	 * @return
	 */
	boolean win(Board board);

	/**
	 * Este método devolverá true cuando se pueda hacer una mezcla entre los valores
	 * 'a' y 'b'. Será distntio según las reglas del juego.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	boolean moveCondition(int a, int b);

	/**
	 * Este método comprobará si el usuario ha perdido. Para ello, comprobará si es
	 * posible realizar un nuevo movimiento.
	 * 
	 * @param board
	 * @return
	 */
	default boolean lose(Board board) {
		return !board.possibleMove(this);
	}

	/**
	 * Este método creará un nuevo tablero del tamaño que se pase por parámetro.
	 * 
	 * @param size
	 * @return
	 */
	default Board createBoard(int size) {
		return new Board(size);
	}

	/**
	 * Este método servirá para añadir una nueva celda al tablero llamando al método
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
	 * Con este método inicializaremos el tablero con tantas celdas como nos indique
	 * el parámetro numCells. Para ello llamará al método addNewCellAt.
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
