package packMainJava;

public class Reparacion extends Recurso{
	
	private static final Reparacion astillero = new Reparacion();
	
	public void act()
	{
		cumplirFuncion(encontrarObjetivo());
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
