package packMainJava;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

public abstract class Jugador extends Observable {
	private Tablero tableroIA = new Tablero(false);
	private Tablero tableroJ = new Tablero(false);
	private int portaaviones = 1;
	private int submarinos = 2;
	private int destructores = 3;
	private int fragatas = 4;
	private int numMisiles = 5;
	private int numEscudos = 2;
	private int numReparaciones = 1;
	private int numRadar = 1;
	private int dinero = 1000;
	//private Barco[] laArmadaInvencible = new Barco[10];
	private Set<Barco> laArmadaInvencible = new HashSet<Barco>();
	private Jugador oponente;
	Inventario inv = Inventario.getInventario(); // Instancia única al Singleton

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
		int filai, filaf, columnai, columnaf;
		if (hor) {

			if (!celdaEstaEnTablero(fila, columna)
					|| !celdaEstaEnTablero(fila, columna + tam - 1)) {
				return false;

			}

		} else {
			if (!celdaEstaEnTablero(fila, columna)
					|| !celdaEstaEnTablero(fila + tam - 1, columna)) {
				return false;

			}
		}

		// Comprueba si hay un barco
		// Calcular fila final y columan final en función dehor y vert
		if (hor) {
			filai = Math.max(0, fila - 1);
			filaf = Math.min(fila + 1, 9);

			columnai = Math.max(0, columna - 1);
			columnaf = Math.min(columna + tam, 9);

		} else {
			filai = Math.max(0, fila - 1);
			filaf = Math.min(fila + tam, 9);
			columnai = Math.max(0, columna - 1);
			columnaf = Math.min(columna + 1, 9);

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

	public boolean addBarco(TipoDeBarco tipob, int fila, int columna,
			boolean hor) {
		if (comprobarNumBarcos(tipob)
				&& puedePonerBarco(tipob.getLongitud(), fila, columna, hor)) {
			Barco pBarco = new Barco(tipob);
			//Casilla pOcupa[] = new Casilla[tipob.getLongitud()];
			Map<Casilla, Boolean> mapa = new HashMap<Casilla, Boolean>();
			for (int i = 0; i < tipob.getLongitud(); i++) {
				if (hor) {
					tableroJ.getCasilla(fila, columna + i).setEstado(
							CasillaEstado.OCUPADA);
					tableroJ.getCasilla(fila, columna + i)
							.setOcupadaPor(pBarco);
					mapa.put(tableroJ.getCasilla(fila, columna + i), false);
					//pOcupa[i] = tableroJ.getCasilla(fila, columna + i);
					
				} else {
					tableroJ.getCasilla(fila + i, columna).setEstado(
							CasillaEstado.OCUPADA);
					tableroJ.getCasilla(fila + i, columna)
							.setOcupadaPor(pBarco);
					mapa.put(tableroJ.getCasilla(fila + i, columna), false);
					//pOcupa[i] = tableroJ.getCasilla(fila + i, columna);
				}
				//pBarco.setPosicion(pOcupa);
				pBarco.setMapa(mapa);
				addBarcoToArmadaInvencible(pBarco);
			}

			decrementarContBarco(tipob);
			setChanged();
			notifyObservers(); // Indicar que es el tablero de barcos
			return true;
		}
		return false;
	}

	public void addBarcoToArmadaInvencible(Barco sanJuanNepomuceno) {
		/*boolean encontrado = false;
		int cont = 0;
		while (cont < 10 && !encontrado) {
			if (laArmadaInvencible[cont] != null) {
				cont++;
			} else {
				laArmadaInvencible[cont] = sanJuanNepomuceno;
				encontrado = true;
			}
		}
		*/
		/*
		 * if(cont > 9) { laArmadaInvencible[0] = sanJuanNepomuceno; }
		 */
		laArmadaInvencible.add(sanJuanNepomuceno);
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

	public Barco getBarcoEnCasilla(int fila, int columna) {
		return tableroJ.getCasilla(fila, columna).getOcupadaPor();
	}

	public Casilla getCasillaJugador(int fila, int columna) {
		return tableroJ.getCasilla(fila, columna);
	}

	public Casilla getCasillaIA(int fila, int columna) {
		return tableroIA.getCasilla(fila, columna);
	}

	public void setEscudoEnBarco(int fila, int columna) {
	//	if (inv.getNumEscudos() > 0) {
			Casilla pCasilla = tableroJ.getCasilla(fila, columna);
			if (pCasilla.getEstado() != CasillaEstado.AGUA) {
				if (pCasilla.getOcupadaPor().getEstado() != Status.HUNDIDO) {
					pCasilla.getOcupadaPor().setProtegido(true);
					//inv.restarEscudo();
					setChanged();
					notifyObservers();
				}			
			}
		//}
	}

	public void usarRadar(int fila, int columna, Jugador pJugador) {
		//if(inv.getNumRadares() > 0){
			ArrayList<Casilla> lista = new ArrayList<Casilla>();
			int filaInicial = Math.max(0, fila - 1);
			int filaFinal = Math.min(tableroJ.getMaxFil() - 1, fila + 1);
			int colInicial = Math.max(0, columna - 1);
			int colFinal = Math.min(tableroJ.getMaxCol(), columna + 1);
	
			for (int i = filaInicial; i <= filaFinal; i++) {
				for (int j = colInicial; j <= colFinal; j++) {
					Casilla pCasilla = pJugador.getCasillaJugador(i, j);
					if (pCasilla.getOcupadaPor() != null) {
						lista.add(pCasilla);
					}
				}
			}
	
			if (lista.size() > 0) {
				Random rnd = new Random();
				int randomNum;
				randomNum = rnd.nextInt(lista.size());
				Casilla resultado = lista.get(randomNum);
				resultado.setDetectada(true);
				setChanged();
				notifyObservers();
			}
			//inv.restarRadar();
		//}
	}

	public void repararBarco(int fila, int columna) {
		//if (inv.getNumReparaciones() > 0) {
			Barco normandy = getCasillaJugador(fila, columna).getOcupadaPor();
			if (normandy != null && normandy.getEstado() != Status.HUNDIDO) {
				normandy.repararBarco();
				normandy.setEstado(Status.INTACTO);
				setChanged();
				notifyObservers();
				//inv.restarReparacion();
			}
		//}
	}

	public void usarBomba(int fila, int columna) {
		// Casilla pCasilla = tableroJ.getCasilla(fila, columna);
		Casilla pCasilla = getOponente().getCasillaJugador(fila, columna); // MODIFICADO!
																		// (En
																		// pruebas)
		// Casilla pCasilla = getCasillaIA(fila, columna);
		// Barco tangoZulu = getCasillaIA(fila,columna).getOcupadaPor();
		Barco tangoZulu =getOponente().getBarcoEnCasilla(fila, columna);
		if (tangoZulu != null) {
			if (!tangoZulu.isProtegido()) {
				tangoZulu.setImpacto(pCasilla);
				tableroIA.getCasilla(fila, columna).setEstado(CasillaEstado.TOCADO);
			} else {
				tangoZulu.setProtegido(false);
				tableroIA.getCasilla(fila, columna).setEstado(CasillaEstado.OCUPADA);
			}

			pCasilla.setRevelado(true);
			
			
		} else {

			tableroIA.getCasilla(fila, columna).setEstado(CasillaEstado.AGUA);
			pCasilla.setRevelado(true);
			

		}
		setChanged();
		notifyObservers();
		getOponente().setChanged();
		getOponente().notifyObservers();
	}

	public void usarMisil(int fila, int columna) {
		//if (inv.getNumMisiles() > 0) {
			// Barco papaBear = getCasillaJugador(fila,columna).getOcupadaPor();
			Casilla pCasilla = getOponente().getCasillaJugador(fila, columna);
			Barco papaBear = getOponente().getBarcoEnCasilla(fila, columna);
			Casilla[] posiciones;
			// Barco papaBear =
			// IA.getIA().getCasillaJugador(fila,columna).getOcupadaPor();
			if (papaBear != null) {
				if (!papaBear.isProtegido()) {
					Map<Casilla, Boolean> mapa = papaBear.getMapa();
					Set<Casilla> deltaFoxtrot = mapa.keySet();
					//posiciones = papaBear.getPosicion();
					for (Casilla potato : deltaFoxtrot){
						potato.setRevelado(true);
					}
					papaBear.hundirBarco(papaBear);
					
				} else {
					papaBear.setProtegido(false);
					
				}
				//inv.restarMisil();
				pCasilla.setRevelado(true);
			} else {

				//inv.restarMisil();
				pCasilla.setRevelado(true);
				
			}
			setChanged();
			notifyObservers();
			getOponente().setChanged();
			getOponente().notifyObservers();
		//}
	}
/*
	public Barco[] getLaArmadaInvencible() {
		return laArmadaInvencible;
	}

	public void setLaArmadaInvencible(Barco[] plaArmadaInvencible) {
		laArmadaInvencible = plaArmadaInvencible;
	}

	public void setPosArmada(Barco pBarco, int pos) {
		laArmadaInvencible[pos] = pBarco;
	}

	public Barco getPosArmada(int pos) {
		return laArmadaInvencible[pos];
	}
*/
	public Set<Barco> getLaArmadaInvencible()
	{
		return laArmadaInvencible;
	}
	public Tablero getTableroJ() {
		return tableroJ;
	}

	public Tablero getTableroIA() {
		return tableroIA;
	}

	public Jugador getOponente() {
		return oponente;
	}

	public void setOponente(Jugador oponente) {
		this.oponente = oponente;
	}

	public boolean comprobarFinPartida()
	{
		int cont = 0;
		for(Barco pBarco: laArmadaInvencible)
		{
			if(pBarco.getEstado() == Status.HUNDIDO)
			{
				cont++;
			}
		}
		if(cont == 10){
			return true;
		}
		else
		{
			return false;
		}
	}
}
