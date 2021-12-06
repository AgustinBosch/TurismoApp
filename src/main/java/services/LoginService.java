package services;

import model.Usuario;
import model.nullobjects.NullUsuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;

public class LoginService {
	
	public Usuario login(String nombre, String pass) {
		UsuarioDAO ud = DAOFactory.getUsuarioDAO();
		Usuario usuario = ud.findByName(nombre);
		//|| !usuario.checkPassword(pass)
		if (usuario.isNull() || !usuario.checkPassword(pass)) {
			usuario = NullUsuario.build();
    	}
		
		return usuario;
		
	}

}
