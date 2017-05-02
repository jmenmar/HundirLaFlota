package packMainJava;


//Habra que hacerlo singleton
public class Player extends Jugador {

    private static Player mPlayer = new Player();
	private Player(){

	}
    
	public static Player getPlayer(){
		return mPlayer;
	}
}

