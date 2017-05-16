package packMainJava;


//Habra que hacerlo singleton
public class Player extends Jugador {

    private static Player mPlayer = new Player();
	private Player(){

	}
    
	public static Player getPlayer(){
		return mPlayer;
	}
	@Override
	public void usarBomba(int fila, int columna) {
		// TODO Auto-generated method stub
		super.usarBomba(fila, columna);
		IA.getIA().act();
	}
	public void usarMisil(int fila, int columna) {
		// TODO Auto-generated method stub
		super.usarMisil(fila, columna);
		IA.getIA().act();
	}
	
}

