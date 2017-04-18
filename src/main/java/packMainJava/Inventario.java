package packMainJava;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Inventario extends Observable{
	
	private static Inventario mInventario = new Inventario(1,1,1,1);

	private List <Observer> observerList;
	private int numBombas;
	private int numMisiles;
	private int numRadares;
	private int numEscudos;
	
	private Inventario(int pNumBombas, int pNumMisiles, int pNumRadares, int pNumEscudos){

		numBombas = pNumBombas;
		numMisiles = pNumMisiles;
		numRadares = pNumRadares;
		numEscudos = pNumEscudos;
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
		this.notifyObservers();
	}
	
	public void addMisil(){
		numMisiles++;
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
	}

	public int getNumEscudos() {
		return numEscudos;
	}

	public void setNumEscudos(int numEscudos) {
		this.numEscudos = numEscudos;
	}
	
	public void addEscudo(){
		numEscudos++;
	}
	
	@Override
	public void addObserver(Observer observer) {
		this.observerList.add(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : this.observerList)
			observer.update(this, "Act");
	}

	
	
}
