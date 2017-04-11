package packMainJava;

public class Casilla {
	
	private int x;
	private int y;
	CasillaEstado estado;
	boolean tocaBarco;
	Barco ocupadaPor;
	
	//Constructor
	//n es horizontal y m es Vertical
	public Casilla(int n, int m)
	{
		this.x = n;
		this.y = m;
		this.estado = CasillaEstado.AGUA;
		this.tocaBarco = false;
		this.ocupadaPor = null;
	}
	
	//Get & set (no hay setX o setY porque la posicion de una casilla no debería de variar una
	//vez creada
	public CasillaEstado getEstado() {
		return estado;
	}

	public void setEstado(CasillaEstado estado) {
		this.estado = estado;
	}

	public boolean isTocaBarco() {
		return tocaBarco;
	}

	public void setTocaBarco(boolean tocaBarco) {
		this.tocaBarco = tocaBarco;
	}

	public Barco getOcupadaPor() {
		return ocupadaPor;
	}

	public void setOcupadaPor(Barco ocupadaPor) {
		this.ocupadaPor = ocupadaPor;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
