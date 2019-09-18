package view;

import java.util.*;

import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.ViajeUber;

public class MVCView 
{
	Scanner lector;

	/**
	 * Metodo constructor
	 */
	public MVCView()
	{
		lector = new Scanner(System.in);
	}

	public int printMenu()
	{
		int opcion = 0;
		while(true)
		{
			System.out.println("¿Cuál información quiere manipular?\n1)Horas\n2)Dias\n3)Mes");
			System.out.println("*-1 para salir*");
			opcion = lector.nextInt();
			if(opcion == -1 || (opcion <= 9 && opcion >= 1))
				break;
		}

		return opcion;
	}

	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	/**
	 * Este método carga el primer menu que le aparece al usuario
	 * El usuario escoge que trimestre analizar
	 * @return 	Devuelve un int con el trimestre escogido 
	 */
	public int printMenuCarga()
	{
		System.out.println("¿Qué trimestre quiere ver?\n1)1\n2)2\n3)3\n4)4");
		int trimestre = lector.nextInt();
		return trimestre;
	}

	public void printRespuestaCarga(Queue<ViajeUber> viajes, double menorIdentificador, double mayorIdentificador)
	{
		double hora = viajes.firstElement().getHora();
		int dia = viajes.firstElement().getDia();
		int mes = viajes.firstElement().getMes();
		if(hora != -1)
		{
			System.out.println("El total de viajes  por hora en el archivo es: " + viajes.tamano());
			System.out.println("La zona con menor identificador es: " + menorIdentificador );
			System.out.println("La zona con mayor identificador es: " + mayorIdentificador);
		}
		else if(dia != -1)
		{
			System.out.println("El total de viajes  por semana en el archivo es: " + viajes.tamano());
			System.out.println("La zona con menor identificador es: " + menorIdentificador);
			System.out.println("La zona con mayor identificador es: " + mayorIdentificador);
		}
		else if(mes != -1)
		{
			System.out.println("El total de viajes  por mes en el archivo es: " + viajes.tamano());
			System.out.println("La zona con menor identificador es: " + menorIdentificador);
			System.out.println("La zona con mayor identificador es: " + mayorIdentificador);
		}
	}

	public void printInfoHoras(Queue<ViajeUber> viajes, int opcion)
	{

	}
	public void printInfoDias(Queue<ViajeUber> viajes, int opcion)
	{
		if(viajes.isEmpty())
		{
			System.out.println("No se encontró ningun viaje entre esas zonas");
		}
		else
		{
			Nodo<ViajeUber> temporal = viajes.firstNodo();
			int i = 1;
			while(temporal != null)
			{
				System.out.println(i + ")El tiempo promedio del viaje es: " + temporal.darElemento().getTiempoPromedio() + 
						" y la desviación estándar es: " + temporal.darElemento().getDesviacion());
				temporal = temporal.darSiguiente();
				i++;
			}
			
		}
	}
	public void printInfoMeses(Queue<ViajeUber> viajes, int opcion)
	{

	}

	public int[] printOpciones()
	{
		int[] info = new int[10];
		System.out.println("Escoja una opción que quiera ver");
		int opcion = -1;
		while(true)
		{
			System.out.println("1)Consultar el tiempo de viaje promedio y desviación estándar \nentre dos zonas en un día específico ");
			System.out.println("2)Consultar la información de los viajes con mayor tiempo promedio para un día");
			System.out.println("3)Comparar los tiempos promedios de una zona contra un rango de zonas.");
			opcion = lector.nextInt();
			if(opcion == -1 || (opcion <= 3 && opcion >= 1))
				break;
		}
	
		info[0] = opcion;
		System.out.println();
		
		System.out.println("�Cuantos viajes le interesa?");
		info[1] = lector.nextInt();
		
		if(opcion == 1)
		{
			System.out.println("Zona inicial");
			info[2] = lector.nextInt();
			System.out.println("Zona final");
			info[3] = lector.nextInt();
		}
		else if(opcion == 2)
		{
			System.out.println("�Cuantos viajes le interesa?");
			info[2] = lector.nextInt();
		}
		else
		{
			System.out.println("Zona inicial");
			info[2] = lector.nextInt();
			System.out.println("Zona final");
			info[3] = lector.nextInt();
			System.out.println("Zona especifica");
			info[4] = lector.nextInt();
			
		}
		return info;
	}
	
	
}
