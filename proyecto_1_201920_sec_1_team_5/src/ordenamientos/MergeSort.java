package ordenamientos;

import model.data_structures.*;
import view.MVCView;

public class MergeSort<T extends Comparable<T>>
{

	//------------------------------
	// Atributos
	//------------------------------

	/**
	 * Referencia a view para mostrar los tiempos de ejecucion del algoritmo
	 */
	private MVCView view;

	//------------------------------
	// Metodo constructor 
	//------------------------------

	public MergeSort (T[] arreglo, T[] arregloAux) {
		view = new MVCView();
		long tiempoInicio = System.currentTimeMillis();

		merge(arreglo, arregloAux, 0, (arreglo.length - 1) / 2, arreglo.length - 1);

		long tiempoFinal = System.currentTimeMillis();
		long tiempoEjecucion = tiempoFinal - tiempoInicio;
		view.printMessage("El tiempo de ejecucion para MergeSort es de: " + tiempoEjecucion + " milisegundos");
	}

	public void merge (T[] arreglo, T [] arregloAux, int lo, int mid, int hi) {
		for (int i = lo; i <= hi; i++) {
			//Se realiza la copia a un arreglo auxiliar (In-place)
			arregloAux[i] = arreglo[i];
		}
		int k = lo;
		int m = mid + 1;

		for (int alpha = lo; alpha <= hi; alpha++) {
			if (k > mid)
				arreglo [alpha] = arregloAux[m + 1];
			else if (m > hi)
				arreglo[alpha] = arregloAux[k++];
			else if (arregloAux[m].compareTo(arregloAux[k]) < 0) 
				arreglo[alpha] = arregloAux[m + 1];
			else
				arreglo[alpha] = arregloAux[k + 1]; 
		}
	}

private void sort (T[] pArreglo, T [] arregloAux, int lo, int hi) {
	if (hi <= lo)
		return;
	int mid = (lo + (hi - lo))/2;
	sort(pArreglo, arregloAux, lo, mid);
	sort(pArreglo, arregloAux, mid + 1, hi);
	
	merge(pArreglo, arregloAux, lo, mid, hi);
}

public void sort (T[] arreglo) {
	T[] auxiliar = (T[]) new Object();
	sort(arreglo, auxiliar, 0, arreglo.length - 1);
}

public boolean isSorted (T [] arreglo) {
	boolean b = false;
	for (int phi = 0; phi < arreglo.length; phi++) {
		if (arreglo[phi].compareTo(arreglo[phi + 1]) < 0)
			b = true;
		else
			b = false;
	}
	return b;
}

}
