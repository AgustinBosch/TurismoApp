package model.promocion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Sugerible;

public abstract class Promo implements Sugerible {

	private ArrayList<Atraccion> miPromo;
	private final double duracionPromedio;
	private final String generoDePromo;
	protected double costo;
	private int id;
	private String descripcion;
	protected Map<String, String> errors;

	public Promo(int id, ArrayList<Atraccion> miPromo, String generoDePromo, String descripcion) {
		this.miPromo = miPromo;
		this.duracionPromedio = this.setDuracion();
		this.generoDePromo = generoDePromo;
		this.id = id;
		this.descripcion = descripcion;
	}

	public boolean isValido() {
		validar();
		return errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return this.errors;
	}

	public void validar() {
		errors = new HashMap<String, String>();
		if (this.miPromo.isEmpty()) {
			errors.put("atracciones", "No atracciones seleccionadas");
		}

		for (Atraccion atr : miPromo) {
			if (!atr.getGenero().equals(generoDePromo)) {
				errors.put(atr.getNombre(), atr.getNombre() + " genero distinto");
			}
		}
	}

	private double setDuracion() {
		double duracion = 0d;
		for (Atraccion atraccion : this.miPromo) {
			duracion += atraccion.getDuracion();
		}
		return duracion;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	public ArrayList<Atraccion> getMisAtracciones() {
		return this.miPromo;
	}
	
	public String getMisAtraccionesString() {
		String texto = "";
		for (Atraccion atraccion : miPromo) {
			texto += atraccion.getNombre() + "-";
		}
		return texto.substring(0, texto.length() - 1);
	}

	@Override
	public String getGenero() {
		return this.generoDePromo;
	}

	public abstract String getTipoPromo();

	public abstract double getExtra();

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getDuracion() {
		return this.duracionPromedio;
	}

	@Override
	public boolean tieneCupo() {
		for (Atraccion atraccion : this.miPromo) {
			if (!atraccion.tieneCupo())
				return false;
		}
		return true;
	}

	@Override
	public void ocuparLugar() {
		for (Atraccion atraccion : this.miPromo)
			atraccion.ocuparLugar();
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String getNombre() {
		return "Pack " + this.generoDePromo;
	}

	@Override
	public String toString() {
		return this.getNombre() + " cuesta: " + this.getCosto() + ", dura: " + this.duracionPromedio + " horas";
	}

	/*
	 * PRE: Recibe un sugerible por parametro POST: Retorna True si this y s tienen
	 * algun elemento en comun
	 */

	@Override
	public boolean tengoSugerible(Sugerible s) {
		boolean resultado = false;
		int i = 0;
		while (!resultado && i < this.miPromo.size())
			resultado = s.tengoSugerible(this.miPromo.get(i++));

		return resultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
