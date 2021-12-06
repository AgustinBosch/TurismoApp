package model.nullobjects;

import model.exceptions.EscritorExceptions;
import model.exceptions.TipoException;
import model.promocion.Promo;

public class NullPromo extends Promo {

	public static Promo build() {
		Promo p = null;
		try {
			p = new NullPromo();
		} catch (TipoException te) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", te);
		}
		return p;
	}

	public boolean isNull() {
		return true;
	}

	public NullPromo() throws TipoException {
		super(0, null, "");
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
