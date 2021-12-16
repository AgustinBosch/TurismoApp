package model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import model.comparator.OrdenarOferta;
import model.promocion.*;


public class ComparatorTest {

	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	Atraccion atraccion4;
	Atraccion atraccion5;
	Atraccion atraccion6;

	PromoAbsoluta promoAbsoluta;
	PromoPorcentual promoPorcentual;
	PromoAxB promoAxB;

	ArrayList<Sugerible> sugeribles;

	@Before
	public void startUp() {
		atraccion1 = new Atraccion(1, "Moira", 10, "AVENTURA", 2, 6, "texto desc");
		atraccion2 = new Atraccion(2, "Minas Tirith", 5, "PAISAJE", 3, 25, "texto desc");
		atraccion3 = new Atraccion(3, "La Comarca", 3, "DEGUSTACION", 6, 150, "texto desc");
		atraccion4 = new Atraccion(4, "Erebor", 12, "PAISAJE", 3, 32, "texto desc");
		atraccion5 = new Atraccion(5, "Bosque Negro", 3, "AVENTURA", 4, 12, "texto desc");
		atraccion6 = new Atraccion(6, "Lothl�rien", 35, "DEGUSTACION", 1, 30, "texto desc");

		ArrayList<Atraccion> l1 = new ArrayList<Atraccion>();
		l1.add(atraccion1);
		l1.add(atraccion5);
		promoAbsoluta = new PromoAbsoluta(1, l1, "AVENTURA", "texto desc", 3);

		ArrayList<Atraccion> l2 = new ArrayList<Atraccion>();
		l2.add(atraccion2);
		l2.add(atraccion4);
		promoPorcentual = new PromoPorcentual(2, l2, "PAISAJE", "texto desc", 10);

		ArrayList<Atraccion> l3 = new ArrayList<Atraccion>();
		l3.add(atraccion6);
		l3.add(atraccion3);
		promoAxB = new PromoAxB(3, l3, "DEGUSTACION", "texto desc");

		sugeribles = new ArrayList<Sugerible>();
		sugeribles.add(atraccion1);
		sugeribles.add(atraccion2);
		sugeribles.add(atraccion3);
		sugeribles.add(atraccion4);
		sugeribles.add(atraccion5);
		sugeribles.add(atraccion6);
		sugeribles.add(promoAbsoluta);
		sugeribles.add(promoPorcentual);
		sugeribles.add(promoAxB);

	}

	@Test
	public void testOrdenarPorAventura() {
		ArrayList<Sugerible> esperado = new ArrayList<Sugerible>();
		esperado.add(promoAbsoluta);
		esperado.add(promoAxB);
		esperado.add(promoPorcentual);
		esperado.add(atraccion1);
		esperado.add(atraccion5);
		esperado.add(atraccion6);
		esperado.add(atraccion4);
		esperado.add(atraccion2);
		esperado.add(atraccion3);
		
		Collections.sort(sugeribles, new OrdenarOferta("AVENTURA"));
		assertTrue(esperado.equals(sugeribles));
	}

	@Test
	public void testOrdenarPorDegustacion() {
		ArrayList<Sugerible> esperado = new ArrayList<Sugerible>();
		esperado.add(promoAxB);
		esperado.add(promoPorcentual);
		esperado.add(promoAbsoluta);
		esperado.add(atraccion6);
		esperado.add(atraccion3);
		esperado.add(atraccion4);
		esperado.add(atraccion1);
		esperado.add(atraccion2);
		esperado.add(atraccion5);

		Collections.sort(sugeribles, new OrdenarOferta("DEGUSTACION"));
		assertTrue(esperado.equals(sugeribles));

	}

	@Test
	public void testOrdenarPorPaisaje() {
		ArrayList<Sugerible> esperado = new ArrayList<Sugerible>();
		esperado.add(promoPorcentual);
		esperado.add(promoAxB);
		esperado.add(promoAbsoluta);
		esperado.add(atraccion4);
		esperado.add(atraccion2);
		esperado.add(atraccion6);
		esperado.add(atraccion1);
		esperado.add(atraccion3);
		esperado.add(atraccion5);
		
		
		Collections.sort(sugeribles, new OrdenarOferta("PAISAJE"));
		assertTrue(esperado.equals(sugeribles));

	}

}
