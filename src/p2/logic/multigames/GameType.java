package tp.p2.logic.multigames;

import tp.p2.rules.GameRules;
import tp.p2.rules.Rules2048;
import tp.p2.rules.RulesFib;
import tp.p2.rules.RulesInverse;
import tp.pr3.exceptions.controllerExceptions.ParseException;

/**
 * Este enumerado contiene los distintos tipos de juego a los que el usuario
 * podrá jugar. Cada tipo de juego tendrá asociado una string y unas reglas
 * concretas.
 * 
 

 */
public enum GameType {
	ORIGINAL("original", new Rules2048(), "2048, original version"), 
	FIB("fib", new RulesFib(), "2048, Fibonacci version"),
	INVERSE("inverse", new RulesInverse(), "2048, inverse version");

	private static GameType[] tipos = GameType.values();
	private String tipoString;
	private GameRules typeRules;
	private String userFriendlyName;

	private GameType(String tipoString, GameRules rules, String friendly) {
		this.tipoString = tipoString;
		this.typeRules = rules;
		this.userFriendlyName = friendly;
	}

	public String getString() {
		return tipoString;
	}

	/**
	 * Con esté método obtendremos las reglas asociadas a cada tipo de juego.
	 * 
	 * @return typeRules
	 */
	public GameRules getTypeRules() {
		return typeRules;
	}

	/**
	 * Este método recibirá una string y la comparará con la de cada tipo de juego
	 * con el fin de obtener ese tipo de juego.
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	
	public static GameType parse(String value) throws ParseException {
		for (GameType g : tipos) {
			if (g.getString().equalsIgnoreCase(value))
				return g;
		}
		throw new ParseException("Unknown game type for play command");
	}
	
	/**
	 * para tener los tipos de juego en string
	 * @return string
	 */
	
	public static String externaliseAll() {
		String s = "";
		for (GameType type : GameType.values())
			s = s + " "+ type.tipoString + ",";
		return s.substring(1, s.length() - 1);
	}
	
	/**
	 * Devuelve el mensaje como string
	 */
	
	public String toString() {
		return userFriendlyName;
	}
	
}