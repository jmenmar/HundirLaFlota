package packMainJava;

public abstract class Jugador {

	public static Tablero tableroIA = new Tablero();
	public static Tablero tableroJ = new Tablero();
		
	public void ponerBarcos()
	{
		
	}
	abstract boolean puedePonerBarco(Tablero tab,int tam,int fila, int columna, boolean hor);
	abstract void ponerBarco(Tablero tab, Barco pBarco);
	abstract void act();
}
