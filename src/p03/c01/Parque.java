package src.p03.c01;

/*
 * Pr�ctica 3 de Dependecias de Estados (Parque)
 * @ version: 1.0
 * @ date: 15/04/2021
 * @ author: Miguel Angulo Fern�ndez
 * @ author: Alejandro Fern�ndez Berrio
 * @ author: Jose Barbero
 * */

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	private final int maxPersonasTotales = 50;
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}

	//m�todo para obtener el atributo privado contadorPersonasTotales
	@Override
	public int getContadorPT() {
		return contadorPersonasTotales;
	}
	
	@Override
	public synchronized void entrarAlParque(String puerta){
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		//si no puede entrar nadie, no dejamos que se entre
		//si no hay nadie, no dejamos que se salga
		if(contadorPersonasTotales == maxPersonasTotales) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(contadorPersonasTotales == 0) {
			this.notify();
		}
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// Comprobamos las invariantes
		checkInvariante();
	}
	
	@Override
	public synchronized void salirDelParque(String puerta) {
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		//wait/notify
		if(contadorPersonasTotales == maxPersonasTotales) {
			this.notify();
		}else if(contadorPersonasTotales == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Disminuimos el contador
		contadorPersonasTotales--;
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");
		
		// Comprobamos las invariantes
		checkInvariante();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		comprobarAntesDeEntrar();
		comprobarAntesDeSalir();
	}

	protected void comprobarAntesDeEntrar(){
		assert contadorPersonasTotales < maxPersonasTotales : "INV: No hay espacio para que entre más gente al parque";
	}

	protected void comprobarAntesDeSalir(){
		assert contadorPersonasTotales > 0 : "INV: No hay nadie en el parque, nadie puede salir";
	}
}