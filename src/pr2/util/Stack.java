package tp.pr2.util;

import tp.pr3.exceptions.controllerExceptions.EmptyStackException;

/**
 * Este clase servir� como pila para almacenar cualquier tipo de objetos. En
 * nuestro caso la utilizaremos para almacenar estados del tablero.
 * 
 

 */
public class Stack {
	private static final int CAPACITY = 20;
	private int elements;
	private Object[] stack;

	/**
	 * Aqu� crearemos una nueva pila de capacidad m�xima.
	 */
	public Stack() {
		stack = new Object[CAPACITY];
		elements = 0;
	}

	/**
	 * Este m�todo compruba si la pila est� vac�a.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return elements == 0;
	}

	/**
	 * Este m�todo servir�a para comprobar si la pila est� llena.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return elements == CAPACITY;
	}

	/**
	 * Este m�todo borrar� todos los objetos almacenados en la pila.
	 */
	public void erase() {
		if (elements < 0 || elements >= CAPACITY)
			throw new IndexOutOfBoundsException();
		for (int i = elements - 1; i >= 0; i--)
			stack[i] = null;
		elements = 0;
	}

	/**
	 * Este m�todo devuelve el �ltimo objeto almacenado en la pila.
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
	 * Este m�todo a�adir� un objeto a la pila. Si est� llena desplazar� todos los
	 * objetos a la izquierda eliminando as� el primero.
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
