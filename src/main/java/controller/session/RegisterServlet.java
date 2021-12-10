package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 2244947150162678906L;
	UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombreregister");
		String pass = req.getParameter("passwordregister");
		String tipo = req.getParameter("tiporegister");
		String oro = req.getParameter("ororegister");
		String tiempo = req.getParameter("tiemporegister");
		String admin = req.getParameter("adminregister");

		if (usuarioService.existeNombre(nombre)) {
			req.setAttribute("flashreg", "Nombre no disponible");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			Usuario userregistro = usuarioService.registrarUsuario(nombre, pass, tipo, oro, tiempo, admin);
			if (!userregistro.isNull()) {
				Usuario user = usuarioService.login(nombre, pass);
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("index.jsp");
			} else {
				req.setAttribute("flashreg", "Usuario invalido");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(req, resp);
			}
		}
	}

}
