package services;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.nullobjects.NullPromo;
import model.promocion.Promo;
import model.promocion.PromoAbsoluta;
import model.promocion.PromoAxB;
import model.promocion.PromoPorcentual;
import persistence.commons.DAOFactory;
import utils.ParseNumeros;

public class PromocionService {

	public void borrar(String id) {
		int idint = ParseNumeros.parInt(id);
		ArrayList<Atraccion> array = new ArrayList<Atraccion>();
		Promo p = new PromoAbsoluta(idint, array, "", "", 0);
		DAOFactory.getPromocionDAO().delete(p);
	}

	public List<Promo> buscarPromos() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	private Promo toPromo(String id, String tipo, String genero, String extra, String descripcion, String[] ids) {
		ArrayList<Atraccion> atracciones = DAOFactory.getPromocionDAO().listaAtracciones(ids);

		double extraD = ParseNumeros.parDouble(extra);
		Promo p = NullPromo.build();
		if (tipo.equals("Absoluta")) {
			p = new PromoAbsoluta(0, atracciones, genero, descripcion, extraD);
		} else if (tipo.equals("Porcentual")) {
			extraD /= 100; 
			p = new PromoPorcentual(0, atracciones, genero, descripcion, extraD);
		} else if (tipo.equals("AxB")) {
			p = new PromoAxB(0, atracciones, genero, descripcion);
		}

		return p;
	}

	public Promo crearPromo(String tipo, String genero, String extra, String descripcion, String[] atracciones) {

		Promo p = NullPromo.build();

		p = toPromo("0", tipo, genero, extra, descripcion, atracciones);

		if (p.isValido()) {
			DAOFactory.getPromocionDAO().insert(p);
		}

		return p;
	}

}
