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
	RegisterService registerService;

	@Override
	public void init() throws ServletException {
		super.init();
		registerService = new RegisterService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombreregister");
		String pass = req.getParameter("passwordregister");
		String tipo = req.getParameter("tiporegister");
		Double oro = Double.parseDouble(req.getParameter("ororegister"));
		Double tiempo = Double.parseDouble(req.getParameter("tiemporegister"));

		if (registerService.existeNombre(nombre)) {
			req.setAttribute("flashreg", "Nombre no disponible");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			
			Usuario user = registerService.registrarUsuario(nombre, pass, tipo, oro, tiempo);
			if (!user.isNull()) {
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
