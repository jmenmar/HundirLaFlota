package packMainJava;

import java.util.Observer;

import PackSwings.Partida;
import PackSwings.Tienda;

public class NPartida {

	public NPartida(){
		Inventario inve = Inventario.getInventario();
		Partida partida = new Partida();
		Tienda shop = Tienda.getTienda();
		inve.addObserver(partida);
		inve.addObserver(shop);
		shop.setVisible(false);
		partida.setVisible(true);
	}
}
