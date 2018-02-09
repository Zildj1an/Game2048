package tp.pr2.util;

import tp.pr3.exceptions.controllerExceptions.EmptyStackException;

/**
 * Este clase servirá como pila para almacenar cualquier tipo de objetos. En
 * nuestro caso la utilizaremos para almacenar estados del tablero.
 * 
 

 */
public class Stack {
	private static final int CAPACITY = 20;
	private int elements;
	private Object[] stack;

	/**
	 * Aquí crearemos una nueva pila de capacidad máxima.
	 */
	public Stack() {
		stack = new Object[CAPACITY];
		elements = 0;
	}

	/**
	 * Este método compruba si la pila está vacía.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return elements == 0;
	}

	/**
	 * Este método servirña para comprobar si la pila está llena.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return elements == CAPACITY;
	}

	/**
	 * Este método borrará todos los objetos almacenados en la pila.
	 */
	public void erase() {
		if (elements < 0 || elements >= CAPACITY)
			throw new IndexOutOfBoundsException();
		for (int i = elements - 1; i >= 0; i--)
			stack[i] = null;
		elements = 0;
	}

	/**
	 * Este método devuelve el último objeto almacenado en la pila.
	 * 
	 * @return
	 */
	public Object pop() throws EmptyStackException{
		if (elements < 0 || elements >= CAPACITY)
			throw new IndexOutOfBoundsException();
		if (isEmpty())
			throw new EmptyStackException();
		--elements;
		Object a = stack[elements];
		stack[elements] = null;
		return a;	
	}

	/**
	 * Este método añadirá un objeto a la pila. Si está llena desplazará todos los
	 * objetos a la izquierda eliminando así el primero.
	 * 
	 * @param state
	 */
	public void push(Object state) {
		if (elements == CAPACITY) {
			for (int i = 0; i < elements - 1; i++) {
				stack[i] = stack[i + 1];
			}
			stack[elements - 1] = state;
		} else {
			stack[elements] = state;
			++elements;
		}
	}
}
