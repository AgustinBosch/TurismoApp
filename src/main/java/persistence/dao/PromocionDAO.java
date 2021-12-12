package persistence.dao;

import java.util.ArrayList;

import model.Atraccion;
import model.promocion.Promo;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Promo> {
	
	public ArrayList<Atraccion> listaAtracciones(String[] ids);

}
