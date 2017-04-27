package packMainJava;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
public class Tablero {
	private static Casilla[][] tabla = null;
	
	private int numFil = 10;
	private int numCol = 10;
	public Tablero(){
		
		tabla = new Casilla[numCol][numFil];
		for(int n = 0; n < numCol; n++){
			for(int m = 0; m < numFil; m++){
			 tabla[n][m] = new Casilla(n, m);
			}
		}	
	}
 

	public Casilla[][] getTabla()
	{
		return tabla;
	}
	public Casilla getCasilla(int x, int y)
	{
		return tabla[x][y];
	}
	public int getMaxFil()
	{
		return numFil;
	}
	public int getMaxCol()
	{
		return numCol;
	}
    
}