package packMainJava;

import java.util.Observable;

public class Barco extends Observable{
	
	private Status estado;
	public TipoDeBarco modelo;
	private boolean protegido;
	private boolean horizontal;
	private Casilla proa;
	private Casilla[] posicion;
	private boolean[] impactos;
	
	//Constructor

	public Barco(TipoDeBarco pModelo)
	{
		this.modelo = pModelo;
		this.estado = Status.INTACTO;
		this.protegido = false;
		this.posicion = new Casilla[this.modelo.getLongitud()];
		this.impactos = new boolean[this.modelo.getLongitud()];
		for(int i = 0; i < impactos.length; i++)
		{
			impactos[i] = false;
		}
	}
	
		//Metodos
		public void setImpacto(Casilla impacto) {
			boolean finder = false;
			boolean done = false;
			int buscador;
			for(int foo = 0; foo < this.impactos.length; foo++)
			{
				if(impacto == this.posicion[foo] && impactos[foo])
				{
					finder = true;
				}
				else if(impacto == this.posicion[foo])
				{
					buscador = foo;
				}
			}
			if(!finder)
			{
				if(protegido)
				{
					protegido = false;
				}
				else
				{
					if(estado == Status.INTACTO)
					{
						estado = Status.TOCADO;
					}
				}
			}
		}
		
		public void hundirBarco()
		{
			for(int i = 0; i < this.posicion.length; i++)
			{
				setImpacto(posicion[i]);
			}
			
		}
		
		public void repararBarco()
		{
			for(int charlie = 0; charlie < this.impactos.length; charlie++)
			{
				this.impactos[charlie] = false;
			}
			this.estado = Status.INTACTO;
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
			this.notifyObservers(); // Indicar que es el tablero de barcos
		}
		public Casilla[] getPosicion() {
			return posicion;
		}

		public boolean[] getImpactos() {
			return impactos;
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
		public void setPosicion(Casilla[] posicion) {
			this.posicion = posicion;
		}

		
		
	
	
}
