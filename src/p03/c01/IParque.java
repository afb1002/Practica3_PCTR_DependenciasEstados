package src.p03.c01;

/*
 * Pr�ctica 3 de Dependecias de Estados (Parque)
 * @ version: 1.0
 * @ date: 15/04/2021
 * @ author: Miguel Angulo Fern�ndez
 * @ author: Alejandro Fern�ndez Berrio
 * @ author: Jose Barbero
 * */

public interface IParque {
	
	public abstract void entrarAlParque(String puerta);

	public abstract void salirDelParque(String puerta);

	//Esta declaraci�n de m�todo se encuntra aqu� porque eclipse nos ped�a que la pusieramos para evitar un warning
	
	public abstract int getContadorPT();

}
