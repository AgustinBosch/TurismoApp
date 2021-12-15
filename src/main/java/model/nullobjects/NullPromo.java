package model.nullobjects;

import java.util.ArrayList;

import model.Atraccion;
import model.promocion.Promo;

public class NullPromo extends Promo {

	public static Promo build() {
		Promo p = null;

		ArrayList<Atraccion> a = new ArrayList<Atraccion>();
		p = new NullPromo(a);

		return p;
	}

	public boolean isNull() {
		return true;
	}

	public NullPromo(ArrayList<Atraccion> a) {
		super(0, a, "", "");
	}

	@Override
	public String getTipoPromo() {
		return "";
	}

	@Override
	public double getExtra() {
		return 0;
	}

}
