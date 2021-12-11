package services;

import java.util.List;

import model.Itinerario;
import model.Usuario;
import model.nullobjects.NullUsuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;
import utils.ParseNumeros;

public class UsuarioService {

	public List<Usuario> buscarUsuarios() {
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
	
//	private Usuario toUsuario(String id, String nombre, String password, String tipo, String oro, String tiempo,
//			String admin) {
//		
//		double oroD = ParseNumeros.parDouble(oro);
//		double tiempoD = ParseNumeros.parDouble(tiempo);
//		boolean adminB = Boolean.parseBoolean(admin);
//		String nombreLower = nombre.toLowerCase();
//		Itinerario i = new Itinerario();
//
//		u = new Usuario(0, nombreLower, password, tipo, oroD, tiempoD, i, adminB);
//		
//	}

	public Usuario registrarUsuario(String nombre, String password, String tipo, String oro, String tiempo,
			String admin) {
		Usuario u = NullUsuario.build();


		double oroD = ParseNumeros.parDouble(oro);
		double tiempoD = ParseNumeros.parDouble(tiempo);
		boolean adminB = Boolean.parseBoolean(admin);
		String nombreLower = nombre.toLowerCase();
		Itinerario i = new Itinerario();

		u = new Usuario(0, nombreLower, password, tipo, oroD, tiempoD, i, adminB);
		u.setPass(password);
		
		if (u.isValido()) {
			DAOFactory.getUsuarioDAO().insert(u);
		}
		return u;
	}

}
