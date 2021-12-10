package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Atraccion;
import model.Sugerible;
import model.comparator.OrdenarOferta;
import model.promocion.Promo;
import persistence.commons.DAOFactory;
import persistence.dao.AtraccionDAO;
import persistence.dao.PromocionDAO;

public class SugeribleService {
	
	public List<Sugerible> buscarSugeribles(String gusto) {
		AtraccionDAO ad = DAOFactory.getAtraccionDAO();
		PromocionDAO pd = DAOFactory.getPromocionDAO();
		List<Atraccion> atracciones = ad.findAll();
		List<Promo> promos = pd.findAll();
		List<Sugerible> sugeribles = new ArrayList<Sugerible>();
		sugeribles.addAll(atracciones);
		sugeribles.addAll(promos);
		Collections.sort(sugeribles, new OrdenarOferta(gusto));
		return sugeribles;
	}

}
