package tp.pr2.util;

import java.util.Random;

/**
 * Esta clase servirá para crear listas de cualquier tipo de objetos. En nuestro
 * caso la utilizaremos para almacenar posiciones vacías del tablero.
 * 
 

 */
public class ArrayAsList {
	private static final int CAPACITY = 100;
	private int capacity;
	private Object[] list;
	private int counter;

	/**
	 * Esta constructora creará una lista del tamaño que nos pasen por parámetro.
	 * 
	 * @param size
	 */
	public ArrayAsList(int size) {
		if (size < 0)
			throw new IllegalArgumentException();
		capacity = size * size;
		create();
	}

	/**
	 * Con esta constructora crearemos una lista de tamaño máximo.
	 */
	public ArrayAsList() {
		capacity = CAPACITY;
		create();
	}

	/**
	 * Con este método inicializaremos la lista con un número de objetos igual al
	 * parámetro 'capacity'.
	 */
	private final void create() {
		list = new Object[capacity];
		counter = 0;
		for (int i = 0; i < capacity; i++) {
			list[i] = new Object();
		}
	}

	/**
	 * Con este método borraremos toda la lista.
	 */
	public void erase() {
		if (counter < 0 || counter >= capacity)
			throw new IndexOutOfBoundsException();
		for (int i = counter; i > 0; i--)
			list[counter - 1] = null;
		counter = 0;
	}

	/**
	 * Este método comprobará si la lista de elementos está llena.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return counter == capacity;
	}

	/**
	 * Este método comprobará si la lista de elementos está vacía.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return counter == 0;
	}

	/**
	 * Este método nos devolverá el número de elementos que hay en la lista.
	 * 
	 * @return
	 */
	public int size() {
		return counter;
	}

	/**
	 * Con este método podremos añadir un elemento a la lista.
	 * 
	 * @param o
	 */
	public void add(Object o) {
		if (counter < 0 || counter >= capacity)
			throw new IndexOutOfBoundsException();
		list[counter] = o;
		counter++;	
	}

	/**
	 * Con este método podremos eliminar el elemento de la lista en la posición pos,
	 * si es que existe.
	 * 
	 * @param pos
	 * @return
	 */
	public boolean remove(int pos) {
		if ((pos < 0) || (pos > counter - 1))
			throw new IndexOutOfBoundsException();
		for (int i = pos; i < counter - 1; i++) {
			list[i] = list[i + 1];
		}
		counter--;
		list[counter] = null;
		return true;
	}

	/**
	 * Este método nos devolverá el elemento de la lista en la posición 'pos'.
	 * 
	 * @param pos
	 * @return
	 */
	public Object get(int pos) {
		if ((pos < 0) || (pos > capacity - 1))
			throw new IndexOutOfBoundsException();
		return list[pos];
	}

	/**
	 * Con este método podremos imprimir los elementos de la lista.
	 */
	public String toString() {
		String stringOut = "Elements of the list: \n\n";
		for (int i = 0; i < counter; i++) {
			stringOut += list[i] + "\n";
		}
		return stringOut;
	}

	/**
	 * Con este método mezclaremos toda la lista para descolocar sus posiciones.
	 * 
	 * @param listObj
	 * @param random
	 */
	public static void shuffle(ArrayAsList listObj, Random random) {
		for (int i = listObj.size(); i > 1; i--) {
			swap(listObj.list, i - 1, random.nextInt(i));
		}
	}

	/**
	 * Este método será utilizado para elegir un elemento aleatorio de la lista.
	 * 
	 * @param list
	 * @param random
	 * @return
	 */
	public static Object choice(ArrayAsList list, Random random) {
		return list.get(random.nextInt(list.size()));
	}
	
	/**
	 * Intercambio de dos elementos del array Object
	 * @param anArray
	 * @param i
	 * @param j
	 */
	private static void swap(Object[] anArray, int i, int j) {
		Object temp = anArray[i];
		anArray[i] = anArray[j];
		anArray[j] = temp;
	}
}