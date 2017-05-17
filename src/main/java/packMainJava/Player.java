package packMainJava;

import java.util.Set;


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
		if(inv.getNumMisiles() > 0){
		super.usarMisil(fila, columna);
		IA.getIA().act();
		}
	}
	@Override
	public void repararBarco(int fila, int columna)
	{
		if(inv.getNumReparaciones() > 0){
		super.repararBarco(fila, columna);
		inv.restarReparacion();
		Set<Casilla> aReparar = getBarcoEnCasilla(fila, columna).getMapa().keySet();
		for(Casilla reparacion:aReparar)
		{
			IA.getIA().getMarca().put(reparacion, false);
		}
		//IA.getIA().getMarca().put(getTableroJ().getCasilla(fila, columna), false);
		}
	}
	@Override
	public void setEscudoEnBarco(int fila, int columna)
	{
		if(inv.getNumEscudos() > 0){
		super.setEscudoEnBarco(fila, columna);
		inv.restarEscudo();
		}
	}
	@Override
	public void usarRadar(int fila, int columna, Jugador pJugador)
	{
		if(inv.getNumRadares()>0)
		{
			super.usarRadar(fila, columna, pJugador);
			inv.restarRadar();
		}
	}
	
}

