package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Usuario;
import services.AtraccionService;
import services.UsuarioService;

@WebServlet("/crearUsuario.do")
public class CrearUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1318737052720197683L;
	UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/crearUsuario.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombreusuario");
		String pass = req.getParameter("passwordusuario");
		String oro = req.getParameter("orousuario");
		String tiempo = req.getParameter("tiempousuario");
		String genero = req.getParameter("generousuario");
		String admin = req.getParameter("adminusuario");

		Usuario u = usuarioService.registrarUsuario(nombre, pass, genero, oro, tiempo, admin);

		if (u.isValido()) {
			resp.sendRedirect("/TurismoApp/administrar.do");
		} else {
			req.setAttribute("usuario", u);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/usuario/crearUsuario.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
