package tp.p2.rules;

import java.util.Random;

import tp.pr1.Board;
import tp.pr1.Cell;
import tp.pr1.Position;
import tp.pr2.util.MyMathsUtil;

/**
 * Esta clase controla las reglas de una modificación del juego 2048. En este
 * caso, en lugar de potencias de 2, los números serán los de la sucesión de
 * Fibonacci.
 * 
 

 */
public class RulesFib implements GameRules {
	private static final int STOP_VALUE = 144;

	public void addNewCellAt(Board board, Position pos, Random rand) {
		int randomValue;
		float decimal = rand.nextFloat();
		if (decimal < 0.9) {
			randomValue = 1;
		} else {
			randomValue = 2;
		}
		board.setCellValue(pos, randomValue);
	}

	/**
	 * Realiza el merge entre dos celdas y devuelve el valor 
	 * lanza una excepción Aritmética si es necesario
	 */
	
	public int merge(Cell self, Cell other){
		if (other.getCellValue() == MyMathsUtil.nextFib(self.getCellValue())
				|| self.getCellValue() == MyMathsUtil.nextFib(other.getCellValue())
				|| (self.getCellValue() == 1 && other.getCellValue() == 1)) {
			other.setCellValue(other.getCellValue() + self.getCellValue());
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
		return (a == MyMathsUtil.nextFib(b) || b == MyMathsUtil.nextFib(a) || a == 0 || b == 0 || (a == 1 && b == 1));
	}
}
