package packMainJava;

public class Casilla {
	
	private int x;
	private int y;
	private CasillaEstado estado;
	private boolean tocaBarco;
	private Barco ocupadaPor;
	private boolean detectada;
	private  boolean revelado;
	
	//Constructor
	//n es horizontal y m es Vertical
	public Casilla(int n, int m, boolean revelar)
	{
		x = n;
		y = m;
		estado = CasillaEstado.AGUA;
		tocaBarco = false;
		ocupadaPor = null;
		detectada = false;
		setRevelado(revelar);
	}
	
	//Get & set (no hay setX o setY porque la posicion de una casilla no deber�a de variar una
	//vez creada
	public CasillaEstado getEstado() {
		return estado;
	}

	public void setEstado(CasillaEstado estadoNuevo) {
		estado = estadoNuevo;
	}

	public boolean isTocaBarco() {
		return tocaBarco;
	}

	public void setTocaBarco(boolean sienteElRoce) {
		tocaBarco = sienteElRoce;
	}

	public Barco getOcupadaPor() {
		return ocupadaPor;
	}

	public void setOcupadaPor(Barco hayOkupasPorAqui) {
		ocupadaPor = hayOkupasPorAqui;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDetectada() {
		return detectada;
	}

	public void setDetectada(boolean detectada) {
		this.detectada = detectada;
	}

	public boolean isRevelado() {
		return revelado;
	}

	public void setRevelado(boolean revelado) {
		this.revelado = revelado;
	}
	
	
}
