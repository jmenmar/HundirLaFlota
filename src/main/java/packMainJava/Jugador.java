package packMainJava;

public abstract class Jugador {

	Tablero tableroPropio = new Tablero();
	Tablero tableroOponente = new Tablero();

	public void ponerBarcos()
	{
		
	}
	abstract boolean puedePonerBarco(Tablero tab,int tam,int fila, int columna, boolean hor);
	abstract void ponerBarco(Tablero tab, Barco pBarco);
	abstract void act();
}
