package tp.pr1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import tp.p2.rules.GameRules;
import tp.pr2.util.ArrayAsList;
import tp.pr2.util.MyStringUtils;
import tp.pr3.exceptions.controllerExceptions.InvalidFormatException;

/**
 * Esta clase servirá para modificar el tablero de la partida.
 * 
 

 */
public class Board {
	private Cell[][] TheBoard;
	private int boardSize;

	/**
	 * Aquí crearemos un tablero de un tamaño determinado.
	 * 
	 * @param size
	 */
	public Board(int size) {
		boardSize = size;
		TheBoard = new Cell[size][size];
		initializeBoard(TheBoard, boardSize);
	}

	/**
	 * Aquí podremos obtener el tamaño del tablero.
	 * 
	 * @return
	 */
	public int getSize() {
		return boardSize;
	}

	/**
	 * Este método devolverá una lista con las posiciones vacías del tablero.
	 * 
	 * @param list
	 * @return
	 */
	public ArrayAsList freeList(ArrayAsList list) {
		list.erase();
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				if (TheBoard[i][j].isEmpty())
					list.add(new Position(i, j));
		return list;
	}
	
	/**
	 * Inicializa el board con nuevas celdas
	 * @param board
	 * @param size
	 */
	private final void initializeBoard(Cell[][] board, int size) {
		for (int i = 0; i < size; ++i)
			for (int j = 0; j < size; ++j)
				board[i][j] = new Cell();
	}

	/**
	 * Este método servirá establecer el estado actual del tablero utilizando otro
	 * estado.
	 * 
	 * @param aState
	 */
	public void setState(int[][] aState) {
		for (int i = 0; i < boardSize; ++i)
			for (int j = 0; j < boardSize; ++j)
				TheBoard[i][j].setCellValue(aState[i][j]);
	}

	/**
	 * Este método devuelve el estado actual del tablero, es decir, los valores de
	 * cada una de sus celdas.
	 * 
	 * @return
	 */
	public int[][] getState() {
		int matrix[][] = new int[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				matrix[i][j] = TheBoard[i][j].getCellValue();

		return matrix;
	}
	
	/**
	 * Llamado por el load de Game para leer el archivo de texto 
	 * con la partida.
	 * @param in
	 * @throws InvalidFormatException
	 */
	public void load(BufferedReader in) throws InvalidFormatException{
		try
		{
			String title = "";
			String row[];
			title = in.readLine();
			row = title.trim().split("\\s+");
			int size = row.length;
			Cell[][] newBoard = new Cell[size][size];
			initializeBoard(newBoard, size);
			for(int i = 0; i < size; ++i){
				newBoard[0][i].setCellValue(Integer.parseInt(row[i]));
			}
			for(int j = 1; j < size; ++j){
				title = in.readLine();
				row = title.trim().split("\\s+");
				for(int k = 0; k < size; ++k){
					newBoard[j][k].setCellValue(Integer.parseInt(row[k]));
				}
			}
			title = in.readLine();
			if (!title.equals(""))
				throw new InvalidFormatException();
			boardSize = size;
			TheBoard = newBoard;
		}
		catch (InvalidFormatException | NumberFormatException | IOException e)
		{
			throw new InvalidFormatException();
		}
	}
	
	/**
	 * Almacena el board en el archivo de texto 
	 * @param out
	 * @throws IOException
	 */
	public void store(BufferedWriter out) throws IOException{
		for(int i = 0; i < boardSize; ++i){
			for(int j = 0; j < boardSize; ++j){
				out.write(TheBoard[i][j].toString());
				out.write(" ");
			}
			out.newLine();
		}
	}

	/**
	 * Este método modifica el valor de una celda en una posición de terminada.
	 * 
	 * @param pos
	 * @param value
	 */
	public void setCellValue(Position pos, int value) {
		TheBoard[pos.getX()][pos.getY()].setCellValue(value);
	}

	/**
	 * Este método ejecuta un movimiento en una dirección determinado siguiendo unas
	 * reglas de juego determinadas. En este caso, para ahorrar cçodigo, hemos
	 * decidido reflejar y transponer la matriz de celdas de forma que solo tengamos
	 * que ejecutar el movimiento hacia la derecha.
	 * 
	 * @param dir
	 * @param rules
	 * @return
	 */
	public MoveResults executeMove(Direction dir, GameRules rules) throws ArithmeticException {
		/*
		 * Instead of developing one movement for each situation we decided to create
		 * methods reflect() and transpose() to make things easier
		 */
		MoveResults data = new MoveResults();
		boolean moved = false;
		int puntos = 0;
		data.setMoved(false);
			if (dir.equals(Direction.LEFT)) {
				TheBoard = reflect(TheBoard);
				moved = movement();
				puntos = merge(rules);
				TheBoard = reflect(TheBoard);
			} else if (dir.equals(Direction.UP)) {
				TheBoard = transpose(TheBoard);
				TheBoard = reflect(TheBoard);
				moved = movement();
				puntos = merge(rules);
				TheBoard = reflect(TheBoard);
				TheBoard = transpose(TheBoard);
			} else if (dir.equals(Direction.DOWN)) {
				TheBoard = transpose(TheBoard);
				moved = movement();
				puntos = merge(rules);
				TheBoard = transpose(TheBoard);
			} else {
				moved = movement();
				puntos = merge(rules);
			}
			
			if ((puntos > 0) || moved) 
				data.setMoved(true);
			else
				System.err.println("Not an actual move! Try again");
		data.setScored(puntos);
		return data;
	}
/**
 *  Utilizado para optimizar el proceso del movimiento
 *  transpone una matriz
 * @param board
 * @return
 */
	private Cell[][] transpose(Cell[][] board) {
		Cell[][] AuxBoard = new Cell[boardSize][boardSize]; // Auxiliar matrix in order to make the move
		initializeBoard(AuxBoard, boardSize);
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {

				AuxBoard[i][j].setCellValue(board[j][i].getCellValue());
			}
		}
		return AuxBoard;
	}

	/**
	 * Utilizado para optimizar el proceso del movimiento
	 * @param board
	 * @return
	 */
	private Cell[][] reflect(Cell[][] board) {
		Cell[][] AuxBoard = new Cell[boardSize][boardSize]; // Auxiliary matrix in order to make the move
		initializeBoard(AuxBoard, boardSize);
		for (int i = 0; i < boardSize; i++) {
			int k = boardSize - 1;
			for (int j = 0; j < boardSize; j++) {
				AuxBoard[i][j].setCellValue(board[i][k].getCellValue());
				k--;
			}
		}
		return AuxBoard;
	}

	/**
	 * Comprueba si se produce un movimiento
	 * @return
	 */
	private boolean movement() {
		int counter;
		boolean movement = false;
		int[] moved = new int[boardSize];
		for (int i = 0; i < boardSize; i++) {
			counter = boardSize - 1;

			for (int s = 0; s < boardSize; s++)
				moved[s] = 0;

			for (int m = boardSize - 1; m >= 0; m--)
				if (!TheBoard[i][m].isEmpty()) {
					moved[counter] = TheBoard[i][m].getCellValue();
					counter--;
				}

			for (int x = boardSize - 1; x >= 0; x--) {
				if (TheBoard[i][x].getCellValue() != moved[x])
					movement = true;
				TheBoard[i][x].setCellValue(moved[x]);
			}
		}
		return movement;
	}

	/**
	 * calcula los puntos finales del merge
	 * @param rules
	 * @return
	 * @throws ArithmeticException
	 */
	private int merge(GameRules rules) throws ArithmeticException{
		int points = 0, finalPoints = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = boardSize - 2; (j >= 0) && (!TheBoard[i][j].isEmpty()); j--) {
				Position pos = new Position(i, j);
				points = TheBoard[i][j].doMerge(getCell(pos.neighbor()), rules);
				if (points > 0) {
					finalPoints += points;
					int k = j;
					boolean out = false;
					while ((k > 0) && (!out)) {
						if (!TheBoard[i][k - 1].isEmpty()) {
							TheBoard[i][k].setCellValue(TheBoard[i][k - 1].getCellValue());
							TheBoard[i][k - 1].setCellValue(0);
						} else {
							out = true;
						}
						k--;
					}
				}
			}
		}
		return finalPoints;
	}

	/**
	 * Este método servirá para imprimir el tablero.
	 */
	public String toString() {
		String boards = "";
		String line = MyStringUtils.repeat("-", 7 * boardSize + (boardSize + 1));
		boards += line;
		for (int i = 0; i < boardSize; i++) {
			boards += "\n";
			boards += "|";
			for (int m = 0; m < boardSize; ++m) {
				if (TheBoard[i][m].isEmpty()) {
					boards += MyStringUtils.centre(" " + "", 7);
				} else {
					boards += MyStringUtils.centre(TheBoard[i][m] + "", 7);
				}
				boards += "|";
			}
			boards += "\n";
			boards += line;
		}
		return boards;
	}

	/**
	 * Este método devolverá el valor máximo del tablero.
	 * 
	 * @return
	 */
	public int getMaxValue() throws ArrayIndexOutOfBoundsException {
		int mayor = TheBoard[0][0].getCellValue();
		for (int i = 0; i < getSize(); i++)
			for (int j = 0; j < getSize(); j++)
				if (TheBoard[i][j].getCellValue() > mayor)
					mayor = TheBoard[i][j].getCellValue();
		return mayor;
	}

	/**
	 * Este método devolverá el valor mínimo del tablero no nulo.
	 * 
	 * @return
	 */
	public int getMinValue() {
		int menor = 0;
		boolean primeroEncontrado = false;
		for (int i = 0; i < getSize(); i++)
			for (int j = 0; j < getSize(); j++)
				if (TheBoard[i][j].getCellValue() != 0 && !primeroEncontrado) {
					menor = TheBoard[i][j].getCellValue();
					primeroEncontrado = true;
				} else if (TheBoard[i][j].getCellValue() < menor && TheBoard[i][j].getCellValue() != 0)
					menor = TheBoard[i][j].getCellValue();
		return menor;
	}

	/**
	 * Este método comprobará si es posible realizar algún movimiento en el tablero
	 * sesún unas reglas de juego determinadas. De esta forma podremos saber si el
	 * usuario ha perdido o no.
	 * 
	 * @param rules
	 * @return
	 */
	public boolean possibleMove(GameRules rules) {
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize - 1; j++)
				if (rules.moveCondition(TheBoard[i][j].getCellValue(), TheBoard[i][j + 1].getCellValue()))
					return true;

		for (int i = 0; i < boardSize - 1; i++)
			for (int j = 0; j < boardSize; j++)
				if (rules.moveCondition(TheBoard[i][j].getCellValue(), TheBoard[i + 1][j].getCellValue()))
					return true;

		return false;
	}
	
	/**
	 * devuelve una cell teniendo una posición
	 * @param pos
	 * @return
	 */
	private Cell getCell(Position pos) {
		return TheBoard[pos.getX()][pos.getY()];
	}
}