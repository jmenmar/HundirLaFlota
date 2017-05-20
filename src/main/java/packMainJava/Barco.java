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
	private Map<Casilla, Boolean> impactos = new HashMap<Casilla, Boolean>();
	
	//Constructor

	public Barco(TipoDeBarco pModelo)
	{
		this.modelo = pModelo;
		this.estado = Status.INTACTO;
		this.protegido = false;
	}
	
		//Metodos
		public void setImpacto(Casilla impacto) {
			
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
		
			for(Casilla impacto:impactos.keySet())
				 {
				setImpacto(impacto);
				 }
			
		}
		
		public boolean comprobarHundimiento(){
			boolean resultado = false;
			int cont = 0;
			for(Casilla comprobador: impactos.keySet())
			{
				if(impactos.get(comprobador))cont++;
			}
			if(cont == impactos.size())resultado = true;
			return resultado;
		}
		
		public void repararBarco()
		{
			for(Casilla damage:impactos.keySet())
			{
				impactos.put(damage, false);
				setEstado(Status.INTACTO);
			}
			
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
		public void setMapa(Map<Casilla, Boolean> pMapa)
		{
			impactos = pMapa;
		}
		public Map<Casilla, Boolean> getMapa()
		{
			return impactos;
		}
		
		
	
	
}
