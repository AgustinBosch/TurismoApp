package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.promocion.*;



public class tengoSugeribleTest {

	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	Atraccion atraccion4;
	Atraccion atraccion5;

	PromoAbsoluta promoAbsoluta;
	PromoAbsoluta promoAbsolutaDos;
	PromoPorcentual promoPorcentual;

	@Before
	public void startUp() {
		atraccion1 = new Atraccion(1, "Moira", 10, "AVENTURA", 2, 6, "texto desc");
		atraccion2 = new Atraccion(2, "Minas Tirith", 5, "PAISAJE", 3, 25, "texto desc");
		atraccion3 = new Atraccion(3, "Erebor", 12, "PAISAJE", 3, 32, "texto desc");
		atraccion4 = new Atraccion(4, "Bosque Negro", 3, "AVENTURA", 4, 12, "texto desc");
		atraccion5 = new Atraccion(5, "Atraccion de Aventura", 10, "AVENTURA", 10, 10, "texto desc");

		ArrayList<Atraccion> l1 = new ArrayList<Atraccion>();
		l1.add(atraccion1);
		l1.add(atraccion4);
		promoAbsoluta = new PromoAbsoluta(1, l1, "AVENTURA", "texto desc", 3);

		ArrayList<Atraccion> l4 = new ArrayList<Atraccion>();
		l4.add(atraccion1);
		l4.add(atraccion5);
		promoAbsolutaDos = new PromoAbsoluta(2, l4, "AVENTURA", "texto desc", 3);

		ArrayList<Atraccion> l2 = new ArrayList<Atraccion>();
		l2.add(atraccion2);
		l2.add(atraccion3);
		promoPorcentual = new PromoPorcentual(3, l2, "PAISAJE", "texto desc", 10);

	}

	@Test
	public void pruebaAtraccionContiene() {

		assertTrue(atraccion1.tengoSugerible(atraccion1));
		assertTrue(atraccion1.tengoSugerible(promoAbsolutaDos));
		assertTrue(atraccion4.tengoSugerible(promoAbsoluta));
		assertTrue(atraccion5.tengoSugerible(promoAbsolutaDos));

	}

	@Test
	public void pruebaPromoContiene() {
		assertTrue(promoAbsoluta.tengoSugerible(atraccion1));
		assertTrue(promoAbsoluta.tengoSugerible(atraccion4));
		assertTrue(promoAbsoluta.tengoSugerible(promoAbsolutaDos));
		assertTrue(promoAbsolutaDos.tengoSugerible(promoAbsoluta));
	}

	@Test
	public void pruebaPromoNoContiene() {
		assertFalse(promoAbsoluta.tengoSugerible(atraccion2));
		assertFalse(promoAbsoluta.tengoSugerible(atraccion3));
		assertFalse(promoAbsolutaDos.tengoSugerible(atraccion3));
	}

	@Test
	public void pruebaAtraccionNoContiene() {
		assertFalse(atraccion1.tengoSugerible(atraccion2));
		assertFalse(atraccion2.tengoSugerible(promoAbsoluta));
		assertFalse(atraccion4.tengoSugerible(promoPorcentual));
	}

}
