package packMainJava;

import java.awt.Color;
import java.util.Observable;

public abstract class Jugador extends Observable {

	private Tablero tableroIA = new Tablero();
	private Tablero tableroJ = new Tablero();
	private int portaaviones = 1;
	private int submarinos = 2;
	private int destructores = 3;
	private int fragatas = 4;
	private int numMisiles = 5;
	private int numEscudos = 2;
	private int numReparaciones = 1;
	private int numRadar = 1;
	private int dinero = 1000;

	// Compruebas que la fila y la columna estan dentro del tablero
	private boolean celdaEstaEnTablero(int fila, int columna) {
		if (fila < 0)
			return false;
		if (columna < 0)
			return false;
		if (fila >= 10)
			return false;
		if (columna >= 10)
			return false;

		return true;
	}

	public boolean puedePonerBarco(int tam, int fila, int columna, boolean hor) {
		int filai,filaf,columnai,columnaf;
		if (hor) {

			if (!celdaEstaEnTablero(fila, columna)
					|| !celdaEstaEnTablero(fila, columna + tam-1)) {
				return false;

			}

		} else {
			if (!celdaEstaEnTablero(fila, columna)
					|| !celdaEstaEnTablero(fila+tam-1, columna)) {
				return false;

			}
		}

		// Comprueba si hay un barco
		// Calcular fila final y columan final en función dehor y vert
		if(hor){
			filai=Math.max(0,fila - 1);
			filaf=Math.min( fila + 1,9);

			columnai=Math.max(0,columna - 1);
			columnaf=Math.min( columna +tam,9);
			
		}else{
			filai=Math.max(0,fila - 1);
			filaf=Math.min( fila + tam ,9);
			columnai=Math.max(0,columna - 1);
			columnaf=Math.min( columna + 1,9);
			
		}
		for (int fila2 = filai; fila2 <= filaf; fila2++) {
			for (int columna2 = columnai; columna2 <= columnaf; columna2++) {
				if (celdaEstaEnTablero(fila2, columna2)) {
					if (tableroJ.getCasilla(fila2, columna2).getEstado() != CasillaEstado.AGUA) {
						return false;
					}

				}
			}
		}
		return true;

	}

	public boolean comprobarNumBarcos(TipoDeBarco tipob) {
		switch (tipob) {
		case SUBMARINO:
			return submarinos > 0;
		case PORTAAVIONES:
			return portaaviones > 0;
		case DESTRUCTOR:
			return destructores > 0;
		case FRAGATA:
			return fragatas > 0;
		}
		return false;
	}

	public boolean addBarco(TipoDeBarco tipob, int fila, int columna, boolean hor) {
		if (comprobarNumBarcos(tipob)
				&& puedePonerBarco(tipob.getLongitud(), fila, columna, hor)) {
			Barco pBarco = new Barco(tipob);
			for (int i = 0; i < tipob.getLongitud(); i++) {
				if (hor) {
					tableroJ.getCasilla(fila, columna + i).setEstado(
							CasillaEstado.OCUPADA);
					tableroJ.getCasilla(fila, columna + i)
							.setOcupadaPor(pBarco);
				} else {
					tableroJ.getCasilla(fila + i, columna).setEstado(
							CasillaEstado.OCUPADA);
					tableroJ.getCasilla(fila + i, columna)
							.setOcupadaPor(pBarco);
				}
			}

			decrementarContBarco(tipob);
			setChanged();
			notifyObservers(); // Indicar que es el tablero de barcos
			return true;
		}
      return false;
	}
	
	private void decrementarContBarco(TipoDeBarco tipob) {
		switch (tipob) {
		case SUBMARINO:
			 submarinos--;
			 break;
		case PORTAAVIONES:
			portaaviones--;
			break;
		case DESTRUCTOR:
			destructores--;
			break;
		case FRAGATA:
			fragatas--;
			break;
		}
	}

	public CasillaEstado getEstadoCasillaBarcoJugador(int fila, int columna) {
		return tableroJ.getCasilla(fila, columna).getEstado();
	}
	public CasillaEstado getEstadoCasillaBarcoIA(int fila, int columna) {
		return tableroIA.getCasilla(fila, columna).getEstado();
	}
	public int getNumEscudos() {
		return numEscudos;
	}
	public void setNumEscudos(int numEscudos) {
		this.numEscudos = numEscudos;
	}
	public int getNumReparaciones() {
		return numReparaciones;
	}
	public void setNumReparaciones(int numReparaciones) {
		this.numReparaciones = numReparaciones;
	}
	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

}
