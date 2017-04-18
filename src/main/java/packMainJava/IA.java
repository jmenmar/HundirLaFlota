package packMainJava;
public class IA {
 
	//para que no de error
	public IA(){	
		
		//poner 5 barcos de diferente tamaño de 1 a 5, luego ya pondremos mas variedad como en el enunciado
/*		for(int tam=1;tam<=5;tam++){		
	    	ponerBarco(tableroIA, tam);
	}
	*/
	}
	
	public boolean celdaEstaEnTablero(int fila,int columna){
		if(fila<0)	return false;
		if(columna<0)	return false;
		if(fila>=10)	return false;
		if(columna>=10)	return false;
		
		return true;
	}
	
	public void ponerBarco(int tab[][], int tam){
		int fila,columna,hor;
		do{
				fila= (int)(Math.random()*10);
				columna= (int)(Math.random()*10);
				hor= (int)(Math.random()*2);
		}while(puedePonerBarco(tab,tam,fila,columna,hor));
		int dFila=0,dColumna=0;
		if(hor==1) dFila=1;
		else dColumna=1;
		
		
		if(dFila==1){
			for(int fila2=fila;fila2<fila+tam;fila2++){
				if(celdaEstaEnTablero(fila2,columna)){
					tab[fila2][columna]=tam;
				}
				
			}
			
		}else if(dColumna==1){
			for(int columna2=columna;columna2<columna+tam;columna2++){
				if(celdaEstaEnTablero(fila,columna2)){
					tab[fila][columna2]=tam;
				}
				
			}
		}
		
	}

	public boolean puedePonerBarco(int tab[][],int tam,int fila, int columna, int hor){
		int dFila=0,dColumna=0;
		if(hor==1){
			dFila=1;
		}else{
			dColumna=1;
		}
		if(dFila==1){
			for(int fila2=fila;fila2<fila+tam;fila2++){
				if(celdaEstaEnTablero(fila2,columna)){
					return false;
				}
				
			}
			
		}else if(dColumna==1){
			for(int columna2=columna;columna2<columna+tam;columna2++){
				if(celdaEstaEnTablero(fila,columna2)){
					return false;
				}
				
			}
		}	
			
		
		for(int fila2=fila-1;fila2<fila+1+dFila*tam;fila2++){
			for(int columna2=columna-1;columna2<columna+1+dColumna*tam;columna2++){
				if(celdaEstaEnTablero(fila2,columna2)){
					if(tab[fila2][columna2]!=0){
					  return false;
					}
					
				}
			}
		}
		return true;
	
	
	}
	
	
}
