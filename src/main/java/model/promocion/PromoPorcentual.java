package model.promocion;

import java.util.ArrayList;

import model.Atraccion;
import model.exceptions.DatosNegativosException;
import model.exceptions.TipoException;

public class PromoPorcentual extends Promo {

	private final double porcentajeDescuento;

	public PromoPorcentual(int id, ArrayList<Atraccion> atraccionesPromo, String generoDePromo, String descripcion, double porcentajeDescuento) throws DatosNegativosException, TipoException  {
		super(id, atraccionesPromo, generoDePromo, descripcion);
		this.porcentajeDescuento = validarDescuento(porcentajeDescuento);
		this.costo = setCosto();
	}

	/*
	 * PRE: Recibe el porcentaje de descuento de la promocion
	 * POST: Retorna el porcentaje de descuento de la promocion en caso de que sea valido,
	 * 		caso contrario lanza DatosNegativosException
	 */
	
	private double validarDescuento(double porcentajeDescuento)throws DatosNegativosException {
		if (porcentajeDescuento < 0) {
			throw new DatosNegativosException("El porcentaje de descuento de la promo: " + this.getNombre() + " es negativo");
		}
		return porcentajeDescuento;
	}
	
	private double setCosto() {
		double costo = 0;
		for (Atraccion i: this.getMisAtracciones()) {
			costo += i.getCosto();
		}
		costo -= costo * this.porcentajeDescuento; 
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
	
	
	
	
	//Retorna el valor de la promocion con el descuento ya aplicado
	
	

}
