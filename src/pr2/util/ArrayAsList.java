package tp.pr2.util;

import java.util.Random;

/**
 * Esta clase servir� para crear listas de cualquier tipo de objetos. En nuestro
 * caso la utilizaremos para almacenar posiciones vac�as del tablero.
 * 
 

 */
public class ArrayAsList {
	private static final int CAPACITY = 100;
	private int capacity;
	private Object[] list;
	private int counter;

	/**
	 * Esta constructora crear� una lista del tama�o que nos pasen por par�metro.
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
	 * Con esta constructora crearemos una lista de tama�o m�ximo.
	 */
	public ArrayAsList() {
		capacity = CAPACITY;
		create();
	}

	/**
	 * Con este m�todo inicializaremos la lista con un n�mero de objetos igual al
	 * par�metro 'capacity'.
	 */
	private final void create() {
		list = new Object[capacity];
		counter = 0;
		for (int i = 0; i < capacity; i++) {
			list[i] = new Object();
		}
	}

	/**
	 * Con este m�todo borraremos toda la lista.
	 */
	public void erase() {
		if (counter < 0 || counter >= capacity)
			throw new IndexOutOfBoundsException();
		for (int i = counter; i > 0; i--)
			list[counter - 1] = null;
		counter = 0;
	}

	/**
	 * Este m�todo comprobar� si la lista de elementos est� llena.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return counter == capacity;
	}

	/**
	 * Este m�todo comprobar� si la lista de elementos est� vac�a.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return counter == 0;
	}

	/**
	 * Este m�todo nos devolver� el n�mero de elementos que hay en la lista.
	 * 
	 * @return
	 */
	public int size() {
		return counter;
	}

	/**
	 * Con este m�todo podremos a�adir un elemento a la lista.
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
	 * Con este m�todo podremos eliminar el elemento de la lista en la posici�n pos,
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
	 * Este m�todo nos devolver� el elemento de la lista en la posici�n 'pos'.
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
	 * Con este m�todo podremos imprimir los elementos de la lista.
	 */
	public String toString() {
		String stringOut = "Elements of the list: \n\n";
		for (int i = 0; i < counter; i++) {
			stringOut += list[i] + "\n";
		}
		return stringOut;
	}

	/**
	 * Con este m�todo mezclaremos toda la lista para descolocar sus posiciones.
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
	 * Este m�todo ser� utilizado para elegir un elemento aleatorio de la lista.
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