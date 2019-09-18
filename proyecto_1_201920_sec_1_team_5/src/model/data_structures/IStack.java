package model.data_structures;

import java.util.Iterator;

public interface IStack <T> {
	
	//------------------------------------
	// Stack interface functions
	//------------------------------------
	
	/**
	 * Permite a�adir elementos a la estructura de datos Stack
	 */
	void push (T elemento);
	
	/**
	 * Permite eliminar un elemento de la estructura pile / Stack
	 */
	T pop ();
	
	/**
	 * Consulta el tama�o de la estructura
	 */
	int darTamanoStack ();
	
	/**
	 * Consulta si la pila esta vacia
	 */
	boolean isEmpty ();
	
	/**
	 * Consulta el elemento tope de la pila
	 */
	T darElementoTope ();
}
