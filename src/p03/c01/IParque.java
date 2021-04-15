package src.p03.c01;

/*
 * Práctica 3 de Dependecias de Estados (Parque)
 * @ version: 1.0
 * @ date: 15/04/2021
 * @ author: Miguel Angulo Fernández
 * @ author: Alejandro Fernández Berrio
 * @ author: Jose Barbero
 * */

public interface IParque {
	
	public abstract void entrarAlParque(String puerta);

	public abstract void salirDelParque(String puerta);

	//Esta declaración de método se encuntra aquí porque eclipse nos pedía que la pusieramos para evitar un warning
	
	public abstract int getContadorPT();

}
