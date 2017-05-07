package packMainJava;

public class Bomba extends Recurso {

	private static Bomba gbu10 = new Bomba();
	
	public void act(int x, int y, Tablero tab)
	{
		cumplirFuncion(tab.getCasilla(x, y));
	}
	
	public CasillaEstado cumplirFuncion(Casilla pCasilla)
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
			return CasillaEstado.OCUPADA;
		} else 
		{
			return CasillaEstado.AGUA;
		}
		
	}
	
	public static Bomba getBomba()
	{
		return gbu10;
	}
}
