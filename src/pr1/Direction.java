package tp.pr1;

import tp.pr3.exceptions.controllerExceptions.ParseException;

/**
 * Esta clase nos servirá para establecer la dirección en la que ese ejecutará
 * el movimiento en el juego.
 * 
 

 */
public enum Direction {
	UP("up"), DOWN("down"), RIGHT("right"), LEFT("left");

	private static Direction[] directions = Direction.values();
	private String directionString;

	Direction(String directionString) {
		this.directionString = directionString;
	}

	private String getString() {
		return directionString;
	}

	/**
	 * Con este método obtendremos la dirección que conicida con la String que se
	 * pasa por parámetro.
	 * 
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static Direction parse(String value) throws ParseException {
		for (Direction d : directions) {
			if (value.equalsIgnoreCase(d.getString()))
				return d;
		}
		throw new ParseException("Unknown direction for move command");
	}
	
	/**
	 * 
	 * @return
	 */
	public static String externaliseAll() {
		String s = "";
		for (Direction dir : Direction.values())
			s = s + " "+ dir.directionString + ",";
		return s.substring(1, s.length() - 1);
	}
}