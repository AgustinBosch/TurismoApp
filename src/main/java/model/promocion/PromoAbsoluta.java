package model.promocion;

import java.util.ArrayList;

import model.Atraccion;
import model.exceptions.DatosNegativosException;
import model.exceptions.TipoException;

public class PromoAbsoluta extends Promo {
	
	private double nuevoCosto;

	public PromoAbsoluta(int id, ArrayList<Atraccion> miPromo, String generoDePromo, String descripcion, double nuevoCosto) throws TipoException, DatosNegativosException
			  {
		super(id, miPromo, generoDePromo, descripcion);
		this.nuevoCosto = nuevoCosto;
		this.costo = validarNuevoCosto(nuevoCosto);
	}

	/*
	 * PRE: Recibe el costo de la promocion POST: Retorna el costo de la promocion
	 * en caso de que sea valido, caso contrario lanza DatosNegativosException
	 */

	private double validarNuevoCosto(double nuevoCosto) throws DatosNegativosException {
		if (nuevoCosto < 0) {
			throw new DatosNegativosException("El nuevo costo de la promo: " + this.getNombre() + " es negativo");
		}
		return nuevoCosto;
	}

	@Override
	public double getExtra() {
		return this.nuevoCosto;
	}

	@Override
	public String getTipoPromo() {
		return "Absoluta";
	}

}
