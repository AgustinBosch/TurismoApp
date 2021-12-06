package model.nullobjects;

import model.Usuario;
import model.exceptions.DatosNegativosException;
import model.exceptions.EscritorExceptions;

public class NullUsuario extends Usuario {

	public static Usuario build() {
		Usuario u = null;
		try {
			u = new NullUsuario();
		} catch (DatosNegativosException dne) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", dne);
		}
		return u;
	}

	public NullUsuario() throws DatosNegativosException {
		super(0, "", "", "", 0, 0, null, false);
	}

	public boolean isNull() {
		return true;
	}
}
