package packMainJava;
import java.util.Random;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;
import static java.lang.System.out;
import static java.lang.System.err;
import PackSwings.Partida;
public class IA extends Jugador{
	
	private int numMisiles = 5;
	private int numEscudos = 2;
	private int numReparaciones = 1;
	private int numRadar = 1;
	private int dinero = 1000;
	private boolean magisterioEsUnGradoMedio;
	private final int precioMisil = 100;
	private final int precioEscudo = 200;
	private final int precioRadar = 250;
	private Casilla detectado;
	private static IA mIA = new IA();
	
	
	private IA()
	{
		
	}
	
	public static IA getIA()
	{
		return mIA;
	}
	
	public void ponerBarco(TipoDeBarco pBarco){
		int fila,columna,hor;
		Random rnd = new Random();
		//Sacar posicion al azar hasta que se pueda colocar dicho barco
		do{
				fila= rnd.nextInt(10);
				columna= rnd.nextInt(10);
				hor= rnd.nextInt(2);
		}while(!addBarco(pBarco,fila,columna,hor == 1));	
	}
	
	public static IA getmIA() {
		return mIA;
	}
	public static void setmIA(IA mIA) {
		IA.mIA = mIA;
	}
	
	public void act()
	{
		Random random = new Random();
		detectado = null;
		comprarRecursos(random.nextInt(100));
		if (numReparaciones > 0)
		{
			repararBarco(random.nextInt(100));
		}
		if (numEscudos > 0)
		{
			usarEscudo(random.nextInt(100));
		}
		if (numRadar > 0)
		{
			usarRadar(random.nextInt(100), Player.getPlayer().getTableroJ());
		}
		disparar(random.nextInt(100), Player.getPlayer().getTableroJ());
		//Aqui se cambiaria el turno ya
		
	}
	private void comprarRecursos(int probabilidad)
	{
		magisterioEsUnGradoMedio = true;
		while(magisterioEsUnGradoMedio){
			if(probabilidad < 1)
			{
				Random quePodemosComprar = new Random();
				int controlador = quePodemosComprar.nextInt(3);
				switch (controlador)
				{
					case 0:
						if(dinero >= precioMisil)comprarMisil();
						break;
					case 1:
						if(dinero >= precioEscudo)comprarEscudo();
					
					case 2:
						if(dinero >= precioRadar)comprarRadar();
				}
				Random quePodemosComprar2 = new Random();
				int nuevaProb = quePodemosComprar2.nextInt(100);
				probabilidad = nuevaProb;
			}
			else
			{
				magisterioEsUnGradoMedio = false;
			}
		}
	}
	private void comprarMisil()
	{
		numMisiles++;
		dinero -= precioMisil;
	}
	private void comprarEscudo()
	{
		numEscudos++;
		dinero -= precioEscudo;
	}
	private void comprarRadar()
	{
		numRadar++;
		dinero -= precioRadar;
	}
	private void repararBarco(int probabilidad)
	{
		if(probabilidad % 5 == 0)
		{
			ArrayList<Barco> listaTocados = new ArrayList<Barco>();
			for(Barco santisimaTrinidad:getLaArmadaInvencible())
			{
				if(santisimaTrinidad == null){System.out.println("Nulo");}else{
				System.out.println(santisimaTrinidad.toString());}
				if(santisimaTrinidad.getEstado() == Status.TOCADO)
				{
					listaTocados.add(santisimaTrinidad);
				}
			}
			if(listaTocados.size() > 0)
			{
					Random alphaBravoDelta666 = new Random();
					int posibilidades = alphaBravoDelta666.nextInt(listaTocados.size());
					listaTocados.get(posibilidades).repararBarco();
			}
		}
	}
	private void usarEscudo(int probabilidad)
	{
		if(probabilidad % 5 == 0)
		{
			ArrayList<Barco> listaNoGluGluGlu = new ArrayList<Barco>();
			for(Barco JuanCarlosPrimero:getLaArmadaInvencible())
			{
				if(JuanCarlosPrimero.getEstado() != Status.HUNDIDO)
				{
					listaNoGluGluGlu.add(JuanCarlosPrimero);
				}
			}
			if(listaNoGluGluGlu.size() > 0)
			{
				Random livingInTheVatican = new Random();
				int quePasaSiLoPongoAqui = livingInTheVatican.nextInt(listaNoGluGluGlu.size());
				listaNoGluGluGlu.get(quePasaSiLoPongoAqui).setProtegido(true);
				numEscudos--;
			}
			
		}
	}
	private void usarRadar(int probabilidad, Tablero tab)
	{
		if(probabilidad < 20)
		{
			int numCol = tab.getMaxCol();
			int numFil = tab.getMaxFil();
			int randomX;
			int randomY;
			Random bigBird = new Random();
			randomX = bigBird.nextInt((numCol - 4));
			randomY = bigBird.nextInt((numFil - 4));
			usarRadar(randomX,randomY, Player.getPlayer());
			numRadar--;
		}
	}
	private void disparar(int probabilidad, Tablero tab)
	{
		Random kBoom = new Random();
		int bombaOMisil = kBoom.nextInt();
		Casilla objetivo;
		if(bombaOMisil < 25 && numMisiles > 0)
		{
			if(detectado != null)
			{
				objetivo = detectado;
			}
			else
			{
				int numFil = tab.getMaxFil();
				int numCol = tab.getMaxCol();
				int randomX;
				int randomY;
				Random patriot = new Random();
				randomX = patriot.nextInt(numCol);
				randomY = patriot.nextInt(numFil);
				objetivo = tab.getCasilla(randomX, randomY);				
			}
			usarMisil(objetivo.getX(),objetivo.getY());
			System.out.println("Disparo realizado: \nX =" +objetivo.getX() + "\nY =: " + objetivo.getY());
		}
		else
		{
			if(detectado != null)
			{
				objetivo = detectado;
			}
			else
			{
				int numFil = tab.getMaxFil();
				int numCol = tab.getMaxCol();
				int randomX;
				int randomY;
				Random freedom = new Random();
				randomX = freedom.nextInt(numCol);
				randomY = freedom.nextInt(numFil);
				objetivo = tab.getCasilla(randomX, randomY);				
			}
			usarBomba(objetivo.getX(),objetivo.getY());
		}
	}
	
	
	public void colocarBarcosPropios()
	{
		int numPor = 1;
		int numSub = 2;
		int numDes = 3;
		int numFra = 4;
		
		while(numPor > 0)
		{
			ponerBarco(TipoDeBarco.PORTAAVIONES);
			numPor--;
		}
		
		while(numSub > 0)
		{
			ponerBarco(TipoDeBarco.SUBMARINO);
			numSub--;
		}
		while(numDes > 0)
		{
			ponerBarco(TipoDeBarco.DESTRUCTOR);	
			numDes--;
		}
		while(numFra > 0)
		{
			ponerBarco(TipoDeBarco.FRAGATA);
			numFra--;
		}
	}
	
}
