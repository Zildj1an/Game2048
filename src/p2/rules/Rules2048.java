package tp.p2.rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;

/**
 * Esta clase controla las reglas del juego original 2048.
 * 
 

 */
public class Rules2048 implements GameRules {
	private static final int STOP_VALUE = 2048;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int randomValue;
		float decimal = rand.nextFloat();
		if (decimal < 0.9) {
			randomValue = 2;
		} else {
			randomValue = 4;
		}
		board.setCellValue(pos, randomValue);
	}

	/**
	 * Realiza el merge entre dos celdas y devuelve el valor 
	 * lanza una excepción Aritmética si es necesario
	 */
	
	public int merge(Cell self, Cell other) throws ArithmeticException{
		if (self.getCellValue() == other.getCellValue()) {
			other.setCellValue(self.getCellValue() * 2);
			self.setCellValue(0);
			return other.getCellValue();
		} else
			return 0;
	}

	/**
	 *  devuelve el "valor maximo" del board
	 */
	public int getWinValue(Board board) {
		return board.getMaxValue();
	}
	
	/**
	 * comprueba que el valor de getWinValue coincida con la constante STOP_VALUE
	 */
	public boolean win(Board board) {
		return (getWinValue(board) == STOP_VALUE);
	}

	/**
	 * condicón necesaria para mover (iguales, o una es cero)
	 */
	public boolean moveCondition(int a, int b) {
		return (a == b || a == 0 || b == 0);
	}
}
