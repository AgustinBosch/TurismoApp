package model.promocion;

import java.util.ArrayList;

import model.Atraccion;
import model.exceptions.TipoException;

public class PromoAxB extends Promo {

	public PromoAxB(int id, ArrayList<Atraccion> atraccionesPromo, String generoDePromo) throws TipoException 	 {
		super(id, atraccionesPromo, generoDePromo);
		this.costo = setCosto();
	}

	private double setCosto() {
		double costo = 0;
		for (Atraccion i: this.getMisAtracciones()) {
			costo += i.getCosto();
		}
		costo -= this.getMisAtracciones().get(this.getMisAtracciones().size() - 1).getCosto();
		return costo;
	}

	@Override
	public double getExtra() {
		return 0;
	}

	@Override
	public String getTipoPromo() {
		return "AxB";
	}

	

}
