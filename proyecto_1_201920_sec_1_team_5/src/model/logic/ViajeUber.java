package model.logic;

public class ViajeUber implements Comparable<ViajeUber>
{

	//-------------------------------------------
	//Atributos
	//-------------------------------------------


	private double idOrigen;
	private double idDestino;
	private int mes;
	private int dia;
	private double hora;
	private double tiempoPromedio;
	private double desviacion;
	private double geometrico;
	private double desviGeometrica;


	/**
	 * Metodo constructor
	 * @param idOrigen Id de la zona origen
	 * @param idDestino ID de la zona destino
	 * @param hora Hora del d√≠a. Si hora == -1, los datos analizados son dia o mes
	 * @param dia Dia de la semana. Si dia == -1, los datos analizados son hora o mes
	 * @param mes Mes del a√±o. Si mes == -1, los datos analizados son hora o dia
	 * @param tiempoPromedio Tiempo promedio en segundos de los viajes relacionados
	 * @param desviacion DesviacioÃ?n estaÃ?ndar de los viajes relacionados
	 * @param geometrico Tiempo promedio geomeÃ?trico en segundos de los viajes relacionados
	 * @param desviGeometrica DesviacioÃ?n estaÃ?ndar geomeÃ?trica de los viajes relacionados
	 */
	public ViajeUber(double idOrigen , double idDestino, double hora, int dia, int mes, double tiempoPromedio, double desviacion, double geometrico, double desviGeometrica)
	{
		this.idOrigen = idOrigen;
		this.idDestino = idDestino;
		this.hora = hora;
		this.dia = dia;
		this.mes = mes;
		this.tiempoPromedio = tiempoPromedio;
		this.desviacion = desviacion;
		this.geometrico = geometrico;
		this.desviGeometrica = desviGeometrica;
	}


	//-------------------------------------------
	//Metodos
	//-------------------------------------------


	/**
	 * Permite obtener el ID de origen de un viaje
	 * @return idOrigen
	 */
	public double getIdOrigen () {
		return idOrigen;
	}

	/**
	 * Permite obtener el ID de destino de un viaje
	 * @return idDestino
	 */
	public double getIdDestino () {
		return idDestino;
	}


	/**
	 * Permite obtener el tiempo promedio de un viaje
	 * @return tiempoPromedio
	 */
	public double getTiempoPromedio () {
		return tiempoPromedio;
	}

	/**
	 * Permite obtener la desviacion
	 * @return desviacion
	 */
	public double getDesviacion () {
		return desviacion;
	}

	/**
	 * Permite obtener el tiempo proncipal del viaje de forma geometrica
	 * @return geometrico
	 */
	public double getGeometrico () {
		return geometrico;
	}

	/**
	 * Permite obtener la desviacion estandar geometrica de un viaje
	 * @return desviGeometrica
	 */
	public double getDesviGeometrica () {
		return desviGeometrica;
	}

	/**
	 * Permite obtener la hora de un viaje
	 * @return hora
	 */
	public double getHora () {
		return hora;
	}

	/**
	 * M√©todo para devolver la informacion del dia
	 * @return dia
	 */
	public int getDia()
	{
		return dia;
	}

	/**
	 * M√©todo para devolver la informacion sobre que mes
	 * @return mes
	 */
	public int getMes()
	{
		return mes;
	}


	@Override
	public int compareTo(ViajeUber o) 
	{
		if(this.tiempoPromedio < o.tiempoPromedio)
			return -1;
		else if(this.tiempoPromedio > o.tiempoPromedio)
			return 1;
		return 0;
	}

}
