package packMainJava;
import java.util.Random;
import java.util.ArrayList;
public class Radar extends Recurso{
	
	private final static Radar awacs = new Radar();
	public void act()
	{
		cumplirFuncion(encontrarObjetivo());
	}
	public Casilla cumplirFuncion(Casilla pCasilla) {
		// TODO Auto-generated method stub
		int posX = pCasilla.getX();
		int posY = pCasilla.getY();
		int contX = 0;
		int contY = 0;
		Casilla[][] rastreo = new Casilla[4][4];
		ArrayList<Casilla> lista = new ArrayList<Casilla>();
		for(int hotel = posX; hotel < posX + 4; hotel++){
			for(int victor = posY; victor < posY + 4; victor++)
				{
				//rastreo[contX][contY] = Tablero.getTabla[hotel][victor];
				contY++;
				}
			contX++;
		}
		for(contX = 0; contX < 4; contX++)
		{
			for(contY = 0; contY < 4; contY++)
			{
				if(rastreo[contX][contY] != null)
				{
					lista.add(rastreo[contX][contY]);
				}
			}
		}
		Random rnd = new Random();
		int randomNum;
		randomNum = rnd.nextInt((lista.size()) + 1);
		Casilla resultado = lista.get(randomNum);
		return resultado;
	}
	
	public Radar getRadar()
	{
		return awacs;
	}
}
