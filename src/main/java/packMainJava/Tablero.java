package packMainJava;
import java.util.ArrayList;
public class Tablero {
	private static Tablero mTablero;
	
	private Tablero(){}
 
	int tableroUsuario[][]= new int[10][10];
	int tableroIA[][]= new int[10][10];
	
	public void IniciarTablero(){
		for(int n=0;n<10;n++){
			for(int m=0;m<10;m++){
				tableroUsuario[n][m]=0;
				tableroIA[n][m]=0;
			}
		}
	}
}