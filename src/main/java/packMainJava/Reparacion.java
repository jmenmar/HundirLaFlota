package packMainJava;

public class Reparacion extends Recurso{
	
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
}
