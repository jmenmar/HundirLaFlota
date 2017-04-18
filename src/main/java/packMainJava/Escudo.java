package packMainJava;

public class Escudo extends Recurso{
	
	public Escudo(){
		
	}

	public void cumplirFuncion(Casilla pCasilla) {
		// TODO Auto-generated method stub
		pCasilla.getOcupadaPor().setProtegido(true);
	}
	
}
