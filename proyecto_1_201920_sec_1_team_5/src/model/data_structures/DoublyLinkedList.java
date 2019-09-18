package model.data_structures;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DoublyLinkedList<T> implements IDoublyLinkedList<T> {

private static final Long serialVersionUID = 1L;
	
	private Nodo <T> primero;
	
	private int cantidadElementos;
	
	private Nodo <T> siguiente;
	
	private Nodo <T> anterior;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public DoublyLinkedList () {
		primero = null;
		cantidadElementos = 0;
	}
	
	/**
	 * Se guarda al primer elemento que viene por parametro en el primer Nodo
	 * @param elemento que se va a agregar
	 * @throws Exception si el elemento que se va a agregar es nulo
	 */
	
	public DoublyLinkedList (T pElemento) {
		if (pElemento == null) {
			throw new NullPointerException ("El elemento que se va a agregar es nulo, no existe");
		}else {
			primero = new Nodo<T>(pElemento);
			cantidadElementos ++;
		}
	}

	public Iterator<T> iterator() {
		return new IteradorLista<>(primero);
	}

	public void aniadirElemento(T elemento) {
		
		Nodo <T> actual = primero;
		
		if (elemento == null) {
			throw new NullPointerException("El elemento que se quiere a�adir es null");
		} else {
			if (primero == null) {
				primero = new Nodo<T>(elemento);
				cantidadElementos++;
			}
			if (!contiene(elemento)) {
				Nodo<T> nuevo = new Nodo<T> (elemento);
				actual.cambiarAnterior(nuevo);
				nuevo.cambiarSiguiente(actual);
				cantidadElementos++;
			}
		}
	}

	public T eliminarElemento(T elemento) {
		Nodo<T> actual = primero;
		T elem = null;
		
		if (elemento == null || actual == null) {
			throw new NullPointerException("el elemento a eliminar o la lista son null");
		}else {
			
			if (actual != null && contiene(elemento)) {
				
				if (actual.darElemento().equals(elemento)) {
					primero = primero.darSiguiente();
					primero.cambiarAnterior(null);
					cantidadElementos --;
					elem = elemento;
				}else if (darElemento(cantidadElementos).equals(elemento)) {
					Nodo<T> ant = actual.darAnterior();
					ant.cambiarSiguiente(null);
					actual.cambiarAnterior(null);
					elemento = darElemento(cantidadElementos);
				}else if (darElemento(cantidadElementos - 1).equals(elemento)) {
					Nodo<T> temp = actual.darAnterior();
					Nodo<T> temp2 = actual.darSiguiente();
					actual.cambiarSiguiente(null);
					temp.cambiarSiguiente(temp2);
					actual.cambiarAnterior(null);
					temp2.cambiarAnterior(temp);
					elem = darElemento(cantidadElementos - 1);
				}
			}
		}
		return elem;
	}

	@Override
	public boolean eliminarElementoPos(int posicion) {
		boolean x = false;
		Nodo<T> actual = primero;
		
		if (posicion < 0 || posicion > cantidadElementos) {
			throw new IndexOutOfBoundsException("la posicion ingresada es: " + posicion);
		} else {
			while (actual != null && !x) {
				T elem = actual.darElemento();
				if (elem.equals(darElemento(posicion)) && posicion == 0) {
					primero = actual.darSiguiente();
					actual.darSiguiente().cambiarAnterior(null);
					x = true;
				}else if (elem.equals(darElemento(posicion)) && posicion == cantidadElementos) {
					Nodo<T> temp = actual.darAnterior();
					temp.cambiarSiguiente(null);
					actual.cambiarAnterior(null);
					x = true;
				}else {
					Nodo<T> alpha = actual.darSiguiente();
					Nodo <T> phi = actual.darAnterior();
					alpha.cambiarSiguiente(phi);
					phi.cambiarAnterior(alpha);
					actual.cambiarSiguiente(null);
					actual.cambiarAnterior(null);
					x = true;
					
				}
				actual = actual.darSiguiente();
			}
		}
		
		return x;
		
	}

	public Integer darTamanio() {
		int omega = 0;
		Nodo<T> actual = primero;
		
		while (actual != null) {
			actual = actual.darSiguiente();
			omega++;
		}
		return omega;
	}

	public T darElemento(int pos) {
		
		Nodo<T> actual = primero;
		int iter = 0;
		if (pos < 0 || pos > cantidadElementos) {
			throw new IndexOutOfBoundsException("se esta pidiendo el elemento en la posicion: " + pos);
		}else {
			while (actual != null && iter < cantidadElementos) {
				actual.darSiguiente();
				iter++;
			}
		}return (T) actual;
	}

	@Override
	public T darElementoActual() {
		Nodo<T> act = primero;
		T elem = null;
		if (act == null) {
			throw new NullPointerException("La lista esta vacia");
		}
		while (act != null) {
			elem = act.darElemento();
		}
		return elem;
	}

	@Override
	public Nodo<T> darSiguiente() {
		return siguiente;
	}

	@Override
	public Nodo<T> darAnterior() {
		return anterior;
	}

	public void aniadirElementoPos(T elem, int posicion) {
		
		if(posicion < 0 || posicion > darTamanio())
			throw new IndexOutOfBoundsException("El elemento esta fuera de la lista");
		else if(elem == null)
			throw new NullPointerException("El elemento a agregar no existe");
		else if(primero == null)
		{
			primero = new Nodo<T>(elem);
			cantidadElementos++;
		}
		else
		{
			Nodo<T> nuevo = new Nodo<T>(elem);
			Nodo<T> anterior = (Nodo<T>) primero;
			if(posicion == 0)
			{
				anterior.cambiarAnterior(anterior);
				nuevo.cambiarSiguiente(anterior);
				primero = nuevo;
				cantidadElementos ++;
			}
			else if(posicion == cantidadElementos)
			{
				aniadirElemento(elem);
			}
			else
			{
				Nodo<T> siguiente = (Nodo<T>)darNodo(posicion);
				nuevo = new Nodo<T>(elem);

				for(int i = 0; i<cantidadElementos-1; i++)
				{
					anterior = (Nodo<T>)anterior.darSiguiente();
				}
				nuevo.cambiarSiguiente(siguiente);
				nuevo.cambiarAnterior(anterior);
				anterior.cambiarSiguiente(nuevo);
				siguiente.cambiarAnterior(nuevo);
				cantidadElementos ++;
			}

		}
	}

	public void aniadirElementoAlFinal(T elemento) {
		
		if(elemento == null)
		{
			throw new NullPointerException("El elemento para agregar no existe");
		}

		else
		{
			if(primero == null)
			{
				primero =  new Nodo<T>(elemento);

				cantidadElementos++;
			}
			if(!contiene(elemento))
			{
				Nodo<T> ultimo = (Nodo<T>) darNodo(cantidadElementos-1);
				Nodo<T> nuevo = new Nodo<T>(elemento);
				ultimo.cambiarSiguiente(nuevo);
				nuevo.cambiarAnterior(ultimo);

				cantidadElementos++;
			}

		}
		
	}

	@Override
	public boolean contiene(Object o) {
		boolean x = false;
		Nodo <T> actual = primero;
		for (int i = 0; i < cantidadElementos && !x; i++) {
			if (actual == null) {
				throw new NullPointerException("El primer elemento de la lista es null");
			}else {
				if (actual.darElemento().equals(o))
					x = true;
				else if (actual.equals(o))
					x = true;
				else
					x = false;
			}
			actual = actual.darSiguiente();
		}
		return x;
	}

	public Nodo<T> darNodo( int pos) {
		Nodo<T> actual = primero;

		if(pos < 0 || pos > cantidadElementos)
		{
			throw new IndexOutOfBoundsException("Se est� pidiendo el indice: " + pos + " y el tama�o de la lista es de " + cantidadElementos);
		}

		else
		{
			int posActual = 0;

			while(actual != null && posActual < pos)
			{
				actual = actual.darSiguiente();
				posActual ++;
			}
		}
		return actual;
	}

}
