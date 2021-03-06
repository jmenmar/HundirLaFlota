package packMainJava;

import java.util.Observable;


public class Inventario extends Observable{
	
	private static Inventario mInventario = new Inventario(1,1,1,2,2);

	private int numBombas;
	private int numMisiles;
	private int numRadares;
	private int numEscudos;
	private int numReparaciones;
	
	private Inventario(int pNumBombas, int pNumMisiles, int pNumRadares, int pNumEscudos, int pNumReparaciones){

		numBombas = pNumBombas;
		numMisiles = pNumMisiles;
		numRadares = pNumRadares;
		numEscudos = pNumEscudos;
		numReparaciones = pNumReparaciones;
	}
	
	public static Inventario getInventario() {
		return mInventario;
	}
	
	//GETTER AND SETTERS	
	public static Inventario getmInventario() {
		return mInventario;
	}

	public static void setmInventario(Inventario mInventario) {
		Inventario.mInventario = mInventario;
	}	
	
	public int getNumBombas() {
		return numBombas;
	}

	public void setNumBombas(int numBombas) {
		this.numBombas = numBombas;
	}

	public int getNumMisiles() {
		return numMisiles;
	}
	
	public void setNumMisiles(int numMisiles) {
		this.numMisiles = numMisiles;		
	}
	
	public void addMisil(){
		numMisiles++;
		setChanged();
		this.notifyObservers();
	}

	public void restarMisil(){
		numMisiles--;
		setChanged();
		this.notifyObservers();
	}
	
	public int getNumRadares() {
		return numRadares;
	}

	public void setNumRadares(int numRadares) {
		this.numRadares = numRadares;
	}
	
	public void addRadar(){
		numRadares++;
		setChanged();
		this.notifyObservers();
	}
	
	public void restarRadar(){
		numRadares--;
		setChanged();
		this.notifyObservers();
	}

	public int getNumEscudos() {
		return numEscudos;
	}

	public void setNumEscudos(int numEscudos) {
		this.numEscudos = numEscudos;
	}
	
	public void addEscudo(){
		numEscudos++;
		setChanged();
		this.notifyObservers();
	}

	public void restarEscudo(){
		numEscudos--;
		setChanged();
		this.notifyObservers();
	}
	
	public int getNumReparaciones() {
		return numReparaciones;
	}

	public void setNumReparaciones(int numReparaciones) {
		this.numReparaciones = numReparaciones;
	}
	
	public void restarReparacion(){
		numReparaciones--;
		setChanged();
		this.notifyObservers();
	}
	
}
