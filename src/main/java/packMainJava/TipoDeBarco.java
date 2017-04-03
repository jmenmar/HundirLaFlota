package packMainJava;

public enum TipoDeBarco {
PORTAAVIONES(4), SUBMARINO(3), DESTRUCTOR(2), FRAGATA(1);

private int longitud;

	TipoDeBarco(int pLongitud)
	{
		this.longitud = pLongitud;
	}

	public int getLongitud()
	{
		return longitud;
	}
}
