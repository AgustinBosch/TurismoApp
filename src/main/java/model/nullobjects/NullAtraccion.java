package model.nullobjects;

import model.Atraccion;
import model.exceptions.DatosNegativosException;
import model.exceptions.EscritorExceptions;

public class NullAtraccion extends Atraccion {

	public static Atraccion build() {
		Atraccion a = null;
		try {
			a = new NullAtraccion();
		} catch (DatosNegativosException dne) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", dne);
		}
		return a;
	}

	public boolean isNull() {
		return true;
	}

	public NullAtraccion() throws DatosNegativosException {
		super(0, "", 0, "", 0, 0, "");
	}

}
