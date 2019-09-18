package model.data_structures;

import java.util.Iterator;

public class IteradorLista<K> implements Iterator<K> {
	
	private Nodo<K> siguiente;

	public IteradorLista (Nodo<K> primero) {
		siguiente = primero;
	}

	public boolean hasNext() {
		return siguiente != null;
	}

	public K next () {

		if (siguiente == null) {
			throw new NullPointerException("No existe el siguiente elemento");
		}

		K elementoAct = siguiente.darElemento();
		siguiente = siguiente.darSiguiente();
		return  elementoAct;
	}
}
