package packMainJava;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
public class Tablero {
	private static Tablero mTablero;	
	private static Casilla[][] tabla = null;
	
	private Tablero(){
		
		inicializar();
	}
 
	
	public static void inicializar(){
		//inicializamos la estructura
		tabla = new Casilla[10][10];
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
			 Casilla casilla = new Casilla(n, m);
			}
		}		
	}
	
/*	public static void ordenar(){
		int anchoTotal = 300;
		int altoTotal = 300;
		int anchoDeCasilla = anchoTotal / 10;
		int altoDeCasilla = altoTotal /10;
		
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
			  //obtenemos una referencia al boton actual
		      JPanel temp = mCasillas[n][m];
		      //fijar cada casilla a una posicion y tamaño en funcion de su fila y columna
		      temp.setBounds(m * anchoDeCasilla, n * altoDeCasilla, anchoDeCasilla, altoDeCasilla);
		      
			}
		} 
		
	}
	*/
	
	
    
}