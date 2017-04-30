package packMainJava;

import java.awt.event.MouseEvent;

//Habra que hacerlo singleton
public class Player extends Jugador {


	public Player(){
	int pFila=0;
	int pCol=0;
	int pTam=5;
	int pHor=0;
	}

	@Override
	boolean puedePonerBarco(Tablero tab, int tam, int fila, int columna,
			boolean hor) {
		return false;
	}

	@Override
	void ponerBarco(Tablero tab, Barco pBarco) {
		// TODO Auto-generated method stub
		
	}
	void act()
	{
		
	}
}

