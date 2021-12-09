package services;

import model.Sugerible;
import model.Usuario;
import persistence.commons.DAOFactory;

public class ComprarService {
	
	public void comprar(Integer usuarioId, Integer sugeribleId, boolean promo) {
		
		Usuario usuario = DAOFactory.getUsuarioDAO().findbyID(usuarioId);
		Sugerible sug;
		if(promo) {
			sug = DAOFactory.getPromocionDAO().findbyID(sugeribleId);
		}else {
			sug = DAOFactory.getAtraccionDAO().findbyID(sugeribleId);
		}
		
		if(usuario.puedoComprarSugerible(sug) && sug.tieneCupo() && !usuario.yaCompreSugerible(sug)) {
			usuario.comprarSugerible(sug);
			sug.ocuparLugar();
		}
		
		
	}

}
