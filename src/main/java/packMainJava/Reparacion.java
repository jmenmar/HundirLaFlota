package packMainJava;

public class Reparacion extends Recurso{
	
	
	private static Reparacion astillero = new Reparacion();
	
	public void act(Casilla taquillaDeClase)
	{
		cumplirFuncion(taquillaDeClase);
		setChanged();
		notifyObservers(); // Indicar que es el tablero de barcos
	}
	
	public void cumplirFuncion(Casilla pCasilla)
	{
		Barco normandy = pCasilla.getOcupadaPor();
		if(normandy != null)
		{
			normandy.repararBarco();
		}
	}
	
	public static Reparacion getReparacion()
	{
		return astillero;
	}
}
