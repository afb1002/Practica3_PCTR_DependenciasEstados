package src.p03.c01;

/*
 * Pr�ctica 3 de Dependecias de Estados (Parque)
 * @ version: 1.0
 * @ date: 15/04/2021
 * @ author: Miguel Angulo Fern�ndez
 * @ author: Alejandro Fern�ndez Berrio
 * @ author: Jose Barbero
 * */

public class SistemaLanzador {
	public static void main(String[] args) {
		
		IParque parque = new Parque();
		char letra_puerta = 'A';
		
		System.out.println("¡Parque abierto!");
		
		for (int i = 0; i < 5; i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread (entradas).start();
			// Creación de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (salidas).start();
		}
	}	
}