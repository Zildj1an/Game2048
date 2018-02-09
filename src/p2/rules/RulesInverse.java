package tp.p2.rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;

/**
 * En esta clase se controlarán las reglas del una modificación del juego 2048.
 * En este caso se empezará desde el 2048 y el objetivo será
 * alcanzar el 1.
 * 
 

 */
public class RulesInverse implements GameRules {
	private static final int STOP_VALUE = 1;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int randomValue;
		float decimal = rand.nextFloat();
		if (decimal < 0.9) {
			randomValue = 2048;
		} else {
			randomValue = 1024;
		}
		board.setCellValue(pos, randomValue);
	}

	public int merge(Cell self, Cell other) throws ArithmeticException{
		if (self.getCellValue() == other.getCellValue()) {
			int puntos = self.getCellValue();
			other.setCellValue(self.getCellValue() / 2);
			self.setCellValue(0);
			return (2048 * 2) / puntos;
		} else
			return 0;
	}
	/**
	 *  devuelve el "valor maximo" del board
	 */
	public int getWinValue(Board board) {
		return board.getMinValue();
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
