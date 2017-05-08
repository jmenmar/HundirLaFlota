package packMainJava;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public abstract class Jugador extends Observable{
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
	private Barco[] laArmadaInvencible = new Barco[10];

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
				addBarcoToArmadaInvencible(pBarco);
			}
			

			decrementarContBarco(tipob);
			setChanged();
			notifyObservers(); // Indicar que es el tablero de barcos
			return true;
		}
      return false;
	}
	public void addBarcoToArmadaInvencible(Barco sanJuanNepomuceno)
	{
		boolean encontrado = false;
		int cont = 0;
		while(cont < 10 && !encontrado)
		{
			if(getLaArmadaInvencible()[cont] != null)encontrado = true;
			cont++;
		}
		
		if(cont > 9)
		{
			getLaArmadaInvencible()[0] = sanJuanNepomuceno;
		}
		else
		{
			getLaArmadaInvencible()[cont] = sanJuanNepomuceno;
		}
		
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
	public Barco getBarcoEnCasilla(int fila,int columna){
		return tableroJ.getCasilla(fila, columna).getOcupadaPor();
	}
	public Casilla getCasillaJugador(int fila, int columna) {
		return tableroJ.getCasilla(fila, columna);
	}
	public Casilla getCasillaIA(int fila, int columna) {
		return tableroIA.getCasilla(fila, columna);
	}
	public void setEscudoEnBarco(int fila,int columna){
		Casilla pCasilla = tableroJ.getCasilla(fila, columna);
		if(pCasilla.getEstado()!=CasillaEstado.AGUA){
			if(pCasilla.getOcupadaPor().getEstado() != Status.HUNDIDO){
				pCasilla.getOcupadaPor().setProtegido(true);
				setChanged();
				notifyObservers();
			}
		}
	}
	public Casilla usarRadar(int fila,int columna){
		int contX = 0;
		int contY = 0;
		Casilla[][] rastreo = new Casilla[4][4];
		ArrayList<Casilla> lista = new ArrayList<Casilla>();
		for(int hotel = fila; hotel < fila + 4; hotel++){
			for(int victor = columna; victor < columna + 4; victor++)
				{
				//rastreo[contX][contY] = Tablero.getTabla[hotel][victor];
				contY++;
				}
			contX++;
		}
		for(contX = 0; contX < 4; contX++)
		{
			for(contY = 0; contY < 4; contY++)
			{
				if(rastreo[contX][contY] != null)
				{
					lista.add(rastreo[contX][contY]);
				}
			}
		}
		Random rnd = new Random();
		int randomNum;
		randomNum = rnd.nextInt((lista.size()) + 1);
		try{
		Casilla resultado = lista.get(randomNum);
		resultado.setDetectada(true);
		setChanged();
		notifyObservers();	
		return resultado;
		}catch(IndexOutOfBoundsException e)
		{
			System.out.println("El radar no ha encontrado ningun barco");
			return null;
		}
		
	}
	
	public void repararBarco(int fila,int columna){
		Barco normandy = getCasillaJugador(fila,columna).getOcupadaPor();
		if(normandy != null && normandy.getEstado()!= Status.HUNDIDO)
		{
			normandy.repararBarco();
			setChanged();
			notifyObservers();
		}
	}

	public Barco[] getLaArmadaInvencible() {
		return laArmadaInvencible;
	}

	public void setLaArmadaInvencible(Barco[] plaArmadaInvencible) {
		laArmadaInvencible = plaArmadaInvencible;
	}
	
	public void setPosArmada(Barco pBarco, int pos)
	{
		laArmadaInvencible[pos] = pBarco;
	}
	public Barco getPosArmada(int pos)
	{
		return laArmadaInvencible[pos];
	}
	
}
