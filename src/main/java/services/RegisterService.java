package services;


import model.Itinerario;
import model.Usuario;
import model.exceptions.DatosNegativosException;
import model.nullobjects.NullUsuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;

public class RegisterService {

	public Usuario registrarUsuario(String nombre, String password, String tipo, double oro, double tiempo) {
		UsuarioDAO ud = DAOFactory.getUsuarioDAO();

		Itinerario i = new Itinerario();
		Usuario u = NullUsuario.build();

		try {
			u = new Usuario(0, nombre, password, tipo, oro, tiempo, i, false);
			u.setPass(password);
		} catch (DatosNegativosException e) {
			e.printStackTrace();
		}
		ud.insert(u);
		return u;
	}

}
