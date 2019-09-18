package ordenamientos;

import model.data_structures.*;
import model.logic.ViajeUber;
import view.MVCView;

public class ShellSort <T extends Comparable<T>>
{
	//-------------------------------
	// Atributos
	//-------------------------------

	/**
	 * Referencia a la clase view para mostrar los tiempos de ejecución
	 */
	private MVCView vista;

	/**
	 * Metodos de la estructura
	 */
	public ShellSort (T [] arreglo) {

		vista = new MVCView();
		long tiempoInicial = System.currentTimeMillis();
		
		sort(arreglo);
		
		long tiempoFinal = System.currentTimeMillis();
		long duracion = tiempoFinal - tiempoInicial;
		vista.printMessage("La duracion de ejecucion de Shell Sort es de: " + duracion + " milisegundos");

	}

	public void sort (T [] pArreglo) {

		//se define el intervalo para el ordenamiento
		int h = 1;
		while (h < pArreglo.length/3) {
			h = 3*h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < pArreglo.length; i++) {
				for (int j = i; j >= h && pArreglo[j].compareTo(pArreglo[j-h]) > 0; j -= h) {
					T temp = pArreglo[i];
					pArreglo[i] = pArreglo [j];
					pArreglo[j] = temp;
				}
			}
			if (isSorted(pArreglo))
				h /= 3;
		}
	}

	public boolean isSorted (T[] arregloX) {
		for (int i = 0; i < arregloX.length; i++) {
			if (arregloX [i].compareTo(arregloX [i + 1]) < 0)
				return true;
		}
		return false;
	}
}
