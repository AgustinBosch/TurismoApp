package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2124912783074842275L;
	LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombrelogin");
		String pass = req.getParameter("passwordlogin");

		Usuario usuario = loginService.login(nombre, pass);

		if (!usuario.isNull()) {
			req.getSession().setAttribute("user", usuario);
			resp.sendRedirect("index.jsp");
		} else {
			req.setAttribute("flash", "Nombre o contraseña incorrectos");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, resp);
		}

	}
}