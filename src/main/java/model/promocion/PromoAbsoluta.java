package model.promocion;

import java.util.ArrayList;

import model.Atraccion;

public class PromoAbsoluta extends Promo {
	
	private double nuevoCosto;

	public PromoAbsoluta(int id, ArrayList<Atraccion> miPromo, String generoDePromo, String descripcion, double nuevoCosto) {
		super(id, miPromo, generoDePromo, descripcion);
		this.nuevoCosto = nuevoCosto;
		this.costo = nuevoCosto;
	}

	/*
	 * PRE: Recibe el costo de la promocion POST: Retorna el costo de la promocion
	 * en caso de que sea valido, caso contrario lanza DatosNegativosException
	 */
	
	@Override
	public void validar() {
		super.validar();
		if(nuevoCosto <= 0) {
			this.errors.put("Nuevo Costo", "Debe ser positivo");
		}
		
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
