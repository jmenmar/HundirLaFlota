package packMainJava;

public class Escudo extends Recurso{
	
	private final static Escudo shield = new Escudo();

	public void cumplirFuncion(Casilla pCasilla) {
		// TODO Auto-generated method stub
		pCasilla.getOcupadaPor().setProtegido(true);
	}
	
	public static Escudo getShield()
	{
		return shield;
	}
}
