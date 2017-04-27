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
	private final int precioMisil = 100;
	private final int precioEscudo = 200;
	private final int precioRadar = 250;
	private Casilla detectado;
	private Barco[] laArmadaInvencible = new Barco[10];
	private static IA mIA = new IA();
	private IA()
	{
		
	}
	public static IA getIA()
	{
		return mIA;
	}
	
	//Compruebas que la fila y la columna estan dentro del tablero
	public static boolean celdaEstaEnTablero(int fila,int columna){
		if(fila<0)	return false;
		if(columna<0)	return false;
		if(fila>=10)	return false;
		if(columna>=10)	return false;
		
		return true;
	}
	
	public void ponerBarco(Tablero tab, Barco pBarco){
		int fila,columna,hor, tam;
		tam = pBarco.getModelo().getLongitud();
		Random rnd = new Random();
		//Sacar posicion al azar hasta que se pueda colocar dicho barco
		do{
				fila= rnd.nextInt(10);
				columna= rnd.nextInt(10);
				hor= rnd.nextInt(2);
		}while(!puedePonerBarco(tab,tam,fila,columna,hor == 1));
		
		int dFila=0,dColumna=0;
		if(hor==1) dFila=1;
		else dColumna=1;
				
		//Añadir limites
		
		int filaA, filaB, colA, colB, minFila = 0, minCol = 0, mi;
		if(dFila==1){
			for(int fila2=fila;fila2<fila+tam;fila2++){
					tab.getCasilla(fila2, columna).setEstado(CasillaEstado.OCUPADA);
					tab.getCasilla(fila2, columna).setOcupadaPor(pBarco);
			}
			
		}else if(dColumna==1){
			for(int columna2=columna;columna2<columna+tam;columna2++){
					tab.getCasilla(fila, columna2).setEstado(CasillaEstado.OCUPADA);
					tab.getCasilla(fila, columna2).setOcupadaPor(pBarco);
			}
		}
		
	}
	
	
	
	public int getNumEscudos() {
		return numEscudos;
	}
	public void setNumEscudos(int numEscudos) {
		this.numEscudos = numEscudos;
	}
	public int getNumReparaciones() {
		return numReparaciones;
	}
	public void setNumReparaciones(int numReparaciones) {
		this.numReparaciones = numReparaciones;
	}
	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	public static IA getmIA() {
		return mIA;
	}
	public static void setmIA(IA mIA) {
		IA.mIA = mIA;
	}
	@Override
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
	
	public void act()
	{
		Random random = new Random();
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
			usarRadar(random.nextInt(100), PackSwings.Partida.getTableroJugador());
		}
		disparar(random.nextInt(100), PackSwings.Partida.getTableroJugador());
		//Aqui se cambiaria el turno ya
		
	}
	private void comprarRecursos(int probabilidad)
	{
		if(probabilidad < 10)
		{
			Random quePodemosComprar = new Random();
			int controlador = quePodemosComprar.nextInt(3);
			//prueba adelante
			/*	
				Method maquillaje;
				Class[] dineroArray = new Class[1];
				dineroArray[0] = Integer.class;
				switch (controlador)
				{
					case 0: try
							{
								maquillaje = this.getClass().getDeclaredMethod("comprarMisil", dineroArray);
								maquillaje.invoke(null, precioMisil);
							} catch (NoSuchMethodException x) {
							    x.printStackTrace();
							} catch (IllegalAccessException x) {
							    x.printStackTrace();
							} catch (InvocationTargetException x) {
							    x.printStackTrace();
							{
								System.out.println(x.toString());
							}
							}
							break;
					case 1: try
							{
								maquillaje = this.getClass().getDeclaredMethod("comprarMisil", dineroArray);
							} catch(NoSuchMethodException e)
							{
								System.out.println(e.toString());
							}
							break;
					case 2: try
							{
								maquillaje = this.getClass().getDeclaredMethod("comprarMisil", dineroArray);
							} catch(NoSuchMethodException e)
							{
								System.out.println(e.toString());
							}
							break;
				}
			*/
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
			for(Barco santisimaTrinidad:laArmadaInvencible)
			{
				if(santisimaTrinidad.getEstado() == Status.TOCADO)
				{
					listaTocados.add(santisimaTrinidad);
				}
			}
			Random alphaBravoDelta666 = new Random();
			int posibilidades = alphaBravoDelta666.nextInt(listaTocados.size()-1);
			listaTocados.get(posibilidades).repararBarco();
		}
	}
	private void usarEscudo(int probabilidad)
	{
		if(probabilidad % 5 == 0)
		{
			ArrayList<Barco> listaNoGluGluGlu = new ArrayList<Barco>();
			for(Barco JuanCarlosPrimero:laArmadaInvencible)
			{
				if(JuanCarlosPrimero.getEstado() != Status.HUNDIDO)
				{
					listaNoGluGluGlu.add(JuanCarlosPrimero);
				}
			}
			Random livingInTheVatican = new Random();
			int quePasaSiLoPongoAqui = livingInTheVatican.nextInt(listaNoGluGluGlu.size()-1);
			listaNoGluGluGlu.get(quePasaSiLoPongoAqui).setProtegido(true);
		}
	}
	private Casilla usarRadar(int probabilidad, Tablero tab)
	{
		if(probabilidad < 20)
		{
			Random areaDeRastreo = new Random();
			int numCol = tab.getMaxCol();
			int numFil = tab.getMaxFil();
			int randomX;
			int randomY;
			Random bigBird = new Random();
			randomX = bigBird.nextInt((numCol - 4) - 1);
			randomY = bigBird.nextInt((numFil - 4) - 1);
			Casilla ostrasQueAquiHayUno = tab.getCasilla(randomX, randomY);
			return Radar.getRadar().cumplirFuncion(ostrasQueAquiHayUno);
		}
		else
		{
			return null;
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
				randomX = patriot.nextInt((numCol - 4) - 1);
				randomY = patriot.nextInt((numFil - 4) - 1);
				objetivo = tab.getCasilla(randomX, randomY);				
			}
			Misil.getMisil().cumplirFuncion(objetivo);
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
				randomX = freedom.nextInt((numCol - 4) - 1);
				randomY = freedom.nextInt((numFil - 4) - 1);
				objetivo = tab.getCasilla(randomX, randomY);				
			}
			Bomba.getBomba().cumplirFuncion(objetivo);
		}
	}
	
}
