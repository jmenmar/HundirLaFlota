package packMainJava;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestsUnitarios {

	@Test
	public void testTableroYCasillasNoNulos() {
		Tablero testTablero = new Tablero(true);
		assertNotNull("Tablero no nulo.", testTablero);
		for(int i = 0; i < testTablero.getMaxCol(); i++)
		{
			for(int j = 0; j < testTablero.getMaxFil(); j++)
			{
				Casilla casilla = testTablero.getCasilla(i, j);
				assertNotNull("Casilla no nula: " + i + j, casilla);
			}
		}
	}
	
@Test
public void testCasillasTrueFalse()
{
	Tablero testTablero = new Tablero(true);
	Tablero testTablero2 = new Tablero(false);
	for(int i = 0; i < testTablero.getMaxCol(); i++)
	{
		for(int j = 0; j < testTablero.getMaxFil(); j++)
		{
			Casilla casilla = testTablero.getCasilla(i, j);
			Casilla casilla2 = testTablero2.getCasilla(i, j);
			assertTrue("Tablero 1, Casilla revelada: " + i + j, casilla.isRevelado());
			assertFalse("Tablero 2, Casilla no revelada: " + i + j, casilla2.isRevelado());
		}
	}
}

@Test
public void testIaNoNula()
{
	assertNotNull("IA no nula: ", IA.getIA());
}

@Test
public void testInventarioNoNulo()
{
	assertNotNull("Inventario no nulo: ", Inventario.getInventario());
}

@Test
public void testPlayerNoNulo()
{
	assertNotNull("Jugador no nulo", Player.getPlayer());
}
}
