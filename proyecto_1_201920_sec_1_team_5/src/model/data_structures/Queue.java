package model.data_structures;

/**
 * Clase genérica Queue
 * La información de está clase fue tomada de la página:
 * https://algs4.cs.princeton.edu/43mst/Queue.java.html
 * Los autores de esa clase son:
 * Robert Sedgewick
 * Kevin Wayne
 * @author Daniel Rincón & Nikolas Castellanos
 *
 * @param <T> Tipo genérico de la clase
 */
public class Queue<T> implements IQueue<T>
{

	Nodo<T> first, last;
	int tamano;


	public Queue()
	{
		tamano = 0;

	}
	@Override
	public void enqueue(T elem) 
	{
		Nodo<T> oldlast = last;
        last = new Nodo<T>();
        last.cambiarElemento(elem);;
        last.cambiarSiguiente(null); 
        if(isEmpty())
        	first = last;
        else           oldlast.cambiarSiguiente(last);
        tamano++;


	}

	@Override
	public T dequeue() 
	{
		T elem = first.darElemento();
		first = first.darSiguiente();
		tamano--;
		return elem;
	}

	@Override
	public int tamano() {
	
		return tamano;
	}

	@Override
	public boolean isEmpty() 
	{
		return first == null;
	}

	@Override
	public T lastElement() 
	{
		return last.darElemento();
	}
	
	public T firstElement()
	{
		return first.darElemento();
	}
	public Nodo<T> firstNodo()
	{
		return first;
	}
	public Nodo<T> lastNodo()
	{
		return last;
	}

}
