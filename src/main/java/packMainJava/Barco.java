package packMainJava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
public class Barco extends Observable{
	
	private Status estado;
	public TipoDeBarco modelo;
	private boolean protegido;
	private boolean horizontal;
	private Casilla proa;
	//private Casilla[] posicion;
	//private Casilla[] impactos;
	private Map<Casilla, Boolean> impactos = new HashMap<Casilla, Boolean>();
	
	//Constructor

	public Barco(TipoDeBarco pModelo)
	{
		this.modelo = pModelo;
		this.estado = Status.INTACTO;
		this.protegido = false;
		//this.posicion = new Casilla[this.modelo.getLongitud()];
		//this.impactos = new Casilla[this.modelo.getLongitud()];
		/*for(int i = 0; i < impactos.length; i++)
		{
			impactos[i] = null;
		}*/
	}
	
		//Metodos
		public void setImpacto(Casilla impacto) {
			/*int longitud = this.getModelo().getLongitud();
			boolean encontrado = false;
			int i = 0;
			int j = 0;
			while(i < longitud && encontrado)
			{
				while(j < longitud && encontrado)
				{
					if(posicion[i] == impactos[j])
					{
						encontrado = true;
						j++;
					}
					i++;
				}
			}
			if(!encontrado)
			{
				boolean finder = false;
				int foo = 0;
				while(foo < longitud && !finder)
				{
					if(impactos[foo] != null)
					{
						foo++;
					}else
					{
						impactos[foo] = impacto;
						finder = true;
					}
				}
			}
			int cont = 0;
			int morgan = 0;
			while(morgan < longitud)
			{
				if(impactos[morgan] != null)
				{
					cont++;
				}
				morgan++;
			}
			if(cont == longitud)
			{
				estado = Status.HUNDIDO;
			}else
			{
				if(cont == 0)
				{
					estado = Status.INTACTO;
				}
				else
				{
					if(cont > 0 && cont < longitud)
					{
						estado = Status.TOCADO;
					}
				}
			}
		*/
		impactos.put(impacto, true);
		Collection<Boolean> tocados = impactos.values();
		int cont = 0;
		for(Boolean comprobador: tocados)
		{
			if(comprobador)
			{
				cont++;
			}
		}
		
		if(cont == modelo.getLongitud())
		{
			estado = Status.HUNDIDO;
		}else
		{
			if(cont == 0)
			{
				estado = Status.INTACTO;
			}
			else
			{
				if(cont > 0 && cont < modelo.getLongitud())
				{
					estado = Status.TOCADO;
				}
			}
		}
		}
		public void hundirBarco(Barco pBarco)
		{
			/*
			for(int i = 0; i < this.posicion.length; i++)
			{
				setImpacto(posicion[i]);
			}
			*/
			
		}
		
		public void comprobarHundimiento(Barco pBarco){
			Casilla pCasilla;
			int tamañoBarco = pBarco.getModelo().getLongitud();
			for(int i=tamañoBarco;i>0;i--){
				//Recorre el baco de la IA y comprueba que todas sus casillas están: Reveladas (true)
				//Si el nº de casillas del Barco reveladas es igual al tamaño del barco
				//Entonces hundirBarco()
			}
		}
		
		public void repararBarco()
		{
			/*
			for(int charlie = 0; charlie < this.impactos.length; charlie++)
			{
				this.impactos[charlie] = null;
			}
			this.estado = Status.INTACTO;
			*/
		}
			
		//get & set (he eliminado los que no deberian de ser modificados tras su inicializacion)
		public Status getEstado() {
			return estado;
		}
		public void setEstado(Status estado) {
			this.estado = estado;
		}
		public boolean isProtegido() {
			return protegido;
		}
		public void setProtegido(boolean protegido) {
			this.protegido = protegido;
			setChanged();
			notifyObservers();
			this.notifyObservers(); // Indicar que es el tablero de barcos
		}
		/*
		public Casilla[] getPosicion() {
			return posicion;
		}
		*/
		/*
		public Casilla[] getImpactos() {
			return impactos;
		}
		*/

		public TipoDeBarco getModelo() {
			return modelo;
		}
		public boolean isHorizontal() {
			return horizontal;
		}
		public void setHorizontal(boolean horizontal) {
			this.horizontal = horizontal;
		}
		public Casilla getProa() {
			return proa;
		}
		public void setProa(Casilla proa) {
			this.proa = proa;
		}
		/*
		public void setPosicion(Casilla[] posicion) {
			this.posicion = posicion;
		}
		*/
		public void setMapa(Map<Casilla, Boolean> pMapa)
		{
			impactos = pMapa;
		}
		public Map<Casilla, Boolean> getMapa()
		{
			return impactos;
		}
		
		
	
	
}
