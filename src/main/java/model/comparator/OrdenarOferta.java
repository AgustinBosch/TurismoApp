package model.comparator;

import java.util.Comparator;

import model.Sugerible;

public class OrdenarOferta implements Comparator<Sugerible> {

	private String genero;

	public OrdenarOferta(String genero) {
		this.genero = genero;
	}

	@Override
	public int compare(Sugerible o1, Sugerible o2) {
		int resultado;
		resultado = -Boolean.compare(o1.esPromo(), o2.esPromo());
		if (resultado == 0) {
			if (o1.getGenero().equals(this.genero) && !o2.getGenero().equals(this.genero))
				resultado = -1;
			else if (!o1.getGenero().equals(this.genero) && o2.getGenero().equals(this.genero))
				resultado = 1;
			else {
				resultado = -Double.compare(o1.getCosto(), o2.getCosto());
				if (resultado == 0)
					resultado = -Double.compare(o1.getDuracion(), o2.getDuracion());
			}
		}
		return resultado;
	}

}
