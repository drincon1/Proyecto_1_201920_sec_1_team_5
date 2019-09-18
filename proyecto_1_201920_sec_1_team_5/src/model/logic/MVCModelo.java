package model.logic;

import ordenamientos.*;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.opencsv.*;

import model.data_structures.DoublyLinkedList;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;



/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {


	//Instanciar Queue de tipo viaje
	private Queue<ViajeUber> viajesQueueH;
	private Queue<ViajeUber> viajesQueueS;
	private Queue<ViajeUber> viajesQueueM;

	//Instanciar Stack de tipo viaje
	private Stack<ViajeUber> viajesStack;

	//Instanciar una lista
	private DoublyLinkedList<ViajeUber> listaViajes;

	/**
	 * Metodo constructor 
	 * Inicializa el queue viajesQueue y el stack viajesStack
	 */
	public MVCModelo()
	{
		viajesQueueH = new Queue<ViajeUber>();
		viajesQueueS = new Queue<ViajeUber>();
		viajesQueueM = new Queue<ViajeUber>();
		viajesStack = new Stack<ViajeUber>();
		listaViajes = new DoublyLinkedList<ViajeUber>();
	}

	public MVCModelo(int capacidad)
	{

	}

	/**
	 * Metodo que carga los archivos cvs que contiene la informacion
	 * El trimestre puede ser 1, 2, 3
	 * @param trimestre De cual de los trimestre se va a descargar la informacion. trimestre == 1 || 
	 * trimestre == 2 || trimestre == 3 || trimestre == 4
	 * @throws IOException
	 */
	public void cargar(int trimestre) throws IOException
	{
		//String donde se va aguardar la ruta del archivo
		String rutaH = "./data/bogota-cadastral-2018-" + trimestre + "-All-HourlyAggregate.csv";
		String rutaS = "./data/bogota-cadastral-2018-" + trimestre + "-WeeklyAggregate.csv";
		String rutaM = "./data/bogota-cadastral-2018-" + trimestre + "-All-MonthlyAggregate.csv";

		//CSVReader donde se van a leer los datos
		CSVReader reader = null;

		ViajeUber nuevo = null;
		try
		{
			reader = new CSVReader(new FileReader(rutaH));
			String [] nextLine;
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null)
			{


				nuevo = new ViajeUber(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1]), 
						Double.parseDouble(nextLine[2]), -1,-1, Double.parseDouble(nextLine[3]), 
						Double.parseDouble(nextLine[4]), Double.parseDouble(nextLine[5]), Double.parseDouble(nextLine[6]));

				viajesQueueH.enqueue(nuevo);

			}

			reader = new CSVReader(new FileReader(rutaS));
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null)
			{


				nuevo = new ViajeUber(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1]), 
						-1, Integer.parseInt(nextLine[2]),-1, Double.parseDouble(nextLine[3]), 
						Double.parseDouble(nextLine[4]), Double.parseDouble(nextLine[5]), 
						Double.parseDouble(nextLine[6]));
				viajesQueueS.enqueue(nuevo);

			}
			reader = new CSVReader(new FileReader(rutaM));
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null)
			{


				nuevo = new ViajeUber(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1]), 
						-1, -1,Integer.parseInt(nextLine[2]), Double.parseDouble(nextLine[3]), 
						Double.parseDouble(nextLine[4]), Double.parseDouble(nextLine[5]), 
						Double.parseDouble(nextLine[6]));

				viajesQueueM.enqueue(nuevo);

			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
	}

	/**
	 * Devuelve la estructura deseada. Puede ser la que tiene el dia activado, mes activado o hora activada
	 * Activada es que uno de sus valores no sea -1
	 * @param tipo Puede ser "H" = hora, "S" = semana/dia o default = mes
	 * @return La estructura deseada
	 */
	public Queue<ViajeUber> darViajesQueue(String tipo)
	{
		switch(tipo)
		{
		case "H":
			return viajesQueueH;
		case "S":
			return viajesQueueS;
		default:
			return viajesQueueM;
		}

	}

	/**
	 * Metodo que busca el menor identificador de una zona
	 * @param viajes La estructura que se va revisar. viajes puede tener hora, dia o mes
	 * @return El menor identificador
	 */
	public double buscarMenorIdentificador(Queue<ViajeUber> viajes)
	{
		double menor = viajes.firstElement().getIdOrigen();
		Nodo<ViajeUber> temporal = viajes.firstNodo();
		while(temporal != null)
		{
			if(menor > temporal.darElemento().getIdOrigen())
			{
				menor = temporal.darElemento().getIdOrigen();
			}
			temporal = temporal.darSiguiente();
		}

		return menor;
	}

	/**
	 * Metodo que busca el mayor identificador de una zona
	 * @param viajes La estructura que se va a revisar. viajes puede tener hora, dia o mes
	 * @return El mayor identificador
	 */
	public double buscarMayorIdentificador(Queue<ViajeUber> viajes)
	{
		double mayor = viajes.firstElement().getIdOrigen();
		Nodo<ViajeUber> temporal = viajes.firstNodo();
		while(temporal != null)
		{
			if(mayor < temporal.darElemento().getIdOrigen())
				mayor = temporal.darElemento().getIdOrigen();
			temporal = temporal.darSiguiente();
		}

		return mayor;
	}

	/**
	 * Metodo que busca los viajes que esten en un dÃ­a especÃ­fico
	 * @param viajes La cola con los viajes. viajes != null
	 * @return Los viajes que tengan el dÃ­a.
	 */
	public Queue<ViajeUber> buscarPorDia(Queue<ViajeUber> viajes, int dia)
	{
		Queue<ViajeUber> encontrados = new Queue<ViajeUber>();
		Nodo<ViajeUber> temporal = viajes.firstNodo();
		while(temporal != null)
		{
			if(temporal.darElemento().getDia() == dia)
				encontrados.enqueue(temporal.darElemento());
			temporal = temporal.darSiguiente();
		}

		return encontrados;


	}

	//---------------------------------------------------
	// Requerimientos Parte A
	//---------------------------------------------------

	/**
	 * Consulta el tiempo promedio de viaje y su desviacion estandar 
	 * de los viajes entre una zona de origen y una destino para 
	 * un mes dado 
	 * @param pIdOrigen
	 * @param pIdDestino
	 * @param pMes
	 * @return La estructura con el tiempo promedio y la desviacion estandar
	 * @throws Exception si para el mes consultado no existe informacion
	 */
	public Queue<ViajeUber> tiempoPromedioDesviacionViajes (double pIdOrigen, double pIdDestino, int pMes) throws Exception {

		Queue<ViajeUber> viajesCondicion = new Queue<ViajeUber>();
		Queue<ViajeUber> buscarMes = new Queue<ViajeUber>();
		int x = 0;
		int y = 0;
		ViajeUber elementoActual = null;

		while (x <= viajesQueueM.tamano()){
			elementoActual = viajesQueueM.dequeue();
			if (elementoActual.getMes() == pMes)
				buscarMes.enqueue(elementoActual);
			x++;
		}while (y <= buscarMes.tamano()){
			ViajeUber temp = buscarMes.dequeue();
			if (temp.getIdOrigen() == pIdOrigen && temp.getIdDestino() == pIdDestino)
				viajesCondicion.enqueue(temp);
			y++;
		}
		return viajesCondicion;

	}

	public Queue<ViajeUber> darViajesMayorTiempoPromedio (int mesParametro, int N){

		Queue<ViajeUber> viajesMayorT = new Queue<ViajeUber>();
		Queue<ViajeUber> aux = new Queue<ViajeUber>();
		ViajeUber mayor = null;
		double max = 0.0;
		int iter = 0;

		while (iter <= viajesQueueM.tamano()) {
			ViajeUber act = viajesQueueM.dequeue();
			if (act.getMes() == mesParametro)
				aux.enqueue(act);
			while (iter <= aux.tamano() && aux.firstElement() != null) {
				mayor = aux.dequeue();
				ViajeUber anterior = aux.dequeue();
				if (mayor.getTiempoPromedio() < anterior.getTiempoPromedio()) {
					mayor = anterior;
					max = mayor.getTiempoPromedio();
				}
				if (max - anterior.getTiempoPromedio() <= 150)
					viajesMayorT.enqueue(anterior);
			}

			iter++;
		}
		return viajesMayorT;

	}

	/**
	 * Compara los tiempos promedios de los viajes para una zona dada contra
	 * cada zona x en un arreglo de zonas dado [zona menor, zona mayor] en ambos sentidos 
	 * @param zonaMenor
	 * @param zonaMayor
	 * @param zonaDada
	 * @param pMes
	 * @return
	 */
	public String compararTiemposPromZonaDadaZonaX (double zonaMenor, double zonaMayor, double zonaDada, int pMes){

		DoublyLinkedList<ViajeUber> listaPorMes = new DoublyLinkedList<ViajeUber>();
		DoublyLinkedList<ViajeUber> viajesOrigen = new DoublyLinkedList<ViajeUber>();
		ViajeUber[] viajesEnRango = null;
		String str = "";
		double zonaX = 0.0;

		for (int i = 0; i < viajesQueueM.tamano(); i++){
			ViajeUber act = viajesQueueM.dequeue();
			if (act.getMes() == pMes)
				listaPorMes.aniadirElemento(act);
		}


		for (int j = 0; j < listaPorMes.darTamanio(); j++){
			ViajeUber temp = listaPorMes.darElemento(j);
			if (temp.getIdOrigen() >= zonaMenor && temp.getIdDestino() <= zonaMayor) {
				viajesOrigen.aniadirElemento(temp);
				viajesEnRango = new ViajeUber[viajesOrigen.darTamanio()];
				viajesEnRango[j] = viajesOrigen.darElemento(j);
				ShellSort<ViajeUber> ordenarPorZonaX = new ShellSort<ViajeUber>(viajesEnRango);
			}
			for (int alpha = 0; alpha < viajesEnRango.length; alpha++) {
				ViajeUber x = viajesEnRango[alpha];
				ViajeUber y = viajesEnRango[alpha + 1];
				ViajeUber or = viajesOrigen.darElemento(alpha);
				zonaX = x.getIdDestino();
				if (x.getIdOrigen() == or.getIdOrigen() && x.getIdDestino() == zonaX)
					str = "El tiempo promedio: " + x.getTiempoPromedio() + "de" + zonaDada + "a" + x.getIdDestino();
				if (y.getIdDestino() == or.getIdOrigen() && y.getIdOrigen() == x.getIdDestino())
					str += "VS" + "Tiempo promedio: " + y.getTiempoPromedio() + "de " + x.getIdDestino() + "a" + or.getIdOrigen();
			}
		}
		return str;
	}


	//---------------------------------------------------------------------
	// Requerimientos Parte B
	//---------------------------------------------------------------------


	/**

	 * Muestra el tiempo de viaje promedio y la desviación estándar 

	 * de los viajes que se encuentran entre una zona origen y una 

	 * zona destino en un dia especifico. 

	 * @param pIdOrigen

	 * @param pIdDestino

	 * @param dia

	 * @return

	 */

	public Queue<ViajeUber> tiempoDesviacionEstandar( int dia, double pIdOrigen, double pIdDestino)

	{
		Queue<ViajeUber> viajes = new Queue<ViajeUber>();
		Queue<ViajeUber> encontrados = buscarPorDia(viajesQueueS, dia);
		if(!(encontrados.isEmpty()))
		{
			Nodo<ViajeUber> temporal = encontrados.firstNodo();
			while(temporal != null)
			{
				ViajeUber viajeTemp = temporal.darElemento();
				if(viajeTemp.getIdDestino() == pIdDestino &&  viajeTemp.getIdOrigen() == pIdOrigen )
					viajes.enqueue(viajeTemp);
			}
		}

		return viajes;

	}


	/**

	 * Método que devuelve los n viajes con mayor tiempo promedio

	 * @param dia Dia que quiere el usuario. n >=1 && n<=7

	 * @param n Cantidad de viajes que el usuario quiere saber

	 * @return

	 */
	public Queue<ViajeUber> mayorTiempoPromedioN(int dia, int n)

	{

		Queue<ViajeUber> viajes = buscarPorDia( viajesQueueS,dia);

		Nodo<ViajeUber> tempo = viajes.firstNodo();

		ViajeUber[] viajesArreglo = new ViajeUber[viajes.tamano()];

		int i = 0;

		//convierte la cola de los viajes de un dia a un arreglo

		if(!(viajes.isEmpty()))

		{

			while(tempo != null && i < viajes.tamano())

			{

				viajesArreglo[i] = tempo.darElemento();

				i++;

				tempo = tempo.darSiguiente();

			}

		}


		QuickSort<ViajeUber> viajesOrdenados = new QuickSort<ViajeUber>(viajesArreglo);


		Queue<ViajeUber> viajesN = new Queue<ViajeUber>();

		for(int x = 0; x < n; x++)

		{

			viajesN.enqueue(viajesArreglo[x]);

		}


		return viajesN;

	}


	/**

	 * Metodo que comparar los tiempos promedios de los viajes para una zona dada 

	 * contra cada zona en un rango de zonas dado en ambos sentidos para un día dado.

	 * Punto 3B

	 * @param zona

	 * @param zonaMin

	 * @param zonaMax

	 * @param dia

	 */

	public String[] comparaZonas(double zona, double zonaMin, double zonaMax, int dia)

	{


		//Cola con los viajes del día específico

		Queue<ViajeUber> viajesDelDia = buscarPorDia(viajesQueueS, dia);


		//Cola con los viajes que esten entre zonaMin y zonaMax

		Queue<ViajeUber> viajesZonasLimites = new Queue<ViajeUber>();


		//Indice que recorre los viajes de la cola viajesDelDia

		Nodo<ViajeUber> tempo = viajesDelDia.firstNodo();

		while(tempo != null)

		{

			ViajeUber elem = tempo.darElemento();

			if(elem.getIdDestino() <= zonaMax && elem.getIdOrigen() >= zonaMin)

				viajesZonasLimites.enqueue(elem);

			tempo = tempo.darSiguiente();

		}


		//La cola con los viajes que tenga la zona dada igual a la zonaOrigen

		Queue<ViajeUber> viajesZona = new Queue<ViajeUber>();

		//Indice que recorre los viajes de la cola viajesDelDia

		tempo = viajesDelDia.firstNodo();

		while(tempo != null)

		{

			ViajeUber elem = tempo.darElemento();

			if(elem.getIdOrigen() == zona)

				viajesZona.enqueue(elem);

			tempo = tempo.darSiguiente();

		}


		//Convierte la cola viajesZonasLimites a un arreglo para que este pueda ser ordenado

		ViajeUber[] arregloViajesZonasLimites = new ViajeUber[viajesZonasLimites.tamano()];

		int i = 0; 

		tempo = viajesZonasLimites.firstNodo();

		while(tempo != null)

		{

			arregloViajesZonasLimites[i] = tempo.darElemento();

			tempo = tempo.darSiguiente();

			i++;

		}


		ShellSort<ViajeUber> ordenador = new ShellSort<ViajeUber>(arregloViajesZonasLimites);


		//Llama al método comparadorConRespuesta para que este compare y configure el mensaje

		//de salida

		if(arregloViajesZonasLimites[0] != null && !(viajesZona.isEmpty()))

			return comparadorConRespuesta(arregloViajesZonasLimites,viajesZona);


		else

			return null;


	}

	public String[] comparadorConRespuesta(ViajeUber[] arreglo, Queue<ViajeUber> cola)

	{

		String[] respuesta = null;

		//Define que tamaño va a tener el arreglo

		if(arreglo.length > cola.tamano())

			respuesta = new String[arreglo.length];

		else 

			respuesta = new String[cola.tamano()];


		int i = 0;

		int j = 0;

		Nodo<ViajeUber> tempo = cola.firstNodo();

		while(i < respuesta.length)

		{

			if(j < arreglo.length && arreglo[j] != null && tempo != null)

				respuesta[i] = tempo.darElemento().getTiempoPromedio() + " de " + tempo.darElemento().getIdOrigen() + "a" + arreglo[j].getIdOrigen() 

				+ " vs " + arreglo[j].getTiempoPromedio() + " de " + arreglo[j].getIdOrigen() + " a " + tempo.darElemento().getIdOrigen();

			else if(j < arreglo.length && arreglo[j] == null && tempo != null)

			{

				respuesta[i] = tempo.darElemento().getTiempoPromedio() + " de " + tempo.darElemento().getIdOrigen() + "a" + arreglo[j].getIdOrigen() 

						+ " vs No hay viajes entre las zonas delimitadas";

			}

			else if(j < arreglo.length && arreglo[j] != null && tempo == null)

			{

				respuesta[i] = "No hay viajes de Zona dada vs " + arreglo[j].getTiempoPromedio() + " de " + arreglo[j].getIdOrigen() + " a " + tempo.darElemento().getIdOrigen();;

			}

			tempo = tempo.darSiguiente();

			i++;

			j++;

		}
		return respuesta;

	}



	//---------------------------------------------------------------------
	// Requerimientos Parte C
	//---------------------------------------------------------------------

	/**

	 * Consultaa los viajes entre zonas en un margen de tiempo. 

	 * Punto 1C

	 * @param hInicio Hora inicial del margen. hInicio >= 0 && hInicio <= 23

	 * @param hFinal Hora final del margen. hFinal >= 0 && hFinal <= 23 && hFinal > hInicio

	 * @param IdOrigen El id de la zona origen del viaje. IdOrigen > 0 

	 * @param IdDestino El id de la zona destino del viaje. IdOrigen > 0

	 * @return

	 */

	public Queue<ViajeUber> horasEntreZonas(int hInicio, int hFinal, double IdOrigen, double IdDestino)

	{

		Queue<ViajeUber> viajes = new Queue<ViajeUber>();


		Nodo<ViajeUber> tempo = viajesQueueH.firstNodo();


		while(tempo != null)

		{

			ViajeUber elem = tempo.darElemento();

			if(elem.getHora() >= hInicio && elem.getHora() <= hFinal)

			{

				if(elem.getIdDestino() == IdDestino && elem.getIdOrigen() == IdOrigen)

					viajes.enqueue(elem);

			}


			tempo = tempo.darSiguiente();

		}


		return viajes;

	}
	
	/**
	 * Consulta los N viajes con mayor tiempo promedio
	 * @param pHora
	 * @param N
	 * @return
	 */
	public Stack<ViajeUber> consultarViajesMayorTiempoPromedio (int pHora, int N) {

		Stack<ViajeUber> viajesPila = new Stack<ViajeUber>();
		ViajeUber [] viajesN = null;
		ViajeUber x = null;

		for (int i = 0; i < this.viajesStack.darTamanoStack(); i++) {
			x = viajesStack.pop();
			if (x.getHora() == pHora) {
				viajesPila.push(x);
				try {
					viajesN = new ViajeUber[N];
					viajesN[i] = x;
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			ViajeUber [] mergeAux = new ViajeUber[viajesN.length];
			MergeSort<ViajeUber> sort = new MergeSort<ViajeUber>(viajesN, mergeAux);
			viajesPila.push(viajesN[i]);
		}

		return viajesPila;

	}

	/**

	 * Generar una gráfica ASCII que muestra el tiempo promedio de los viajes 

	 * entre una zona origen y una zona destino para cada hora del día.

	 * Punto C3

	 * @param zonaOrigen Id de la zona de origen

	 * @param zonaDestino Id de la zona de destino

	 * @return Un arreglo con la cantidad de minutos por hora de los viajes

	 */

	public int[] graficaASCII(double zonaOrigen, double zonaDestino)

	{

		int[] grafica = new int[24];


		Nodo<ViajeUber> tempo = viajesQueueH.firstNodo();

		int hora  = 0;

		int minutos = 0;


		while(tempo != null)

		{

			ViajeUber elem = tempo.darElemento();

			if(elem.getIdDestino() == zonaDestino && elem.getIdOrigen() == zonaOrigen)

			{

				hora = (int) elem.getHora();

				minutos = (int) elem.getTiempoPromedio()/60;

				grafica[hora] += minutos;


			}

			tempo = tempo.darSiguiente();

		}


		return grafica;

	}



}
