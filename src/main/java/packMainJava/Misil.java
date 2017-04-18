package packMainJava;

public class Misil extends Recurso {
	
	private static final Misil sidewinder = new Misil();

	public void act()
	{
		cumplirFuncion(encontrarObjetivo());
	}

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

	public Misil getMisil()
	{
		return sidewinder;
	}
	
}
