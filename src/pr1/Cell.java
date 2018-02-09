package tp.pr1;

import tp.p2.rules.GameRules;

/**
 * Esta clase servirá para manipular las celdas del tablero.
 * 
 

 */
public class Cell {
	private int cellValue;

	/**
	 * Aquí se creará una nueva celda con valor 0.
	 */
	public Cell() {
		cellValue = 0;
	}

	/**
	 * En este método modificaremos el valor de la celda con el que nos pasen por
	 * parámetro.
	 * 
	 * @param value
	 */
	public void setCellValue(int value) {
		cellValue = value;
	}

	/**
	 * Aquí devolveremos el valor de la celda en la que nos encontramos.
	 * 
	 * @return
	 */
	public int getCellValue() {
		return cellValue;
	}

	/**
	 * Aquí comprobaremos si la celda está vacía, es decir, si su valor es de 0.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (cellValue == 0);
	}

	/**
	 * Este método servirá para mezclar una celda con otra. Funcionará de distinta
	 * forma según las reglas del juego.
	 * 
	 * @param neighbourCell
	 * @param rules
	 * @return
	 */
	public int doMerge(Cell neighbourCell, GameRules rules) throws ArithmeticException{
		return rules.merge(this, neighbourCell);
	}

	/**
	 * Con este método imprimiremos en pantalla el valor de la celda.
	 */
	public String toString() {
		return Integer.toString(cellValue);
	}
}