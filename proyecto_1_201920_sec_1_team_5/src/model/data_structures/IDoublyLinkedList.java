package model.data_structures;

public interface IDoublyLinkedList <T> extends Iterable<T> {

	//-------------------------------------------------
	// Metedos
	//-------------------------------------------------

	/**
	 * Aniade un elemento a la lista
	 * @param elemento
	 */
	void aniadirElemento (T elemento);

	/**
	 * Dada una posicion aniade el elemento a la lista 
	 * @param elemento a aniadir 
	 * @param pos en la que se va a insertar el elemento en la lista
	 */
	void aniadirElementoPos (T elem, int pos);


	/**
	 * Aniade el elemento al final de la lista
	 * @param elemento a aniadir
	 */
	void aniadirElementoAlFinal (T elemento);


	/**
	 * Revisa si el elemento se encuentra en la lista  
	 * @param elemento que se va a verificar en la lista
	 * @return True si el elemento esta en la lsita
	 */
	boolean contiene (T elemento);

	/**
	 * Permite eliminar un elemento en la lista
	 * @param elemento a eliminar de la lista
	 * @return el elemento eliminado
	 */
	T eliminarElemento (T elemento);

	/**
	 * Elimina un elemento en cierta posicion
	 * @param posicion 
	 * @return True si se pudo eliminar el elemento
	 */
	boolean eliminarElementoPos (int posicion);

	/**
	 * Da el tamaño de la lista 
	 * @return entero con el tamaño
	 */
	Integer darTamanio ();

	/**
	 * Tamaño en cierta posicion
	 * @param pos donde se quiere verificar el elemento
	 * @return elemento en la posicion dada
	 */
	T darElemento (int pos);

	T darElementoActual ();

	/**
	 * Siguiente nodo al actual
	 * @return nodo siguiente
	 */
	Nodo <T> darSiguiente();

	/**
	 * Nodo anterior del actual
	 * @return nodo anterior
	 */
	Nodo <T> darAnterior ();

	/**
	 * Busca un nodo en cierta posicion
	 * @param posicion
	 * @return Nodo buscado
	 */
	Nodo <T> darNodo (int posicion);


}
