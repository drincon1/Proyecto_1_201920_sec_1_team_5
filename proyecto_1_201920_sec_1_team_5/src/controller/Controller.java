package controller;

import java.io.IOException;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller () throws IOException
	{
		modelo = new MVCModelo();
		view = new MVCView();

	}

	public void run() throws IOException 
	{
		//Menu que imprime que archivo quiere usar el usuario
		int trimestre = 0;
		while(true)
		{
			trimestre = view.printMenuCarga();
			if(trimestre >= 1 && trimestre <= 4)
				break;
			System.out.println("El numero tiene que estar entre 1 y 4");

		}

		//Manda la informacion a modelo para que el la cargue
		modelo.cargar(trimestre);

		//Manda el view el queue con la información ya agregada, el menor identificador y el mayor identificador 
		view.printRespuestaCarga(modelo.darViajesQueue("H"), modelo.buscarMenorIdentificador(modelo.darViajesQueue("H")), modelo.buscarMayorIdentificador(modelo.darViajesQueue("H")));
		view.printRespuestaCarga(modelo.darViajesQueue("S"), modelo.buscarMenorIdentificador(modelo.darViajesQueue("S")), modelo.buscarMayorIdentificador(modelo.darViajesQueue("S")));
		view.printRespuestaCarga(modelo.darViajesQueue("M"), modelo.buscarMenorIdentificador(modelo.darViajesQueue("M")), modelo.buscarMayorIdentificador(modelo.darViajesQueue("M")));

		
		//interaccion entre usuario y plataforma
		//usuario escoje que quiere ver
		int opcion = view.printMenu();
		
		int[] operacion = view.printOpciones();
		
		switch(opcion)
		{
		//Manipular con datos de viajes por hora
		case 1: 
			if(operacion[0] == 1)
				return;
			else if(operacion[0] == 2)
				return;
			else if(operacion[0] == 3) 
				return;
				
			break;
		//Manipular con datos de viajes por dia	
		case 2:
			if(operacion[0] == 1)
				return;
			else if(operacion[0] == 2)
				view.printInfoDias(modelo.tiempoDesviacionEstandar(operacion[1], (double) operacion[2], (double) operacion[3]), 2);
			else if(operacion[0] == 3) 
				return;
				
			break;
		//Manipular con datos de viajes por mes
		default: 
			if(operacion[0] == 1)
				return;
			else if(operacion[0] == 2)
				return;
			else if(operacion[0] == 3) 
				return;
			break;
		}



	}	
}
