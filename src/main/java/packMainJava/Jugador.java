package packMainJava;

public abstract class Jugador {

	public static Tablero tableroIA = new Tablero();
	public static Tablero tableroJ = new Tablero();
		
	public void ponerBarcos()
	{
		
	}
	abstract void ponerBarco(Tablero tab, Barco pBarco);
	abstract void act();
	//Compruebas que la fila y la columna estan dentro del tablero
	public static boolean celdaEstaEnTablero(int fila,int columna){
		if(fila<0)	return false;
		if(columna<0)	return false;
		if(fila>=10)	return false;
		if(columna>=10)	return false;
		
		return true;
	}
	
	boolean puedePonerBarco(Tablero tab, int tam, int fila, int columna,
			boolean hor) {
		int dFila=0,dColumna=0;
		if(hor){
			for(int fila2=fila;fila2<=fila+tam;fila2++){
				if(!celdaEstaEnTablero(fila2,columna)){
					return false;
				}
				
			}
			
		}else{
			for(int columna2=columna;columna2<=columna+tam;columna2++){
				if(!celdaEstaEnTablero(fila,columna2)){
					return false;
				}
				
			}
		}	
			
		//Comprueba si hay un barco
		for(int fila2=fila-1;fila2<=fila+1+dFila*tam;fila2++){
			for(int columna2=columna-1;columna2<=columna+1+dColumna*tam;columna2++){
				if(celdaEstaEnTablero(fila2,columna2)){
					if(tab.getCasilla(fila2, columna2).getEstado() != CasillaEstado.AGUA){
					  return false;
					}
					
				}
			}
		}
		return true;
	
	
	}
	
}
