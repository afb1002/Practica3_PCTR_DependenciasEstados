package src.p03.c01;

/*
 * Práctica 3 de Dependecias de Estados (Parque)
 * @ version: 1.0
 * @ date: 15/04/2021
 * @ author: Miguel Angulo Fernández
 * @ author: Alejandro Fernández Berrio
 * @ author: Jose Barbero
 * */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadSalidaPuerta implements Runnable{

	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;
	
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}
	
	@Override
	public void run() {
		//mientras el parque no esté vacío, se podrá salir de el.
		for (int i = 0; i < NUMSALIDAS; i ++) {
			try {
				parque.salirDelParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
