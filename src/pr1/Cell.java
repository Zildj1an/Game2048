package tp.pr1;

import tp.p2.rules.GameRules;

/**
 * Esta clase servir� para manipular las celdas del tablero.
 * 
 

 */
public class Cell {
	private int cellValue;

	/**
	 * Aqu� se crear� una nueva celda con valor 0.
	 */
	public Cell() {
		cellValue = 0;
	}

	/**
	 * En este m�todo modificaremos el valor de la celda con el que nos pasen por
	 * par�metro.
	 * 
	 * @param value
	 */
	public void setCellValue(int value) {
		cellValue = value;
	}

	/**
	 * Aqu� devolveremos el valor de la celda en la que nos encontramos.
	 * 
	 * @return
	 */
	public int getCellValue() {
		return cellValue;
	}

	/**
	 * Aqu� comprobaremos si la celda est� vac�a, es decir, si su valor es de 0.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (cellValue == 0);
	}

	/**
	 * Este m�todo servir� para mezclar una celda con otra. Funcionar� de distinta
	 * forma seg�n las reglas del juego.
	 * 
	 * @param neighbourCell
	 * @param rules
	 * @return
	 */
	public int doMerge(Cell neighbourCell, GameRules rules) throws ArithmeticException{
		return rules.merge(this, neighbourCell);
	}

	/**
	 * Con este m�todo imprimiremos en pantalla el valor de la celda.
	 */
	public String toString() {
		return Integer.toString(cellValue);
	}
}