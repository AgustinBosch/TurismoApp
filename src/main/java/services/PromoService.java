package services;

import java.util.List;

import model.promocion.Promo;
import persistence.commons.DAOFactory;

public class PromoService {

	public List<Promo> buscarPromos() {
		return DAOFactory.getPromocionDAO().findAll();
	}
}
