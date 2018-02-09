package tp.pr2.util;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * Esta clase ayudará al funcionamiento del juego con distintas utilidades
 * relacionadas con las Strings.
 * 
 

 */
public class MyStringUtils {
	/**
	 * Este método sirve para repetir una String tantas veces como nos pasen por
	 * parámetro.
	 * 
	 * @param elmnt
	 * @param length
	 * @return
	 */
	public static String repeat(String elmnt, int length) // Outputs -----
	{
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	/**
	 * Este método servirá para centrar una String dentro de un tamaño de
	 * caracteres.
	 * 
	 * @param text
	 * @param len
	 * @return
	 */
	public static String centre(String text, int len) // To center |__|
	{
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;
		return out.substring((int) start, (int) end);
	}
	
	/**
	 * This method is not completely reliable  since exception could also be thrown due to:
	 * incorrect  permissions , no space on disk , problem accessing the device ,...
	 * @param filename
	 * @return
	 */
	// Used to exist  method: org.eclipse . core. internal . resources . OS.isNameValid(filename).
	
	public static boolean validFileName(String filename) {
		File file = new	File(filename);
		if (file.exists()) {
			return canWriteLocal(file);
		}
		else {
			try	{
				file.createNewFile();
				file.delete();
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
	}
	
	/**
	 * Comrpueba para Windows/Linux si se puede scribir
	 * @param file
	 * @return
	 */
	public static boolean canWriteLocal(File file) {
		// works OK on Linux but not on Windows (apparently!)
		if (!file.canWrite()) {
			return false;
		}
		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
}

