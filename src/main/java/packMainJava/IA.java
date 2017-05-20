package packMainJava;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;


public class IA extends Jugador {
	
	private int numMisiles = 5;
	private int numEscudos = 5;
	private int numReparaciones = 5;
	private int numRadar = 1;
	private int dinero = 1000;
	private boolean magisterioEsUnGradoMedio;
	private final int precioMisil = 100;
	private final int precioEscudo = 200;
	private final int precioRadar = 250;
	private Casilla detectado;
	private Casilla repetirDisparo;
	private static IA mIA = new IA();
	private HashMap<Casilla, Boolean> marca = new HashMap<Casilla, Boolean>();
	
	
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
	
	public void act()
	{
		//System.out.println("\nRep: " + numReparaciones + " Shield = " + numEscudos + " Rad = " + numRadar + " Misiles = " + numMisiles + "(" + dinero + ")");
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
		//System.out.println("Rep: " + numReparaciones + " Shield = " + numEscudos + " Rad = " + numRadar + " Misiles = " + numMisiles + "(" + dinero + ")\n");
		//Aqui se cambiaria el turno ya
		
	}
	private void comprarRecursos(int probabilidad)
	{
		magisterioEsUnGradoMedio = true;
		while(magisterioEsUnGradoMedio){
			if(probabilidad < 30)
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
		//System.out.println("Comprado misil");
	}
	private void comprarEscudo()
	{
		numEscudos++;
		dinero -= precioEscudo;
		//System.out.println("Comprado escudo");
	}
	private void comprarRadar()
	{
		numRadar++;
		dinero -= precioRadar;
		//System.out.println("Comprado radar");
	}
	private void repararBarco(int probabilidad)
	{
		if(probabilidad % 5 == 0)
		{
			ArrayList<Barco> listaTocados = new ArrayList<Barco>();
			for(Barco santisimaTrinidad:getLaArmadaInvencible())
			{
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
					listaTocados.get(posibilidades).setEstado(Status.INTACTO);
					//FALTA NOTIFICAR Y SETCHANGED
					super.setChanged();
					super.notifyObservers();
					numReparaciones--;
					//AVISO uso de escudos de la IA, para comprobar que todo funciona correctamente y no es error de setImpacto
					//System.out.println("Barco Reparado");
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
				//AVISO uso de escudos de la IA, para comprobar que todo funciona correctamente y no es error de setImpacto
				//System.out.println("Usado escudo");
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
		Barco pBarco;
		if(repetirDisparo != null)
		{
			objetivo = repetirDisparo;
			repetirDisparo = null;
		}
		else
		{
			if(detectado != null && marca.containsKey(detectado) && marca.get(detectado))
			{
				objetivo = detectado;
			}else{
			int numFil = tab.getMaxFil();
			int numCol = tab.getMaxCol();
			int randomX;
			int randomY;
			Random freedom = new Random();
			do{
			randomX = freedom.nextInt(numCol);
			randomY = freedom.nextInt(numFil);
			objetivo = tab.getCasilla(randomX, randomY);
			}while(marca.containsKey(objetivo) && marca.get(objetivo));
			}
		}
		pBarco = objetivo.getOcupadaPor();
		if(pBarco != null && pBarco.isProtegido())
		{
			repetirDisparo = objetivo;
		}else{
			marca.put(objetivo, true);
		}
		if(bombaOMisil < 25 && numMisiles > 0)
		{
			usarMisil(objetivo.getX(),objetivo.getY());
			numMisiles--;
		}
		else
		{
			usarBomba(objetivo.getX(),objetivo.getY());
		}
		if(pBarco != null && pBarco.getEstado() == Status.HUNDIDO)
		{
			Map<Casilla, Boolean> mikePapa = pBarco.getMapa();
			Set<Casilla> indiaGolf = mikePapa.keySet();
			int xray, xuMin = 100, xiangMax = -1, xiBuffer;
			int yankee, yangMin = 100, yanyuMax = -1, yongBuffer;
			for(Casilla encajonado:indiaGolf)
			{
				xray = encajonado.getX();
				yankee = encajonado.getY();
				xiBuffer = xray - 1;
				xuMin = Math.min(xiBuffer, xuMin);
				xiBuffer = xray + 1;
				xiangMax = Math.max(xiBuffer, xiangMax);
				yongBuffer = yankee -1;
				yangMin = Math.min(yangMin, yongBuffer);
				yongBuffer = yankee + 1;
				yanyuMax = Math.max(yanyuMax, yongBuffer);
			}
			for(int yinYang = yangMin; yinYang <= yanyuMax; yinYang++)
			{
				for(int xuei = xuMin; xuei <= xiangMax; xuei++)
				{
					if(yinYang >= 0 && yinYang <10 && xuei >= 0 && xuei < 10){
					marca.put(tab.getCasilla(xuei, yinYang), true);
					}
				}
			}
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

	public HashMap<Casilla, Boolean> getMarca() {
		return marca;
	}

	public void inicializarMarca() {
		// TODO Auto-generated method stub
		for(int kilo = 0; kilo < 10; kilo++){
			for(int lima = 0; lima < 10; lima++){
				marca.put(IA.getIA().getCasillaJugador(kilo, lima), false);
			}
		}
	}
	
}
