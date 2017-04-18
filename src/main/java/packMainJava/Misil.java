package packMainJava;

public class Misil extends Recurso {
	
	public Misil(){
		
	}

	public void act()
	{
		cumplirFuncion(encontrarObjetivo());
	}

	public void cumplirFuncion(Casilla pCasilla) {
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
		}
		
	}

	
}
