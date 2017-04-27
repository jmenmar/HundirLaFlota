package packMainJava;

public class Casilla {
	
	private int x;
	private int y;
	private CasillaEstado estado;
	private boolean tocaBarco;
	private Barco ocupadaPor;
	
	//Constructor
	//n es horizontal y m es Vertical
	public Casilla(int n, int m)
	{
		x = n;
		y = m;
		estado = CasillaEstado.AGUA;
		tocaBarco = false;
		ocupadaPor = null;
	}
	
	//Get & set (no hay setX o setY porque la posicion de una casilla no deber�a de variar una
	//vez creada
	public CasillaEstado getEstado() {
		return estado;
	}

	public void setEstado(CasillaEstado estado) {
		estado = estado;
	}

	public boolean isTocaBarco() {
		return tocaBarco;
	}

	public void setTocaBarco(boolean tocaBarco) {
		tocaBarco = tocaBarco;
	}

	public Barco getOcupadaPor() {
		return ocupadaPor;
	}

	public void setOcupadaPor(Barco ocupadaPor) {
		ocupadaPor = ocupadaPor;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
