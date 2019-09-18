package model.data_structures;

public interface IQueue<T>
{
	/**
	 * Inserta un nuevo elemento al "final" de la cola
	 * @param El elemento que se va a agregar
	 */
	void enqueue(T elem);
	
	/**
	 * Elimina el elemento del "principio" de la cola 
	 * y lo retorna
	 * @return El elemento eliminado
	 */
	T dequeue();
	
	/**
	 * El tamaño (número de elementos) de la cola
	 * @return El tamaño de la cola
	 */
	int tamano();
	
	/**
	 * Consulta si la cola está vacía
	 * Si la cola está vacía devuelve true
	 * @return True si la cola está vacía. False si la cola tiene al menos
	 * un elemento
	 */
	boolean isEmpty();
	
	/**
	 * Consulta el elemento del "principio" de la cola sin eliminarlo
	 * @return El elemento del principio de la cola
	 */
	T lastElement();
}
