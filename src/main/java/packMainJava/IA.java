package packMainJava;
public class IA {
 
	public IA(){	
	}
	
	//Compruebas que la fila y la columna estan dentro del tablero
	public static boolean celdaEstaEnTablero(int fila,int columna){
		if(fila<0)	return false;
		if(columna<0)	return false;
		if(fila>=10)	return false;
		if(columna>=10)	return false;
		
		return true;
	}
	
	public static void ponerBarco(Tablero tab, int tam){
		int fila,columna,hor;
		int noColgar=100;
		//Sacar posicion al azar hasta que se pueda colocar dicho barco
		do{
				fila= (int)(Math.random()*10);
				columna= (int)(Math.random()*10);
				hor= (int)(Math.random()*2);
				noColgar--;
		}while(!puedePonerBarco(tab,tam,fila,columna,hor) && noColgar>0);
		
		int dFila=0,dColumna=0;
		if(hor==1) dFila=1;
		else dColumna=1;
				
		if(dFila==1){
			for(int fila2=fila;fila2<fila+tam;fila2++){
					tab.getCasilla(fila2, columna).estado = CasillaEstado.OCUPADA;
			}
			
		}else if(dColumna==1){
			for(int columna2=columna;columna2<columna+tam;columna2++){
					tab.getCasilla(fila, columna2).estado = CasillaEstado.OCUPADA;
				
			}
		}
		
	}

	public static boolean puedePonerBarco(Tablero tab,int tam,int fila, int columna, int hor){
		int dFila=0,dColumna=0;
		if(hor==1){
			dFila=1;
		}else{
			dColumna=1;
		}
		if(dFila==1){
			for(int fila2=fila;fila2<=fila+tam;fila2++){
				if(!celdaEstaEnTablero(fila2,columna)){
					return false;
				}
				
			}
			
		}else if(dColumna==1){
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
					if(tab.getCasilla(fila2, columna2).estado!=CasillaEstado.AGUA){
					  return false;
					}
					
				}
			}
		}
		return true;
	
	
	}
	
	
}
