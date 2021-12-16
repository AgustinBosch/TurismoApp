package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.promocion.*;

public class AtraccionYpromoTest {

	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	Atraccion a5;
	Atraccion a6;

	PromoAbsoluta pa;
	PromoPorcentual pp;
	PromoAxB pab;

	@Before
	public void startUp() {
		a1 = new Atraccion(1, "Moira", 10, "AVENTURA", 2, 6, "texto desc");
		a2 = new Atraccion(2, "Minas Tirith", 5, "PAISAJE", 3, 25, "texto desc");
		a3 = new Atraccion(3, "La Comarca", 3, "DEGUSTACION", 6, 150, "texto desc");
		a4 = new Atraccion(4, "Erebor", 12, "PAISAJE", 3, 32, "texto desc");
		a5 = new Atraccion(5, "Bosque Negro", 3, "AVENTURA", 4, 12, "texto desc");
		a6 = new Atraccion(6, "Lothl�rien", 35, "DEGUSTACION", 1, 30, "texto desc");

		ArrayList<Atraccion> l1 = new ArrayList<Atraccion>();
		l1.add(a1);
		l1.add(a5);
		pa = new PromoAbsoluta(1, l1, "AVENTURA", "texto desc", 3);

		ArrayList<Atraccion> l2 = new ArrayList<Atraccion>();
		l2.add(a2);
		l2.add(a4);
		pp = new PromoPorcentual(2, l2, "PAISAJE", "texto desc", 0.1);

		ArrayList<Atraccion> l3 = new ArrayList<Atraccion>();
		l3.add(a3);
		l3.add(a6);
		l3.add(a6);
		pab = new PromoAxB(3, l3, "DEGUSTACION", "texto desc");
	}

	@Test
	public void pruebaCostoAtraccion() {
		assertEquals(10, a1.getCosto(), 0);
		assertEquals(3, a3.getCosto(), 0);
		assertEquals(12, a4.getCosto(), 0);
		assertEquals(35, a6.getCosto(), 0);
	}

	@Test
	public void pruebaCostoPromos() {

		assertEquals(3, pa.getCosto(), 0);
		assertEquals(15.3, pp.getCosto(), 10);
		assertEquals(38, pab.getCosto(), 0);

	}

	@Test
	public void tiempoDeLasPromosCorrecto() {
		assertEquals(6, pa.getDuracion(), 0);
		assertEquals(6, pp.getDuracion(), 0);
		assertEquals(8, pab.getDuracion(), 0);
	}
}
