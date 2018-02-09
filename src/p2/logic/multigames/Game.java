package tp.p2.logic.multigames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import tp.p2.rules.GameRules;
import tp.pr1.Board;
import tp.pr1.Direction;
import tp.pr1.MoveResults;
import tp.pr2.util.Stack;
import tp.pr3.exceptions.controllerExceptions.EmptyStackException;
import tp.pr3.exceptions.controllerExceptions.InvalidFormatException;
import tp.pr3.exceptions.controllerExceptions.ParseException;
import tp.pr3.exceptions.gameOverExceptions.LoseException;
import tp.pr3.exceptions.gameOverExceptions.WinException;

/**
 * En esta clase crearemos el juego cada vez que haya uno nuevo, ejecutaremos
 * los distintos comandos disponibles y comprobaremos si el jugador ha ganado o
 * perdido.

 */
public class Game {
	private Board board;
	private int size;
	private int initCells;
	private Random myRandom;
	private int score;
	private Stack undoStack;
	private Stack redoStack;
	private boolean exit;
	private GameRules currentRules;
	private GameType currentType;
	
	/**
	 * Con este método imprimiremos un ASCII Art del juego 2048
	 */
	public static void ASCII_Art() {
		System.out.println("ad888888b,    ,a8888a,            ,d888       ad88888     ");
		System.out.println("d8'     '88  ,8P''  `'Y8,        ,d8 88      d8'   '8b    ");
		System.out.println("a8P    ,8P   Y8,      ,d8'      88   Y8      ba    Pb     ");
		System.out.println("       88    88        ,d8     88    'Y       8aaa8P'     ");
		System.out.println(",d8P'abc8    88        ,d8     88    'Y       8aaa8P'     ");
		System.out.println("a8P'         88        88   ,d8'     88     ,d8'''8b,     ");
		System.out.println("a8P'        `8b        d8' 8888888888888  d8'     '8b     ");
		System.out.println("d8'          `8ba,  ,ad8'           88    Y8a     a8P     ");
		System.out.println("88888888888    'Y8888P'             88     'Y88888P'     \n");
	}

	/**
	 * Construye un objeto de la clase Game instanciando todos sus atributos.
	 * 
	 * @param seed
	 * @param size
	 * @param initCells
	 * @param reglas
	 * @throws LoseException 
	 */
	public Game(long seed, int size, int initCells, GameType tipo) throws LoseException {
		currentRules = tipo.getTypeRules();
		currentType = tipo;
		myRandom = new Random(seed);
		this.size = size;
		this.initCells = initCells;
		exit = false;
		startGame(true);
	}
	
	/**
	 * Método utilizado por PlayCommand
	 * @param seed
	 * @param size
	 * @param initCells
	 * @param tipo
	 * @throws LoseException 
	 */
	public void setNewGame(long seed, int size, int initCells, GameType tipo) throws LoseException {
		currentRules = tipo.getTypeRules();
		currentType = tipo;
		myRandom = new Random(seed);
		this.size = size;
		this.initCells = initCells;
		exit = false;
		startGame(false);
	}
		
	/**
	 * Metodo utilizado por LoadCommand
	 * @param size
	 * @param initCells
	 * @param score
	 * @param tipo
	 */
	private void loadNewGame(int size, int initCells, int score, GameType tipo) {
		myRandom = new Random();
		this.size = size;
		this.initCells = initCells;
		this.score = score;
		currentType = tipo;
		currentRules = tipo.getTypeRules();
		undoStack.erase();
		redoStack.erase();
		exit = false;
	}
	
	/**
	 * Crea un nuevo board, score de cero y nuevo stack
	 * @param start
	 * @throws LoseException
	 */
	private void startGame(boolean start) throws LoseException // Reset functions
	{
		board = new Board(this.size);
		score = 0;
		currentRules.initBoard(board, initCells, myRandom);
		if(start) {
			undoStack = new Stack();
			redoStack = new Stack();
		}
		else
		{
			undoStack.erase();
			redoStack.erase();
		}
		if (gameOver()) {
			throw new LoseException();
		}
	}

	/**
	 * Con este método ejecutaremos un movimiento en una dirección que nos pasen por
	 * parámetro. Para ello llamaremos un método de Board. Si hemos ejecutado el
	 * movimiento deberemos comprobar si hemos ganado o perdido y tendremos que
	 * guardar el estado anterior a ejecutar ese movimiento en la pila de
	 * movimientos anteriores.
	 * 
	 * @param dir
	 * @return
	 * @throws WinException 
	 * @throws LoseException 
	 */
	public boolean move(Direction dir) throws WinException, LoseException {
		redoStack.erase();
		GameState auxState = initializeState();
		MoveResults auxData = new MoveResults();
		auxData = board.executeMove(dir, currentRules);
		score += auxData.getScored();
		if (auxData.getMoved()) {
			undoStack.push(auxState);
			currentRules.addNewCell(board, myRandom);
			if (victory())
				throw new WinException(); 
			else if (gameOver())
				throw new LoseException();
		}
		return auxData.getMoved();
	}

	/**
	 * Crea un nuevo GameState para inicializarlo
	 * @return state
	 */
	private GameState initializeState() {
		GameState state = new GameState();
		state.setScore(score);
		state.setBoardState(board.getState());
		return state;
	}

	/**
	 * En este método se imprimirán el tablero, la puntuación y el mejor valor según
	 * las reglas del juego.
	 */
	public String toString() {
		String s = "score: " + score + "    best value: " + currentRules.getWinValue(board) + "\n";
		return s + board;
	}

	/**
	 * En este método se ejecutará el comando reset, creando un nuevo juego con un
	 * nuevo tablero y vaciando las pilas.
	 * @throws LoseException 
	 */
	public void reset() throws LoseException {
		startGame(false);
	}

	/**
	 * Este método se utilizará al ejecutar el comando Exit o cuando el usuario haya
	 * ganado o perdido, modificando un atributo booleano que será comprobado en
	 * Controller para salir del juego.
	 */
	public void exit() {
		exit = true;
	}

	/**
	 * Este será el método que utilizará Controller para saber si se ha terminado el
	 * juego, ya sea porque el jugador ha ganado, porque ha perdido o porque ha
	 * introducido el comando Exit.
	 * 
	 * @return
	 */
	public boolean getExit() {
		return exit;
	}

	/**
	 * Con este método ejecutaremos el comando Undo. Así volveremos al estado
	 * anterior del juego a ejecutar el último movimieto. También se añadirá el
	 * movimiento actual a la pila de Redo.
	 * 
	 * @return
	 * @throws EmptyStackException 
	 */
	public void undo() throws EmptyStackException {
		try 
		{
			GameState gamestate = (GameState) undoStack.pop();
			System.out.println("Undoing one move...");
			redoStack.push(initializeState());
			this.setState(gamestate);
		}
		catch (EmptyStackException e)
		{
			throw new EmptyStackException("Undo is not available.");
		}
	}

	/**
	 * Con este método ejecutaremos el comando Redo. Así podremos volver a los
	 * estados siguientes en el caso de que justo antes hayamos ejecutado una o más
	 * veces seguidas el comando Undo.
	 * 
	 * @return
	 * @throws EmptyStackException 
	 */
	public void redo() throws EmptyStackException {
		try
		{
			GameState gamestate = (GameState) redoStack.pop();
			System.out.println("Redoing one move...");
			undoStack.push(initializeState());
			this.setState(gamestate);
		}
		catch (EmptyStackException e)
		{
			throw new EmptyStackException("Nothing to redo.");
		}
	}
	
	/**
	 * Para escribir en el archivo de texto el número de celdas iniciales, el score
	 * y el tipo de juego. Llamado por SaveCommand.
	 * @param out
	 * @throws IOException
	 */
	public void store(BufferedWriter out) throws IOException{ /** Called by load command */
		board.store(out);
		out.newLine();
		out.write("" + initCells);
		out.write(" ");
		out.write("" + score);
		out.write(" ");
		out.write(currentType.getString());
	}

	/**
	 * Asigna al board y al score los del GameState que se le pase
	 * @param aState
	 */
	
	private void setState(GameState aState) {
		score = aState.getScore();
		board.setState(aState.getBoardState());
	}

	/**
	 * Comprueba si el estado del board significa un win
	 * con currentRules
	 * @return
	 */
	
	private boolean victory() {
		if (currentRules.win(board)) {
			exit();
			System.out.println(this);
			return true;
		} else
			return false;
	}
	
	/**
	 * Este método lee el archivo de texto, llamando a board para que
	 * haga parte de ello. LLamado por load de LoadCommand
	 * @param in
	 * @return
	 * @throws InvalidFormatException
	 */
	
	public GameType load(BufferedReader in) throws InvalidFormatException{		
		try
		{
			Board newBoard = new Board(size);
			newBoard.load(in);
			String title = in.readLine();
			String[] row = title.trim().split("\\s+");
			int newInitCells = Integer.parseInt(row[0]);
			if (newInitCells > newBoard.getSize() * newBoard.getSize())
				throw new InvalidFormatException();
			int newScore = Integer.parseInt(row[1]);
			GameType tipo = GameType.parse(row[2]);
			loadNewGame(newBoard.getSize(), newInitCells, newScore, tipo);
			board = newBoard;
		}
		catch (InvalidFormatException | NumberFormatException | IOException | ParseException e)
		{
			throw new InvalidFormatException();
		}
		return currentType;
	}
	
	/**
	 * Comprueba con currentRules si el board actual
	 * significa una partida perdida
	 * @return
	 */
	private boolean gameOver() {
		if (currentRules.lose(board)) {
			exit();
			System.out.println(this);
			return true;
		} else
			return false;
	}
}