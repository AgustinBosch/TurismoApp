package services;

import java.text.ParseException;
import java.util.List;

import model.Itinerario;
import model.Usuario;
import model.exceptions.DatosNegativosException;
import model.nullobjects.NullUsuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;

public class UsuarioService {

	public List<Usuario> buscarUsuarios(){
		return DAOFactory.getUsuarioDAO().findAll();
	}
	
	
	public Usuario login(String nombre, String pass) {
		UsuarioDAO ud = DAOFactory.getUsuarioDAO();
		String nombreLower = nombre.toLowerCase();
		Usuario usuario = ud.findByName(nombreLower);
		if (usuario.isNull() || !usuario.checkPassword(pass)) {
			usuario = NullUsuario.build();
		}
		return usuario;

	}

	public boolean existeNombre(String nombre) {
		return DAOFactory.getUsuarioDAO().existeUsuario(nombre);
	}

	public Usuario registrarUsuario(String nombre, String password, String tipo, String oro, String tiempo,
			String admin) {
		UsuarioDAO ud = DAOFactory.getUsuarioDAO();
		Usuario u = NullUsuario.build();
		try {
			u = parseycrear(nombre, password, tipo, oro, tiempo, admin);
			u.setPass(password);
			ud.insert(u);
		} catch (DatosNegativosException | ParseException e) {
			System.out.println("Usuario Invalido");
		}
		return u;
	}

	private Usuario parseycrear(String nombre, String password, String tipo, String oro, String tiempo, String admin)
			throws DatosNegativosException, ParseException {
		double oroD = Double.parseDouble(oro);
		double tiempoD = Double.parseDouble(tiempo);
		boolean adminB = Boolean.parseBoolean(admin);
		String nombreLower = nombre.toLowerCase();
		Itinerario i = new Itinerario();
		Usuario u = new Usuario(0, nombreLower, password, tipo, oroD, tiempoD, i, adminB);
		u.setPass(password);
		return u;
	}

}
