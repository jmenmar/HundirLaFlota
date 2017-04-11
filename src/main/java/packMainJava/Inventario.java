package packMainJava;

public class Inventario {
	
	private static Inventario mInventario = new Inventario(1,1,1,1);

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
	}
	
	public void addMisil(){
		numMisiles++;
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
	
}
