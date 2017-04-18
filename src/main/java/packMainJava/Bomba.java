package packMainJava;

public class Bomba extends Recurso {

	public Bomba(){
		
	}
	
	public void act() {
		// TODO Auto-generated method stub
		cumplirFuncion(encontrarObjetivo());
	}
	
	public void cumplirFuncion(Casilla pCasilla)
	{
		Barco tangoZulu = pCasilla.getOcupadaPor();
		if(tangoZulu != null)
		{
			if(!tangoZulu.isProtegido())
			{
				tangoZulu.setImpacto(pCasilla);
			}
			else
			{
				tangoZulu.setProtegido(false);
			}
		}
	}
	
}
