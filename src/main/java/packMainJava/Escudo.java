package packMainJava;

import java.util.Observable;

public class Escudo extends Recurso{
	
	public void act(int x, int y, Tablero tab)
	{
		cumplirFuncion(tab.getCasilla(x, y));
	}
	
	private static Escudo shield = new Escudo();
	
	//Constructor
	public Escudo(){		
	}
	  //Debe comprobar que hay un barco debajo, de que tipo es el barco para saber como pintar
	 //El barco no debe estar hundido, tampoco se pueden poner mas de un escudo por turno
	 // El escudo pondra en estado protegido a las casillas segun como esta colado el barco
	public void cumplirFuncion(Casilla pCasilla) {
		pCasilla.getOcupadaPor().setProtegido(true);
		setChanged();
		notifyObservers(); // Indicar que es el tablero de barcos
		
	}
	
	public static Escudo getShield()
	{
		return shield;
	}

}
