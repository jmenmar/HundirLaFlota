package packMainJava;

public class Misil extends Recurso {
	
	
	private static Misil sidewinder = new Misil();

	public CasillaEstado cumplirFuncion(Casilla pCasilla) {
		// TODO Auto-generated method stub
		Barco papaBear = pCasilla.getOcupadaPor();
		if(papaBear != null)
		{
			if(!papaBear.isProtegido())
			{
				papaBear.hundirBarco();
			}
			else
			{
				papaBear.setProtegido(false);
			}
			return CasillaEstado.OCUPADA;
		}
		else
		{
			return CasillaEstado.AGUA;
		}
	}

	public static Misil getMisil()
	{
		return sidewinder;
	}
	
}
