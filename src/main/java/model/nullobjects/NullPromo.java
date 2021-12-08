package model.nullobjects;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.exceptions.EscritorExceptions;
import model.exceptions.TipoException;
import model.promocion.Promo;

public class NullPromo extends Promo {

	public static Promo build() {
		Promo p = null;
		try {
			ArrayList<Atraccion> a = new ArrayList<Atraccion>();
			p = new NullPromo(a);
		} catch (TipoException te) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", te);
		}
		return p;
	}

	public boolean isNull() {
		return true;
	}

	public NullPromo(ArrayList<Atraccion> a) throws TipoException {
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
