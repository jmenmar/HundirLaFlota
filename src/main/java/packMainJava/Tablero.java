package packMainJava;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
public class Tablero {
	private static Casilla[][] tabla = null;
	
	public Tablero(){
		
		tabla = new Casilla[10][10];
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
			 Casilla casilla = new Casilla(n, m);
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
    
}