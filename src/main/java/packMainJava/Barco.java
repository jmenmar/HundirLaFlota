package packMainJava;

public class Barco {
	
	Status estado;
	TipoDeBarco modelo;
	boolean protegido;
	boolean horizontal;
	Casilla proa;
	Casilla[] posicion;
	Casilla[] impactos;
	
	//Constructor
	public Barco(TipoDeBarco pModelo, boolean esHorizontal, Casilla laProa)
	{
		this.modelo = pModelo;
		this.horizontal = esHorizontal;
		this.proa = laProa;
		this.estado = Status.INTACTO;
		this.protegido = false;
		this.posicion = new Casilla[this.modelo.getLongitud()];
		this.impactos = new Casilla[this.modelo.getLongitud()];
		
		//Establecer posicion (DENTRO DEL CONSTRUCTOR LE�E YA)
		
		if(horizontal)
		{
			//IMPORTANTE, no se puede implementar hasta que Tablero lo este
			//ya que esto es un array de casillas DEL TABLERO
		}else
		{
			/*
			 * Lo mismo, en ambos casos es hace un set en las casillas DEL TABLERO que indiquen
			 * que las casillas estan ocupadas por un barco y luego registrar en el array 
			 * posicion[] en BARCO las casillas correspondientes, la operacion se efectua 
			 * mediante un +lonngitud desde la proa, en la x si horizontal y en 
			 * la y si no horizontal.
			*/
		}
		
	}
	
		//Metodos
		public void setImpacto(Casilla impacto) {
			boolean finder = false;
			boolean done = false;
			int cont = 0;
			for(int foo = 0; foo < this.impactos.length; foo++)
			{
				if(impacto == this.impactos[foo])
				{
					finder = true;
				}
			}
			if(!finder)
			{
				if(this.estado == Status.INTACTO)
				{
					this.estado = Status.TOCADO;
				}
				for(int fua = 0; fua < this.impactos.length; fua++)
				{
					if(this.impactos[fua] == null && !done)
					{
						this.impactos[fua] = impacto;
					}
				}
				for(int alpha = 0; alpha < this.impactos.length; alpha++)
				{
					for(int bravo = 0; bravo < this.posicion.length; bravo++)
					{
						if(this.impactos[alpha]==this.posicion[bravo])
						{
							cont++;
						}
					}
				}
				if(cont == this.posicion.length)
				{
					this.estado = Status.HUNDIDO;
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
				this.impactos[charlie] = null;
			}
			this.estado = Status.HUNDIDO;
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
		}
		public Casilla[] getPosicion() {
			return posicion;
		}

		public Casilla[] getImpactos() {
			return impactos;
		}

		
		
	
	
}
