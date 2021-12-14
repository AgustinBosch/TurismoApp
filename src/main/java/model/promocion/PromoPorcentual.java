package model.promocion;

import java.util.ArrayList;

import model.Atraccion;

public class PromoPorcentual extends Promo {

	private final double porcentajeDescuento;

	public PromoPorcentual(int id, ArrayList<Atraccion> atraccionesPromo, String generoDePromo, String descripcion,
			double porcentajeDescuento) {
		super(id, atraccionesPromo, generoDePromo, descripcion);
		this.porcentajeDescuento = porcentajeDescuento;
		this.costo = setCosto();
	}

	@Override
	public void validar() {
		super.validar();
		if (porcentajeDescuento < 0) {
			this.errors.put("extra", "Descuento ser positivo");
		}
		if (porcentajeDescuento > 100) {
			this.errors.put("extra", "Descuento ser menor a 100");
		}

	}

	private double setCosto() {
		double costo = 0;
		for (Atraccion i : this.getMisAtracciones()) {
			costo += i.getCosto();
		}
		double desc = this.porcentajeDescuento / 100.0;
		costo -= costo * desc;
		return costo;
	}

	@Override
	public double getExtra() {
		return this.porcentajeDescuento;
	}

	@Override
	public String getTipoPromo() {
		return "Porcentual";
	}

	// Retorna el valor de la promocion con el descuento ya aplicado

}
