package model.data_structures;

import java.util.Iterator;

//import com.sun.org.omg.CORBA.ExceptionDescription;

public class Stack <T> implements IStack<T> {

	//------------------------------------
	//Atributos Stack
	//------------------------------------
	
	/**
	 * Primer nodo de la lista 
	 */
	Nodo <T> primero;
	
	/**
	 * Tama�o de la pila/Stack
	 */
	private int tamanio;
	
	
	
	//------------------------------------
	// Constructor
	//------------------------------------
	public Stack (){
		primero = null;
		tamanio = 0;
	}
	
	@Override
	public void push(T elemento) {
		
		if (primero == null) {
			primero = new Nodo<T>(elemento);
		}else {
			Nodo<T> pushedElement = new Nodo<T>(elemento);
			pushedElement.cambiarSiguiente(primero);
			primero = pushedElement;
			tamanio ++;
		}
	}

	@Override
	public T pop() {
		T elementoR = null;
		Nodo<T> popElement = primero;
		
		if (tamanio == 1) {
			primero = null;
			elementoR = (T) primero;
			throw new NullPointerException("la estructura de datos fue eliminada");
		}else {
			primero = primero.darSiguiente();
			tamanio --;
			elementoR = (T) popElement;
		}
		return popElement.darElemento();
	}

	@Override
	public int darTamanoStack() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		boolean empty = false;
		Nodo <T> temp = primero;
		if (temp == null){
			empty = true;
		}empty = false;
		
		return empty;
	}

	@Override
	public T darElementoTope() {
		return primero.darElemento();
	}

}
